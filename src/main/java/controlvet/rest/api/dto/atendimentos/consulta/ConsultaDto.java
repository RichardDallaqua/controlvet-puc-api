package controlvet.rest.api.dto.atendimentos.consulta;

import controlvet.rest.api.dto.cadastros.AnimalDto;

import java.util.List;

public class ConsultaDto {

    private Integer id;
    private String Anamnese;
    private String Tratamento;
    private String Prescricao;
    private Double valorTotal;
    private Integer idAgendamento;
    private List<ItensConsultaDto> itensConsultaDto;

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

    public List<ItensConsultaDto> getItensConsultaDto() {
        return itensConsultaDto;
    }

    public void setItensConsultaDto(List<ItensConsultaDto> itensConsultaDto) {
        this.itensConsultaDto = itensConsultaDto;
    }
}
