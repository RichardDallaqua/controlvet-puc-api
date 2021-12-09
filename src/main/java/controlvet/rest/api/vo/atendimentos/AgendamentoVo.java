package controlvet.rest.api.vo.atendimentos;

import controlvet.rest.api.entity.atendimentos.AgendamentoEntity;
import controlvet.rest.api.entity.atendimentos.consulta.ConsultaEntity;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.helper.StringManager;
import controlvet.rest.api.helper.csvmanager.Classes;
import controlvet.rest.api.helper.csvmanager.CsvInterface;
import controlvet.rest.api.service.atendimentos.AgendamentoService;
import controlvet.rest.api.service.atendimentos.consulta.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;

public class AgendamentoVo {

    private Integer id;
    @CsvInterface(colClass = Classes.STRING, property = "dataAgendamento", header = "\"Data\"")
    private String dataAgendamento;
    @CsvInterface(colClass = Classes.STRING, property = "horaAgendamento", header = "\"Hora\"")
    private String horaAgendamento;
    private String dataCadastro;
    private Integer idTutor;
    @CsvInterface(colClass = Classes.STRING, property = "nomeTutor", header = "\"Tutor\"")
    private String nomeTutor;
    private Integer idAnimal;
    @CsvInterface(colClass = Classes.STRING, property = "nomeAnimal", header = "\"Animal\"")
    private String nomeAnimal;
    private Integer idProcedimento;
    private String descricaoProcedimento;
    private Integer idSala;
    private String descricaoSala;
    private Integer idUsuario;
    private String nomeUsuario;
    @CsvInterface(colClass = Classes.STRING, property = "observacao", header = "\"Obs\"")
    private String observacao;
    private String atendido;

    public AgendamentoVo (AgendamentoEntity entity) throws Exception {
        this.id = entity.getId();
        this.dataAgendamento = DataManager.getInstance().formatDate(entity.getDataAgendamento());
        this.horaAgendamento = DataManager.getInstance().formatDate(entity.getHoraAgendamento());
        this.dataCadastro = DataManager.getInstance().formatDate(entity.getDataCadastro());
        this.observacao = entity.getObservacao();

        if (entity.getTutor() != null) {
            this.idTutor = entity.getTutor().getId();
            this.nomeTutor = entity.getTutor().getNome();
        } else {
            this.idTutor = null;
            this.nomeTutor = null;
        }

        if (entity.getAnimal() != null) {
            this.idAnimal = entity.getAnimal().getId();
            this.nomeAnimal = entity.getAnimal().getNome();
        } else {
            this.idAnimal = null;
            this.nomeAnimal = null;
        }

        if (entity.getProcedimento() != null) {
            this.idProcedimento = entity.getProcedimento().getId();
            this.descricaoProcedimento = entity.getProcedimento().getDescricao();
        } else {
            this.idProcedimento = null;
            this.descricaoProcedimento = null;
        }

        if (entity.getSala() != null) {
            this.idSala = entity.getSala().getId();
            this.descricaoSala = entity.getSala().getDescricao();
        } else {
            this.idSala = null;
            this.descricaoSala = null;
        }

        if (entity.getUsuario() != null) {
            this.idUsuario = entity.getUsuario().getId();
            this.nomeUsuario = entity.getUsuario().getNome();
        } else {
            this.idSala = null;
            this.nomeUsuario = null;
        }

        this.atendido = entity.getAtendido();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getHoraAgendamento() {
        return horaAgendamento;
    }

    public void setHoraAgendamento(String horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public String getNomeTutor() {
        return nomeTutor;
    }

    public void setNomeTutor(String nomeTutor) {
        this.nomeTutor = nomeTutor;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public Integer getIdProcedimento() {
        return idProcedimento;
    }

    public void setIdProcedimento(Integer idProcedimento) {
        this.idProcedimento = idProcedimento;
    }

    public String getDescricaoProcedimento() {
        return descricaoProcedimento;
    }

    public void setDescricaoProcedimento(String descricaoProcedimento) {
        this.descricaoProcedimento = descricaoProcedimento;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public String getDescricaoSala() {
        return descricaoSala;
    }

    public void setDescricaoSala(String descricaoSala) {
        this.descricaoSala = descricaoSala;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getAtendido() {
        return atendido;
    }

    public void setAtendido(String atendido) {
        this.atendido = atendido;
    }
}