package controlvet.rest.api.service.atendimentos;

import controlvet.rest.api.dto.atendimentos.AgendamentoDto;
import controlvet.rest.api.entity.atendimentos.AgendamentoEntity;
import controlvet.rest.api.entity.cadastros.ProcedimentoEntity;
import controlvet.rest.api.entity.cadastros.SalaEntity;
import controlvet.rest.api.helper.BooleanManager;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.helper.NumberManager;
import controlvet.rest.api.helper.StringManager;
import controlvet.rest.api.helper.csvmanager.CsvGenerator;
import controlvet.rest.api.repository.atendimentos.AgendamentoRepository;
import controlvet.rest.api.service.cadastros.*;
import controlvet.rest.api.vo.atendimentos.AgendamentoVo;
import controlvet.rest.api.vo.generic.ExportarCsvVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private TutorService tutorService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private ProcedimentoService procedimentoService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private UsuarioService usuarioService;

    public List<AgendamentoVo> findByFiltros(String data) throws Exception {
        if (StringManager.getInstance().isNullOrEmpty(data)) {
            data = DataManager.getInstance().formatDate(Calendar.getInstance().getTime());
        }
        return this.repository.findByFiltros(DataManager.getInstance().stringToDate(data));
    }

     public AgendamentoEntity save(AgendamentoDto dto) throws Exception {
        AgendamentoEntity entity = new AgendamentoEntity();

        validarDados(dto);

        if (isUpdate(dto)) {
            entity = this.repository.findById(dto.getId()).get();
        } else {
            entity.setDataCadastro(new Date());
            entity.setAtendido(BooleanManager.getInstance().parseString(Boolean.FALSE));
        }
        entity.setDataAgendamento(DataManager.getInstance().stringToDate(dto.getDataAgendamento()));
        entity.setHoraAgendamento(DataManager.getInstance().stringToTime(dto.getHoraAgendamento()));
        entity.setTutor(this.tutorService.findById(dto.getIdTutor()));
        entity.setAnimal(this.animalService.findById(dto.getIdAnimal()));
        entity.setProcedimento(this.procedimentoService.findById(dto.getIdProcedimento()));
        entity.setSala(this.salaService.findById(dto.getIdSala()));
        entity.setUsuario(this.usuarioService.findById(dto.getIdUsuario()));
        entity.setObservacao(dto.getObservacao());

        return repository.save(entity);
    }

    public Boolean delete(Integer id) throws ValidationException {
        AgendamentoEntity entity = new AgendamentoEntity();
        entity = this.repository.findById(id).get();
        if (StringManager.getInstance().parseBoolean(entity.getAtendido()) ==  Boolean.TRUE) {
          return false;
        }
        repository.delete(entity);
        return true;
    }

    public Boolean isUpdate(AgendamentoDto dto) {
        return (dto.getId() != null && !dto.getId().equals(0));
    }

    public ExportarCsvVo generateCsv(String data) throws Exception {

        ExportarCsvVo exportarCsv = new ExportarCsvVo();

        if (StringManager.getInstance().isNullOrEmpty(data)) {
            data = DataManager.getInstance().formatDate(Calendar.getInstance().getTime());
        }

        List<AgendamentoVo> listaDados = this.repository.findByFiltros(DataManager.getInstance().stringToDate(data));

        if (!listaDados.isEmpty()) {
            String caminhoArquivo = new CsvGenerator().generateCSV(listaDados, "\"Relatório de Agendamento\"");

            Path path = Paths.get(caminhoArquivo);
            exportarCsv.setArquivo(Files.readAllBytes(path));
            exportarCsv.setNomeArquivo(path.getFileName().toString());
        } else {
            throw new ValidationException("Não existem dados para imprimir");
        }
        return exportarCsv;
    }

    private boolean validarDados (AgendamentoDto dto) throws Exception {
        DataManager dm = new DataManager();
        NumberManager nm = new NumberManager();
        if (dto == null) {
            throw new ValidationException("Informações invalidas");
        }

        if (!dm.validarData(dto.getDataAgendamento())) {
            throw new ValidationException("Valor informado para data é inválido");
        }

        if (!dm.validarHora(dto.getHoraAgendamento())) {
            throw new ValidationException("Valor informado para hora é inválido");
        }

        if (nm.isNullOrZero(dto.getIdTutor())) {
            throw new ValidationException("Valor informado para o Tutor é inválido.");
        }

        if (nm.isNullOrZero(dto.getIdAnimal())) {
            throw new ValidationException("Valor informado para o Animal é inválido.");
        }

        if (nm.isNullOrZero(dto.getIdProcedimento())) {
            throw new ValidationException("Valor informado para o Procedimento é inválido.");
        }

        if (nm.isNullOrZero(dto.getIdSala())) {
            throw new ValidationException("Valor informado para a Sala é inválido.");
        }

        if (nm.isNullOrZero(dto.getIdUsuario())) {
            throw new ValidationException("Valor informado para o Usuário é inválido.");
        }

        isAgendamentoDisponivel(dto);

        return true;
    }

    public AgendamentoEntity findById(Integer idAgendamento) throws ValidationException {
        return this.repository.findById(idAgendamento).orElseThrow(() -> new ValidationException("Agendamento não encontrado"));
    }

    public List<SalaEntity> getSalasAgendamentos(Date dataInicial, Date dataFinal){
        return this.repository.getSalasAgendamentos(dataInicial, dataFinal);
    }

    public Integer getQuantidadeSalaPorAgendamento(Integer idSala, Date dataInicial, Date dataFinal){
        return this.repository.getQuantidadeSalaPorAgendamento(idSala, dataInicial, dataFinal);
    }

    public List<ProcedimentoEntity> getProcedimentosAgendamentos(Date dataInicial, Date dataFinal){
        return this.repository.getProcedimentosAgendamentos(dataInicial, dataFinal);
    }

    public Integer getQuantidadeProcedimentoPorAgendamento(Integer idProcedimento, Date dataInicial, Date dataFinal){
        return this.repository.getQuantidadeProcedimentoPorAgendamento(idProcedimento, dataInicial, dataFinal);
    }

    public List<AgendamentoEntity> getAgendamentosDoMes(Date dataInicial, Date dataFinal){
        return this.repository.getAgendamentosDoMes(dataInicial, dataFinal);
    }

    public Integer getQuantidadeAgendamentoPorDia(Date dataAgendamento){
        return this.repository.getQuantidadeAgendamentoPorDia(dataAgendamento);
    }

    private Boolean isAgendamentoDisponivel(AgendamentoDto dto) throws Exception {
        DataManager dataManager = DataManager.getInstance();
        Date dataNovoAgendamento = dataManager.stringToDate(dto.getDataAgendamento());
        Time horaNovoAgendamento = dataManager.stringToTime(dto.getHoraAgendamento());

        ProcedimentoEntity procedimento = this.procedimentoService.findById(dto.getIdProcedimento());
        Time duracaoProcedimento = procedimento.getDuracao();

        Date dataInicialNovo = dataManager.addTimeinDate(dataNovoAgendamento, horaNovoAgendamento);
        Date dataFinalNovo = dataManager.addTimeinDate(dataInicialNovo, duracaoProcedimento);

        Time horaInicialNovoAgendamento = dataManager.getTimeofDate(dataInicialNovo);
        Time horaFinalNovoAgendamento = dataManager.getTimeofDate(dataFinalNovo);

        List<AgendamentoEntity> agendamentos = this.repository.getAgendamentosPorDataESala(dto.getIdSala(),
                                                                                           dataNovoAgendamento,
                                                                                           (dto.getId() == null ? 0 : dto.getId()));
        for (AgendamentoEntity agendamento: agendamentos) {

            Date dataInicial = dataManager.addTimeinDate(dataNovoAgendamento, agendamento.getHoraAgendamento());
            Date dataFinal = dataManager.addTimeinDate(dataInicial, agendamento.procedimento.getDuracao());
            Time horaInicial = dataManager.getTimeofDate(dataInicial);
            Time horaFinal = dataManager.getTimeofDate(dataFinal);

            if ((horaInicialNovoAgendamento.after(horaInicial) && horaInicialNovoAgendamento.before(horaFinal)) ||
                (horaFinalNovoAgendamento.after(horaInicial) && horaFinalNovoAgendamento.before(horaFinal)) ||
                (horaInicialNovoAgendamento.equals(horaInicial)))
                throw new ValidationException(
                        "Não foi possivel efetuar agendamento. Já existe um(a) "
                                + agendamento.getProcedimento().getDescricao() + " para as " +
                                dataManager.formatDate(agendamento.getHoraAgendamento()) + " no " +
                                agendamento.getSala().getDescricao());
            }
        return true;
    }
}
