package controlvet.rest.api.entity.atendimentos.consulta;

import controlvet.rest.api.entity.atendimentos.AgendamentoEntity;
import controlvet.rest.api.entity.cadastros.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "cadconsultas")
public class ConsultaEntity {
    @Id
    @Column(name = "idcadconsultas")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public Integer id;

    @Column(name = "dcr_anamnese")
    public String anamnese;

    @Column(name = "dcr_tratamento")
    public String tratamento;

    @Column(name = "dcr_prescricao")
    public String prescricao;

    @Column(name = "val_total")
    public Double valorTotal;

    @OneToOne
    @JoinColumn(name = "idcadagendamentos")
    public AgendamentoEntity agendamento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(String anamnese) {
        this.anamnese = anamnese;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public AgendamentoEntity getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(AgendamentoEntity agendamento) {
        this.agendamento = agendamento;
    }
}
