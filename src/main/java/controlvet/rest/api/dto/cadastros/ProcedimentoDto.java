package controlvet.rest.api.dto.cadastros;

public class ProcedimentoDto {

    private Integer id;
    private String descricao;
    private String duracao;
    private String dataCadastro;
    private String dataDesativacao;

    public ProcedimentoDto () {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getDuracao() { return duracao; }
    public void setDuracao(String duracao) { this.duracao = duracao; }
    public String getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(String dataCadastro) { this.dataCadastro = dataCadastro; }
    public String getDataDesativacao() { return dataDesativacao; }
    public void setDataDesativacao(String dataDesativacao) { this.dataDesativacao = dataDesativacao; }
}
