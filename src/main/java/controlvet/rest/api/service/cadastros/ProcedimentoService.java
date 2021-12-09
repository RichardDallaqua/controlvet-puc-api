package controlvet.rest.api.service.cadastros;

import controlvet.rest.api.dto.cadastros.ProcedimentoDto;
import controlvet.rest.api.entity.cadastros.ProcedimentoEntity;
import controlvet.rest.api.helper.StringManager;
import controlvet.rest.api.helper.csvmanager.CsvGenerator;
import controlvet.rest.api.repository.cadastros.ProcedimentoRepository;
import controlvet.rest.api.vo.cadastros.ProcedimentoVo;
import controlvet.rest.api.vo.generic.ExportarCsvVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProcedimentoService {

    @Autowired
    private ProcedimentoRepository repository;

    @Autowired
    private ProcedimentoService procedimentoService;

    public ProcedimentoEntity findById(Integer id) throws ValidationException {
        return this.repository.findById(id).orElseThrow(() -> new ValidationException("Usuário não encontrado"));
    }

    public List<ProcedimentoVo> findByFiltros(String descricao, Boolean listarInativos) throws ValidationException {
        return this.repository.findByFiltros(descricao, Boolean.TRUE.equals(listarInativos) ? 1 : 0);
    }

    public ProcedimentoEntity save(ProcedimentoDto dto) throws ValidationException, ParseException {
        ProcedimentoEntity entity = new ProcedimentoEntity();

        validarDados(dto);

        if (isUpdate(dto)) {
            entity = this.repository.findById(dto.getId()).get();
        } else {
            entity.setDataCadastro(new Date());
        }
        entity.setDescricao(dto.getDescricao());
        entity.setDuracao(TratarHora(dto.getDuracao()));

        return repository.save(entity);
    }

    public ProcedimentoEntity disableOrEnableById(Integer id) throws ValidationException {
        ProcedimentoEntity entity = new ProcedimentoEntity();
        entity = this.repository.findById(id).get();
        entity.setDataDesativacao(entity.getDataDesativacao()== null ? new Date() : null );

        return repository.save(entity);
    }

    public Boolean isUpdate(ProcedimentoDto dto) {
        return (dto.getId() != null && !dto.getId().equals(0));
    }

    public ExportarCsvVo generateCsv(String descricao, Boolean listarInativos) throws ValidationException, IOException {

        ExportarCsvVo exportarCsv = new ExportarCsvVo();

        List<ProcedimentoVo> listaDados = this.repository.findByFiltros(descricao, Boolean.TRUE.equals(listarInativos) ? 1 : 0);

        if (!listaDados.isEmpty()) {
            String caminhoArquivo = new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Usuário\"");

            Path path = Paths.get(caminhoArquivo);
            exportarCsv.setArquivo(Files.readAllBytes(path));
            exportarCsv.setNomeArquivo(path.getFileName().toString());
        } else {
            throw new ValidationException("Não existem dados para imprimir");
        }
        return exportarCsv;
    }

    private boolean validarDados (ProcedimentoDto dto) throws ValidationException {
        StringManager sm = new StringManager();
        if (dto == null) {
            throw new ValidationException("Informações invalidas");
        }
        if (sm.isNullOrEmpty(dto.getDescricao())) {
            throw new ValidationException("Valor informado para a descrição é inválido.");
        }

        if (sm.isNullOrEmpty(dto.getDuracao())) {
            throw new ValidationException("Valor informado para a duração é inválido.");
        }

        ProcedimentoEntity procedimento = this.repository.findBydescricao(dto.getDescricao());

        if (procedimento != null && procedimento.getDescricao().equals(dto.getDescricao()) &&
            !procedimento.getId().equals(dto.getId())) {
            throw new ValidationException("Já existe um procedimento com essa descrição.");
        }

        return true;
    }

    public Time TratarHora(String horaString) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        Date date = formatter.parse(horaString + ":00");
        return new Time(date.getTime());
    }
}
