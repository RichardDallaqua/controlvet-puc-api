package controlvet.rest.api.dto;

public class ProdutoDto {

    private Integer id;
    private String descricao;
    private Double valor;
    private String dataCadastro;
    private String dataDesativacao;

    public ProdutoDto() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public String getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(String dataCadastro) { this.dataCadastro = dataCadastro; }
    public String getDataDesativacao() { return dataDesativacao; }
    public void setDataDesativacao(String dataDesativacao) { this.dataDesativacao = dataDesativacao; }
}
