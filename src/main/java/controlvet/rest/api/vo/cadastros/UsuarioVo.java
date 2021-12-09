package controlvet.rest.api.vo.cadastros;

import controlvet.rest.api.entity.cadastros.UsuarioEntity;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.helper.csvmanager.Classes;
import controlvet.rest.api.helper.csvmanager.CsvInterface;

public class UsuarioVo {

    @CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
    private Integer id;
    @CsvInterface(colClass = Classes.STRING, property = "nome", header = "\"Nome\"")
    private String nome;
    private String senha;
    @CsvInterface(colClass = Classes.STRING, property = "login", header = "\"Login\"")
    private String login;
    @CsvInterface(colClass = Classes.STRING, property = "dataCadastro", header = "\"Data Cadastro\"")
    private String dataCadastro;
    private String dataDesativacao;
    private Integer idPerfil;
    private String perfil;
    private String acessaUsuario;
    private String acessaPerfil;
    private String acessaConsultas;
    private String acessaAgenda;
    private String acessaSala;
    private String acessaProcedimento;
    private String acessaVeterinario;
    private String acessaTutor;
    private String acessaProduto;

    public UsuarioVo (UsuarioEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.senha = entity.getSenha();
        this.login = entity.getLogin();
        this.dataCadastro = entity.getDataCadastro() == null ? null : DataManager.getInstance().formatDate(entity.getDataCadastro());
        this.dataDesativacao = entity.getDataDesativacao() == null ? null : DataManager.getInstance().formatDate(entity.getDataDesativacao());

        if (entity.getPerfil() != null) {
            this.idPerfil = entity.getPerfil().getId();
            this.perfil = entity.getPerfil().getDescricao();
            this.acessaUsuario = entity.getPerfil().getAcessaUsuario();
            this.acessaPerfil = entity.getPerfil().getAcessaPerfil();
            this.acessaConsultas = entity.getPerfil().getAcessaConsultas();
            this.acessaAgenda = entity.getPerfil().getAcessaAgenda();
            this.acessaSala = entity.getPerfil().getAcessaSala();
            this.acessaProcedimento = entity.getPerfil().getAcessaProcedimento();
            this.acessaVeterinario = entity.getPerfil().getAcessaVeterinario();
            this.acessaTutor = entity.getPerfil().getAcessaTutor();
            this.acessaProduto = entity.getPerfil().getAcessaTutor();
        } else {
            this.idPerfil = null;
            this.perfil = null;
            this.acessaUsuario = null;
            this.acessaPerfil = null;
            this.acessaConsultas = null;
            this.acessaAgenda = null;
            this.acessaSala = null;
            this.acessaProcedimento = null;
            this.acessaVeterinario = null;
            this.acessaTutor = null;
            this.acessaProduto = null;
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
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getDataDesativacao() {
        return dataDesativacao;
    }
    public void setDataDesativacao(String dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }
    public Integer getIdPerfil() {
        return idPerfil;
    }
    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }
    public String getPerfil() {
        return perfil;
    }
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getAcessaUsuario() {
        return acessaUsuario;
    }

    public void setAcessaUsuario(String acessaUsuario) {
        this.acessaUsuario = acessaUsuario;
    }

    public String getAcessaPerfil() {
        return acessaPerfil;
    }

    public void setAcessaPerfil(String acessaPerfil) {
        this.acessaPerfil = acessaPerfil;
    }

    public String getAcessaConsultas() {
        return acessaConsultas;
    }

    public void setAcessaConsultas(String acessaConsultas) {
        this.acessaConsultas = acessaConsultas;
    }

    public String getAcessaAgenda() {
        return acessaAgenda;
    }

    public void setAcessaAgenda(String acessaAgenda) {
        this.acessaAgenda = acessaAgenda;
    }

    public String getAcessaSala() {
        return acessaSala;
    }

    public void setAcessaSala(String acessaSala) {
        this.acessaSala = acessaSala;
    }

    public String getAcessaProcedimento() {
        return acessaProcedimento;
    }

    public void setAcessaProcedimento(String acessaProcedimento) {
        this.acessaProcedimento = acessaProcedimento;
    }

    public String getAcessaVeterinario() {
        return acessaVeterinario;
    }

    public void setAcessaVeterinario(String acessaVeterinario) {
        this.acessaVeterinario = acessaVeterinario;
    }

    public String getAcessaTutor() {
        return acessaTutor;
    }

    public void setAcessaTutor(String acessaTutor) {
        this.acessaTutor = acessaTutor;
    }
                            
    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getAcessaProduto() {
        return acessaProduto;
    }

    public void setAcessaProduto(String acessaProduto) {
        this.acessaProduto = acessaProduto;
    }
}