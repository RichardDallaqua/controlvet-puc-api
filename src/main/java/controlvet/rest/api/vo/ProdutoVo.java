package controlvet.rest.api.vo;

import controlvet.rest.api.entity.ProdutoEntity;
import controlvet.rest.api.entity.UsuarioEntity;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.helper.NumberManager;
import controlvet.rest.api.helper.csvmanager.Classes;
import controlvet.rest.api.helper.csvmanager.CsvInterface;

import java.text.DecimalFormat;

public class ProdutoVo {
    
    @CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
    private Integer id;
    @CsvInterface(colClass = Classes.STRING, property = "descricao", header = "\"Descrição\"")
    private String descricao;
    @CsvInterface(colClass = Classes.STRING, property = "valor", header = "\"Valor\"")
    private String valor;
    @CsvInterface(colClass = Classes.STRING, property = "dataCadastro", header = "\"Data Cadastro\"")
    private String dataCadastro;
    private String dataDesativacao;

    public ProdutoVo (ProdutoEntity entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.valor = NumberManager.getInstance().formatCurrencyValue(entity.getValor(), "#,###.00");
        this.dataCadastro = entity.getDataCadastro() == null ? null : DataManager.getInstance().formatDate(entity.getDataCadastro());
        this.dataDesativacao = entity.getDataDesativacao() == null ? null : DataManager.getInstance().formatDate(entity.getDataDesativacao());
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }
    public String getDataDesativacao() {
        return dataDesativacao;
    }
    public void setDataDesativacao(String dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }
    public String getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(String dataCadastro) { this.dataCadastro = dataCadastro; }
}
