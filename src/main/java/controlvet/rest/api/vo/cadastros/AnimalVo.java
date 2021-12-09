package controlvet.rest.api.vo.cadastros;

import controlvet.rest.api.entity.cadastros.AnimalEntity;
import controlvet.rest.api.enums.EspecieEnum;
import controlvet.rest.api.enums.SexoEnum;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.helper.NumberManager;
import controlvet.rest.api.helper.csvmanager.Classes;
import controlvet.rest.api.helper.csvmanager.CsvInterface;

public class AnimalVo {

    @CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
    private Integer id;
    @CsvInterface(colClass = Classes.STRING, property = "nome", header = "\"Nome\"")
    private String nome;
    @CsvInterface(colClass = Classes.STRING, property = "raca", header = "\"Raça\"")
    private String raca;
    private Integer especie;
    @CsvInterface(colClass = Classes.STRING, property = "descricaoEspecie", header = "\"Espécie\"")
    private String descricaoEspecie;
    private Integer sexo;
    @CsvInterface(colClass = Classes.STRING, property = "descricaoSexo", header = "\"Sexo\"")
    private String descricaoSexo;
    @CsvInterface(colClass = Classes.STRING, property = "peso", header = "\"Peso\"")
    private String peso;
    private String dataNascimento;
    private String dataDesativacao;
    private String dataCadastro;
    private Integer idTutor;
    private String tutor;

    public AnimalVo(AnimalEntity entity) {

        this.id = entity.getId();
        this.nome = entity.getNome();
        this.raca = entity.getRaca();
        this.especie = entity.getEspecie();
        this.descricaoSexo = EspecieEnum.valueOf(this.especie).getText();
        this.sexo = entity.getSexo();
        this.descricaoSexo = SexoEnum.valueOf(this.sexo).getText();
        this.peso = NumberManager.getInstance().formatWeightValue(entity.getPeso(), "#,###.000");
        this.dataNascimento = entity.getDataNascimento() == null ? null : DataManager.getInstance().formatDate(entity.getDataNascimento());
        this.dataDesativacao = entity.getDataDesativacao() == null ? null : DataManager.getInstance().formatDate(entity.getDataDesativacao());
        this.dataCadastro = entity.getDataCadastro() == null ? null : DataManager.getInstance().formatDate(entity.getDataCadastro());

        if (entity.getTutor() == null) {
            this.idTutor = null;
            this.tutor = null;
        } else {
            this.idTutor = entity.getTutor().getId();
            this.tutor = entity.getTutor().getNome();
        }
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

    public String getDescricaoEspecie() {
        return descricaoEspecie;
    }

    public void setDescricaoEspecie(String descricaoEspecie) {
        this.descricaoEspecie = descricaoEspecie;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public String getDescricaoSexo() {
        return descricaoSexo;
    }

    public void setDescricaoSexo(String descricaoSexo) {
        this.descricaoSexo = descricaoSexo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(String dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }
}
