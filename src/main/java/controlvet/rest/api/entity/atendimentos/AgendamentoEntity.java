package controlvet.rest.api.entity.atendimentos;

import controlvet.rest.api.entity.cadastros.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "cadagendamentos")
public class AgendamentoEntity {

    @Id
    @Column(name = "idcadagendamentos")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "dat_agendamento")
    public Date dataAgendamento;

    @Column(name = "hor_agendamento")
    public Time horaAgendamento;

    @Column(name = "dcr_observacao")
    public String observacao;

    @Column(name = "dat_cadastro")
    public Date dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcadtutores")
    public TutorEntity tutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcadanimais")
    public AnimalEntity animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcadprocedimentos")
    public ProcedimentoEntity procedimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcadsalas")
    public SalaEntity sala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcadusuarios")
    public UsuarioEntity usuario;

    @Column(name = "flb_atendido")
    public String atendido;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Time getHoraAgendamento() {
        return horaAgendamento;
    }

    public void setHoraAgendamento(Time horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public TutorEntity getTutor() {
        return tutor;
    }

    public void setTutor(TutorEntity tutor) {
        this.tutor = tutor;
    }

    public AnimalEntity getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalEntity animal) {
        this.animal = animal;
    }

    public ProcedimentoEntity getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(ProcedimentoEntity procedimento) {
        this.procedimento = procedimento;
    }

    public SalaEntity getSala() {
        return sala;
    }

    public void setSala(SalaEntity sala) {
        this.sala = sala;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public String getAtendido() {
        return atendido;
    }

    public void setAtendido(String atendido) {
        this.atendido = atendido;
    }
}
