package controlvet.rest.api.vo.atendimentos.consulta;

import controlvet.rest.api.entity.atendimentos.consulta.ConsultaEntity;
import controlvet.rest.api.vo.atendimentos.AgendamentoVo;
import controlvet.rest.api.vo.cadastros.AnimalVo;

import java.util.List;

public class ConsultaVo {

    private Integer id;
    private String Anamnese;
    private String Tratamento;
    private String Prescricao;
    private Double valorTotal;
    private Integer idAgendamento;
    private Integer idMaxItem;
    private List<ItensConsultaVo> ItemConsultaVo;


    public ConsultaVo (ConsultaEntity entity) throws Exception {
        this.id = entity.getId();
        this.Anamnese = entity.getAnamnese();
        this.Tratamento = entity.getTratamento();
        this.Prescricao = entity.getPrescricao();
        this.valorTotal = entity.getValorTotal();
        if (entity.getAgendamento() == null) {
            this.idAgendamento = null;
        } else {
            this.idAgendamento = entity.getAgendamento().id;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnamnese() {
        return Anamnese;
    }

    public void setAnamnese(String anamnese) {
        Anamnese = anamnese;
    }

    public String getTratamento() {
        return Tratamento;
    }

    public void setTratamento(String tratamento) {
        Tratamento = tratamento;
    }

    public String getPrescricao() {
        return Prescricao;
    }

    public void setPrescricao(String prescricao) {
        Prescricao = prescricao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Integer idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public Integer getIdMaxItem() {
        return idMaxItem;
    }

    public void setIdMaxItem(Integer idMaxItem) {
        this.idMaxItem = idMaxItem;
    }

    public List<ItensConsultaVo> getItemConsultaVo() {
        return ItemConsultaVo;
    }

    public void setItemConsultaVo(List<ItensConsultaVo> itemConsultaVo) {
        ItemConsultaVo = itemConsultaVo;
    }
}
