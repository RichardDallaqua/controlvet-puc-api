package controlvet.rest.api.entity.cadastros;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cadprodutos")
public class ProdutoEntity {
    
    @Id
    @Column(name = "idcadprodutos")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public Integer id;

    @Column(name = "dcr_produto")
    public String descricao;

    @Column(name = "val_produto")
    public Double valor;

    @Column(name = "dat_cadastro")
    public Date dataCadastro;

    @Column(name = "dat_desativacao")
    public Date dataDesativacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Date getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(Date dataDesativacao) { this.dataDesativacao = dataDesativacao; }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Double getValor() { return valor; }

    public void setValor(Double valor) { this.valor = valor; }
}
