package controlvet.rest.api.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cadanimais")
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idcadanimais")
    private Integer id;

    @Column(name = "dcr_nome")
    private String nome;

    @Column(name = "dcr_raca")
    private String raca;

    @Column(name = "flg_especie")
    private Integer especie;

    @Column(name = "flg_sexo")
    private Integer sexo;

    @Column(name = "val_peso")
    private Double peso;

    @Column(name = "dat_nascimento")
    private Date dataNascimento;

    @Column(name = "dat_desativacao")
    private Date dataDesativacao;

    @Column(name = "dat_cadastro")
    private Date dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcadtutores")
    private TutorEntity tutor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Integer getEspecie() {
        return especie;
    }

    public void setEspecie(Integer especie) {
        this.especie = especie;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(Date dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public TutorEntity getTutor() {
        return tutor;
    }

    public void setTutor(TutorEntity tutor) {
        this.tutor = tutor;
    }
}