package controlvet.rest.api.service.atendimentos.consulta;

import controlvet.rest.api.dto.atendimentos.consulta.ConsultaDto;
import controlvet.rest.api.dto.cadastros.TutorDto;
import controlvet.rest.api.entity.atendimentos.AgendamentoEntity;
import controlvet.rest.api.entity.atendimentos.consulta.ConsultaEntity;
import controlvet.rest.api.entity.cadastros.ProdutoEntity;
import controlvet.rest.api.entity.cadastros.TutorEntity;
import controlvet.rest.api.helper.BooleanManager;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.helper.StringManager;
import controlvet.rest.api.helper.csvmanager.CsvGenerator;
import controlvet.rest.api.repository.atendimentos.consulta.ConsultaRepository;
import controlvet.rest.api.service.atendimentos.AgendamentoService;
import controlvet.rest.api.service.cadastros.AnimalService;
import controlvet.rest.api.vo.atendimentos.AgendamentoVo;
import controlvet.rest.api.vo.atendimentos.consulta.ConsultaVo;
import controlvet.rest.api.vo.atendimentos.consulta.ItensConsultaVo;
import controlvet.rest.api.vo.cadastros.AnimalVo;
import controlvet.rest.api.vo.cadastros.TutorVo;
import controlvet.rest.api.vo.generic.ExportarCsvVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private ItensConsultaService itensConsultaService;

    @Autowired
    private AgendamentoService agendamentoService;

    public List<ConsultaVo> findByFiltros(String data) throws Exception {
        if (StringManager.getInstance().isNullOrEmpty(data)) {

            data = DataManager.getInstance().formatDate(Calendar.getInstance().getTime());
        }

        List<ConsultaVo> list = this.repository.findByFiltros(DataManager.getInstance().stringToDate(data));
        Integer idMaxItem = 1;
        for (ConsultaVo consultaVo : list) {
            consultaVo.setItemConsultaVo(this.itensConsultaService.findByIdConsulta(consultaVo.getId()));
            for (ItensConsultaVo item : consultaVo.getItemConsultaVo()) {
                idMaxItem = item.getId();
            }
            consultaVo.setIdMaxItem(idMaxItem);
        }

        return list;
    }

    public ConsultaVo findByIdAgendamento(Integer id) throws Exception {
        ConsultaVo consultaVo = new ConsultaVo(this.repository.findByagendamento(id));

        Integer idMaxItem = 1;
        consultaVo.setItemConsultaVo(this.itensConsultaService.findByIdConsulta(consultaVo.getId()));
        for (ItensConsultaVo animal : consultaVo.getItemConsultaVo()) {
            idMaxItem = animal.getId();
        }
        consultaVo.setIdMaxItem(idMaxItem);
        return consultaVo;
    }

    public Boolean consultaEfetuada(Integer id) throws Exception {
        return (this.repository.findByagendamento(id) != null);
    }

    public ConsultaEntity save(ConsultaDto dto) throws Exception {
        validarDados(dto);

        ConsultaEntity entity = new ConsultaEntity();
        if (dto.getId() != null && !dto.getId().equals(0)) {
            entity = this.repository.findById(dto.getId()).get();
        }
        AgendamentoEntity agendamento = this.agendamentoService.findById(dto.getIdAgendamento());
        agendamento.setAtendido(BooleanManager.getInstance().parseString(Boolean.TRUE));
        entity.setAgendamento(agendamento);
        entity.setAnamnese(dto.getAnamnese());
        entity.setPrescricao(dto.getPrescricao());
        entity.setTratamento(dto.getTratamento());
        entity.setValorTotal(dto.getValorTotal());

        this.repository.save(entity);

        if (dto.getItensConsultaDto() != null && !dto.getItensConsultaDto().isEmpty()) {
            this.itensConsultaService.save(entity, dto.getItensConsultaDto());
        } else {
            this.itensConsultaService.deleteAllItens(entity);
        }

        return entity;
    }

    private void validarDados(ConsultaDto dto) throws ValidationException {

        if (dto == null) {
            throw new ValidationException("Consulta inválida.");
        }

        if (StringManager.getInstance().isNullOrEmpty(dto.getAnamnese())) {
            throw new ValidationException("Informe uma anamnese.");
        }

        if (StringManager.getInstance().isNullOrEmpty(dto.getTratamento())) {
            throw new ValidationException("Informe um tratamento.");
        }

        if (StringManager.getInstance().isNullOrEmpty(dto.getPrescricao())) {
            throw new ValidationException("Informe uma prescrição.");
        }

        if (dto.getItensConsultaDto() != null && !dto.getItensConsultaDto().isEmpty()) {
            this.itensConsultaService.validarDados(dto.getItensConsultaDto());
        }
    }

    public ExportarCsvVo generateCsv(String data) throws Exception {

        ExportarCsvVo exportarCsv = new ExportarCsvVo();

        if (StringManager.getInstance().isNullOrEmpty(data)) {
            data = DataManager.getInstance().formatDate(Calendar.getInstance().getTime());
        }

        List<AgendamentoVo> listaDados = this.agendamentoService.findByFiltros(data);

        if (!listaDados.isEmpty()) {
            String caminhoArquivo = new CsvGenerator().generateCSV(listaDados, "\"Relatório de Consulta\"");

            Path path = Paths.get(caminhoArquivo);
            exportarCsv.setArquivo(Files.readAllBytes(path));
            exportarCsv.setNomeArquivo(path.getFileName().toString());
        } else {
            throw new ValidationException("Não existem dados para imprimir");
        }
        return exportarCsv;
    }

}
