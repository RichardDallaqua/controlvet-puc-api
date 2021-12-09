package controlvet.rest.api.entity.atendimentos.consulta;

import controlvet.rest.api.entity.cadastros.ProdutoEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "itensconsultas")
public class ItensConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "iditensconsultas")
    private Integer id;

    @Column(name = "qtd_produto")
    private Integer quantidade;

    @Column(name = "val_unitario")
    private Double valorUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcadprodutos")
    private ProdutoEntity produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcadconsultas")
    private ConsultaEntity consulta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }

    public ConsultaEntity getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaEntity consulta) {
        this.consulta = consulta;
    }
}
