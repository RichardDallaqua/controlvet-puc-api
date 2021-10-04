package controlvet.rest.api.service;

import controlvet.rest.api.dto.SalaDto;
import controlvet.rest.api.entity.SalaEntity;
import controlvet.rest.api.helper.StringManager;
import controlvet.rest.api.helper.csvmanager.CsvGenerator;
import controlvet.rest.api.repository.SalaRepository;
import controlvet.rest.api.vo.SalaVo;
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
public class SalaService {

    @Autowired
    private SalaRepository repository;

    @Autowired
    private SalaService salaService;

    public SalaEntity findById(Integer id) throws ValidationException {
        return this.repository.findById(id).orElseThrow(() -> new ValidationException("Usuário não encontrado"));
    }

    public List<SalaVo> findByFiltros(String descricao, Boolean listarInativos) throws ValidationException {
        return this.repository.findByFiltros(descricao, Boolean.TRUE.equals(listarInativos) ? 1 : 0);
    }

    public SalaEntity save(SalaDto dto) throws ValidationException, ParseException {
        SalaEntity entity = new SalaEntity();

        validarDados(dto);

        if (isUpdate(dto)) {
            entity = this.repository.findById(dto.getId()).get();
        } else {
            entity.setDataCadastro(new Date());
        }
        entity.setDescricao(dto.getDescricao());

        return repository.save(entity);
    }

    public SalaEntity disableOrEnableById(Integer id) throws ValidationException {
        SalaEntity entity = new SalaEntity();
        entity = this.repository.findById(id).get();
        entity.setDataDesativacao(entity.getDataDesativacao()== null ? new Date() : null );

        return repository.save(entity);
    }

    public Boolean isUpdate(SalaDto dto) {
        return (dto.getId() != null && !dto.getId().equals(0));
    }

    public ExportarCsvVo generateCsv(String descricao, Boolean listarInativos) throws ValidationException, IOException {

        ExportarCsvVo exportarCsv = new ExportarCsvVo();

        List<SalaVo> listaDados = this.repository.findByFiltros(descricao, Boolean.TRUE.equals(listarInativos) ? 1 : 0);

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

    private boolean validarDados (SalaDto dto) throws ValidationException {
        StringManager sm = new StringManager();

        if (dto == null) {
            throw new ValidationException("Informações invalidas");
        }

        SalaEntity sala = this.repository.findBydescricao(dto.getDescricao());

        if (sala != null && sala.getDescricao().equals(dto.getDescricao()) && !sala.getId().equals(dto.getId())) {
            throw new ValidationException("Já existe uma sala com essa descrição.");
        }

        if (sm.isNullOrEmpty(dto.getDescricao())) {
            throw new ValidationException("Valor informado para a descrição é inválido.");
        }

        return true;
    }

    public Time TratarHora(String horaString) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        Date date = formatter.parse(horaString + ":00");
        return new Time(date.getTime());
    }
}