package controlvet.rest.api.entity.cadastros;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "cadprocedimentos")
public class ProcedimentoEntity {

    @Id
    @Column(name = "idcadprocedimento")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public Integer id;

    @Column(name = "dcr_procedimento")
    public String descricao;

    @Column(name = "hor_duracao")
    public Time duracao;

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
    public Time getDuracao() { return duracao; }
    public void setDuracao(Time duracao) { this.duracao = duracao; }
    public Date getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Date dataCadastro) { this.dataCadastro = dataCadastro; }
    public Date getDataDesativacao() { return dataDesativacao; }
    public void setDataDesativacao(Date dataDesativacao) { this.dataDesativacao = dataDesativacao; }
}
