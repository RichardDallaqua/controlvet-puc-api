package controlvet.rest.api.vo;

import controlvet.rest.api.entity.PerfilEntity;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.helper.StringManager;
import controlvet.rest.api.helper.csvmanager.Classes;
import controlvet.rest.api.helper.csvmanager.CsvInterface;

import java.util.Date;

public class PerfilVo {

    @CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
    private Integer id;
    @CsvInterface(colClass = Classes.STRING, property = "descricao", header = "\"Descrição\"")
    private String descricao;
    @CsvInterface(colClass = Classes.STRING, property = "dataCadastro", header = "\"Data Cadastro\"")
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

    public PerfilVo(PerfilEntity entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.dataCadastro = entity.getDataCadastro() == null ? null : DataManager.getInstance().formatDate(entity.getDataCadastro());
        this.dataDesativacao = entity.getDataDesativacao() == null ? null : DataManager.getInstance().formatDate(entity.getDataDesativacao());
        StringManager stringManager = StringManager.getInstance();
        this.acessaUsuario = stringManager.parseBoolean(entity.getAcessaUsuario());
        this.acessaPerfil = stringManager.parseBoolean(entity.getAcessaPerfil());
        this.acessaConsultas = stringManager.parseBoolean(entity.getAcessaConsultas());
        this.acessaAgenda = stringManager.parseBoolean(entity.getAcessaAgenda());
        this.acessaSala = stringManager.parseBoolean(entity.getAcessaSala());
        this.acessaProcedimento = stringManager.parseBoolean(entity.getAcessaProcedimento());
        this.acessaVeterinario = stringManager.parseBoolean(entity.getAcessaVeterinario());
        this.acessaTutor = stringManager.parseBoolean(entity.getAcessaTutor());
        this.acessaProduto = stringManager.parseBoolean(entity.getAcessaProduto());
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getDataCadastro() { return dataCadastro; }

    public void setDataCadastro(String dataCadastro) { this.dataCadastro = dataCadastro; }

    public String getDataDesativacao() { return dataDesativacao; }

    public void setDataDesativacao(String dataDesativacao) { this.dataDesativacao = dataDesativacao; }

    public boolean isAcessaUsuario() { return acessaUsuario; }

    public void setAcessaUsuario(boolean acessaUsuario) { this.acessaUsuario = acessaUsuario; }

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
