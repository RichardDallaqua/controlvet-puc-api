package controlvet.rest.api.service;

import controlvet.rest.api.dto.ProdutoDto;
import controlvet.rest.api.entity.ProcedimentoEntity;
import controlvet.rest.api.entity.ProdutoEntity;
import controlvet.rest.api.helper.NumberManager;
import controlvet.rest.api.helper.StringManager;
import controlvet.rest.api.helper.csvmanager.CsvGenerator;
import controlvet.rest.api.repository.ProdutoRepository;
import controlvet.rest.api.vo.ProdutoVo;
import controlvet.rest.api.vo.generic.ExportarCsvVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private PerfilService perfilService;

    public ProdutoEntity findById(Integer id) throws ValidationException {
        return this.repository.findById(id).orElseThrow(() -> new ValidationException("Produto não encontrado"));
    }

    public List<ProdutoVo> findByFiltros(String descricao, Boolean listarInativos) throws ValidationException {
        return this.repository.findByFiltros(descricao, Boolean.TRUE.equals(listarInativos) ? 1 : 0);
    }
    
    public ProdutoEntity save(ProdutoDto dto) throws ValidationException {
        ProdutoEntity entity = new ProdutoEntity();

        validarDados(dto);

        if (isUpdate(dto)) {
            entity = this.repository.findById(dto.getId()).get();
        } else {
            entity.setDataCadastro(new Date());
        }
        entity.setDescricao(dto.getDescricao());
        entity.setValor(dto.getValor());

        return repository.save(entity);
    }

    public ProdutoEntity disableOrEnableById(Integer id) throws ValidationException {
        ProdutoEntity entity = new ProdutoEntity();
        entity = this.repository.findById(id).get();
        entity.setDataDesativacao(entity.getDataDesativacao()== null ? new Date() : null );

        return repository.save(entity);
    }

    public Boolean isUpdate(ProdutoDto dto) {
        return (dto.getId() != null && !dto.getId().equals(0));
    }

    public ExportarCsvVo generateCsv(String descricao, Boolean listarInativos) throws ValidationException, IOException {

        ExportarCsvVo exportarCsv = new ExportarCsvVo();

        List<ProdutoVo> listaDados = this.repository.findByFiltros(descricao, Boolean.TRUE.equals(listarInativos) ? 1 : 0);

        if (!listaDados.isEmpty()) {
            String caminhoArquivo = new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Produto\"");

            Path path = Paths.get(caminhoArquivo);
            exportarCsv.setArquivo(Files.readAllBytes(path));
            exportarCsv.setNomeArquivo(path.getFileName().toString());
        } else {
            throw new ValidationException("Não existem dados para imprimir");
        }
        return exportarCsv;
    }

    private boolean validarDados (ProdutoDto dto) throws ValidationException {
        StringManager sm = new StringManager();
        if (dto == null) {
            throw new ValidationException("Informações invalidas");
        }
        if (sm.isNullOrEmpty(dto.getDescricao())) {
            throw new ValidationException("Valor informado para a descrição é inválido.");
        }

        if (NumberManager.getInstance().isNullOrZero(dto.getValor()) || dto.getValor() < 0) {
            throw new ValidationException("Valor informado para valor é inválido.");
        }

        ProdutoEntity produto = this.repository.findBydescricao(dto.getDescricao());

        if (produto != null && produto.getDescricao().equals(dto.getDescricao()) &&
            !produto.getId().equals(dto.getId())) {
            throw new ValidationException("Já existe um produto com essa descrição.");
        }

        return true;
    }
}
