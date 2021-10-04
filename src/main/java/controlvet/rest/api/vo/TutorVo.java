package controlvet.rest.api.vo;

import controlvet.rest.api.entity.TutorEntity;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.helper.csvmanager.Classes;
import controlvet.rest.api.helper.csvmanager.CsvInterface;

import java.util.List;

public class TutorVo {

    @CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
    private Integer id;
    @CsvInterface(colClass = Classes.STRING, property = "nome", header = "\"Nome\"")
    private String nome;
    @CsvInterface(colClass = Classes.STRING, property = "telefone", header = "\"Telefone\"")
    private String telefone;
    @CsvInterface(colClass = Classes.STRING, property = "email", header = "\"E-Mail\"")
    private String email;
    @CsvInterface(colClass = Classes.STRING, property = "cpf", header = "\"CPF\"")
    private String cpf;
    @CsvInterface(colClass = Classes.STRING, property = "endereco", header = "\"Endereço\"")
    private String endereco;
    @CsvInterface(colClass = Classes.STRING, property = "dataNascimento", header = "\"Data Nascimento\"")
    private String dataNascimento;
    @CsvInterface(colClass = Classes.STRING, property = "dataCadastro", header = "\"Data Cadastro\"")
    private String dataCadastro;
    private String dataDesativacao;
    private List<AnimalVo> animalVo;
    private Integer idMaxItem;

    public TutorVo(TutorEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.telefone = entity.getTelefone();
        this.email = entity.getEmail();
        this.cpf = entity.getCpf();
        this.endereco = entity.getEndereco();
        this.dataDesativacao = entity.getDataDesativacao() == null ? null : DataManager.getInstance().formatDate(entity.getDataDesativacao());
        this.dataCadastro = entity.getDataCadastro() == null ? null : DataManager.getInstance().formatDate(entity.getDataCadastro());
        this.dataNascimento = entity.getDataNascimento() == null ? null : DataManager.getInstance().formatDate(entity.getDataNascimento());
    }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(String dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }

    public List<AnimalVo> getAnimalVo() {
        return animalVo;
    }

    public void setAnimalVo(List<AnimalVo> animalVo) {
        this.animalVo = animalVo;
    }

    public Integer getIdMaxItem() {
        return idMaxItem;
    }

    public void setIdMaxItem(Integer idMaxItem) {
        this.idMaxItem = idMaxItem;
    }
}