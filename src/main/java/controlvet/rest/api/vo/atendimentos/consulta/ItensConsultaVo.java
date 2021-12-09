package controlvet.rest.api.vo.atendimentos.consulta;

import controlvet.rest.api.entity.atendimentos.consulta.ItensConsultaEntity;
import controlvet.rest.api.helper.NumberManager;

public class ItensConsultaVo {

    private Integer id;
    private String quantidade;
    private String valorUnitario;
    private String descricaoProduto;
    private Integer idProduto;

    public ItensConsultaVo (ItensConsultaEntity entity) throws Exception {
        this.id = entity.getId();
        this.quantidade = entity.getQuantidade().toString();
        this.valorUnitario = NumberManager.getInstance().formatCurrencyValue(entity.getValorUnitario(), "#,###.00");
        if (entity.getProduto() == null) {
            this.descricaoProduto = null;
            this.idProduto = null;
        } else {
            this.idProduto = entity.getProduto().getId();
            this.descricaoProduto = entity.getProduto().getDescricao();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }
}
