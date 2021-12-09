package controlvet.rest.api.dto.cadastros;

public class UsuarioDto {

    private Integer id;
    private String nome;
    private String senha;
    private String login;
    private String dataCadastro;
    private String dataDesativacao;
    private Integer idPerfil;

    public UsuarioDto () {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() { return nome; }
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
    public String getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(String dataCadastro) { this.dataCadastro = dataCadastro; }
}