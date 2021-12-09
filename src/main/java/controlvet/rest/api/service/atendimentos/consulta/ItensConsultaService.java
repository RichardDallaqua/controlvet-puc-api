package controlvet.rest.api.service.atendimentos.consulta;

import controlvet.rest.api.dto.atendimentos.consulta.ItensConsultaDto;
import controlvet.rest.api.dto.cadastros.AnimalDto;
import controlvet.rest.api.entity.atendimentos.consulta.ConsultaEntity;
import controlvet.rest.api.entity.atendimentos.consulta.ItensConsultaEntity;
import controlvet.rest.api.entity.cadastros.AnimalEntity;
import controlvet.rest.api.entity.cadastros.ProdutoEntity;
import controlvet.rest.api.entity.cadastros.TutorEntity;
import controlvet.rest.api.enums.EspecieEnum;
import controlvet.rest.api.enums.SexoEnum;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.helper.NumberManager;
import controlvet.rest.api.helper.StringManager;
import controlvet.rest.api.helper.csvmanager.CsvGenerator;
import controlvet.rest.api.repository.atendimentos.consulta.ConsultaRepository;
import controlvet.rest.api.repository.atendimentos.consulta.ItensConsultaRepository;
import controlvet.rest.api.repository.cadastros.AnimalRepository;
import controlvet.rest.api.repository.cadastros.ProdutoRepository;
import controlvet.rest.api.service.cadastros.ProdutoService;
import controlvet.rest.api.vo.atendimentos.consulta.ItensConsultaVo;
import controlvet.rest.api.vo.cadastros.AnimalVo;
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
public class ItensConsultaService {

    @Autowired
    private ItensConsultaRepository repository;

    @Autowired
    private ProdutoService produtoService;

    public List<ItensConsultaVo> findByIdConsulta(Integer idConsulta) throws ValidationException {
        return this.repository.findByIdConsulta(idConsulta);
    }

    public void save(ConsultaEntity entityConsulta,
                     List<ItensConsultaDto> itensConsultaDto) throws Exception {
        deleteAllItens(entityConsulta);

        for (ItensConsultaDto itensConsulta : itensConsultaDto) {
            ItensConsultaEntity entity = new ItensConsultaEntity();
            entity.setQuantidade(itensConsulta.getQuantidade());
            entity.setValorUnitario(itensConsulta.getValorUnitario());
            ProdutoEntity entityProduto = this.produtoService.findById(itensConsulta.getIdProduto());
            entity.setProduto(entityProduto);
            entity.setConsulta(entityConsulta);

            this.repository.save(entity);
        }
    }

    public Boolean isUpdate(AnimalDto dto) {
        return (dto.getId() != null && !dto.getId().equals(0));
    }

    public void validarDados(List<ItensConsultaDto> itensConsultaDto) throws ValidationException {
        for (ItensConsultaDto item : itensConsultaDto) {
            if ((StringManager.getInstance().isNullOrEmpty(item.getDescricaoProduto()) ||
                (NumberManager.getInstance().isNullOrZero(item.getIdProduto())))) {
                throw new ValidationException("Itens foram informados sem descrição ou ID.");
            }

            if (NumberManager.getInstance().isNullOrZero(item.getQuantidade())) {
                throw new ValidationException("Existem item sem quantidade definida.");
            }

            if (NumberManager.getInstance().isNullOrZero(item.getValorUnitario())) {
                throw new ValidationException("Existem item sem valor definido.");
            }
        }
    }

    public void deleteAllItens(ConsultaEntity entityConsulta) {
        List<ItensConsultaEntity> list = this.repository.findByItensConsultaByIdConsulta(entityConsulta.getId());
        for (ItensConsultaEntity item : list) {
            this.repository.delete(item);
        }
    }

    public ExportarCsvVo generateCsv(Integer idConsutla) throws ValidationException, IOException {
        List<ItensConsultaVo> listaDados = this.repository.findByIdConsulta(idConsutla);

        if (listaDados.isEmpty()) {
            throw new ValidationException("Não existem dados para imprimir");
        }
        if (!listaDados.isEmpty()) {
            ExportarCsvVo exportarCsv = new ExportarCsvVo();

            String caminhoArquivo = new CsvGenerator().generateCSV(listaDados, "\"Relatório Animais\"");

            Path path = Paths.get(caminhoArquivo);
            exportarCsv.setArquivo(Files.readAllBytes(path));
            exportarCsv.setNomeArquivo(path.getFileName().toString());

            return exportarCsv;
        } else {
            throw new ValidationException("Não existem dados para imprimir");
        }
    }

    public List<ProdutoEntity> getProdutosVendidos(Date dataInicial, Date dataFinal) {
        return this.repository.getProdutosConsulta(dataInicial, dataFinal);
    }

    public Integer getNumeroVendas(Integer codigoProduto, Date dataInicial, Date dataFinal) {
        return this.repository.getNumeroVendasPorProduto(codigoProduto, dataInicial, dataFinal);
    }

    public Double getFaturamentoBrutoProdutosVendidos(Integer codigoProduto, Date dataInicial, Date dataFinal) {
        return this.repository.getValorFaturamentoBrutoPorProduto(codigoProduto, dataInicial, dataFinal);
    }
}
