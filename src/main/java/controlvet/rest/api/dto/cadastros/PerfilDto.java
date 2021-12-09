package controlvet.rest.api.dto.cadastros;

public class PerfilDto {

    private Integer id;
    private String descricao;
    private String dataCadastro;
    private String dataDesativacao;
    public boolean acessaUsuario;
    public boolean acessaPerfil;
    public boolean acessaConsultas;
    public boolean acessaAgenda;
    public boolean acessaSala;
    public boolean acessaProcedimento;
    public boolean acessaVeterinario;
    public boolean acessaTutor;
    public boolean acessaProduto;
    public PerfilDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

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

    public boolean isAcessaUsuario() { return acessaUsuario; }

    public void setAcessaUsuario(boolean acessaUsuario) { this.acessaUsuario = acessaUsuario;}

    public boolean isAcessaPerfil() { return acessaPerfil; }

    public void setAcessaPerfil(boolean acessaPerfil) { this.acessaPerfil = acessaPerfil; }

    public boolean isAcessaConsultas() { return acessaConsultas; }

    public void setAcessaConsultas(boolean acessaConsultas) { this.acessaConsultas = acessaConsultas; }

    public boolean isAcessaAgenda() { return acessaAgenda; }

    public void setAcessaAgenda(boolean acessaAgenda) { this.acessaAgenda = acessaAgenda; }

    public boolean isAcessaSala() { return acessaSala; }

    public void setAcessaSala(boolean acessaSala) { this.acessaSala = acessaSala; }

    public boolean isAcessaProcedimento() { return acessaProcedimento; }

    public void setAcessaProcedimento(boolean acessaProcedimento) { this.acessaProcedimento = acessaProcedimento; }

    public boolean isAcessaVeterinario() { return acessaVeterinario; }

    public void setAcessaVeterinario(boolean acessaVeterinario) { this.acessaVeterinario = acessaVeterinario; }

    public boolean isAcessaTutor() { return acessaTutor; }

    public void setAcessaTutor(boolean acessaTutor) { this.acessaTutor = acessaTutor; }

    public boolean isAcessaProduto() {
        return acessaProduto;
    }

    public void setAcessaProduto(boolean acessaProduto) {
        this.acessaProduto = acessaProduto;
    }
}
