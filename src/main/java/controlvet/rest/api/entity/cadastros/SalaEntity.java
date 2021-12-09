package controlvet.rest.api.entity.cadastros;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cadsalas")
public class SalaEntity {

    @Id
    @Column(name = "idcadsala")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public Integer id;

    @Column(name = "dcr_sala")
    public String descricao;

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
    public Date getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Date dataCadastro) { this.dataCadastro = dataCadastro; }
    public Date getDataDesativacao() { return dataDesativacao; }
    public void setDataDesativacao(Date dataDesativacao) { this.dataDesativacao = dataDesativacao; }
}