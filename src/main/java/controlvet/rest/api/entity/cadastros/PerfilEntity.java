package controlvet.rest.api.entity.cadastros;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "cadperfis")
public class PerfilEntity {

	@Id
	@Column(name = "idcadperfis")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	public Integer id;

	@Column(name = "dcr_perfil")
	public String descricao;

	@Column(name = "dat_cadastro")
	public Date dataCadastro;

	@Column(name = "dat_desativacao")
	public Date dataDesativacao;

	@Column(name = "flb_acessa_usuario")
	public String acessaUsuario;

	@Column(name = "flb_acessa_perfil")
	public String acessaPerfil;

	@Column(name = "flb_acessa_consultas")
	public String acessaConsultas;

	@Column(name = "flb_acessa_agenda")
	public String acessaAgenda;

	@Column(name = "flb_acessa_sala")
	public String acessaSala;

	@Column(name = "flb_acessa_procedimento")
	public String acessaProcedimento;

	@Column(name = "flb_acessa_veterinario")
	public String acessaVeterinario;

	@Column(name = "flb_acessa_tutor")
	public String acessaTutor;

	@Column(name = "flb_acessa_produto")
	public String acessaProduto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String nome) { this.descricao = nome; }

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	public String getAcessaUsuario() { return acessaUsuario; }

	public void setAcessaUsuario(String acessaUsuario) { this.acessaUsuario = acessaUsuario; }

	public String getAcessaPerfil() { return acessaPerfil; }

	public void setAcessaPerfil(String acessaPerfil) { this.acessaPerfil = acessaPerfil; }

	public String getAcessaConsultas() { return acessaConsultas; }

	public void setAcessaConsultas(String acessaConsultas) { this.acessaConsultas = acessaConsultas; }

	public String getAcessaAgenda() { return acessaAgenda;	}

	public void setAcessaAgenda(String acessaAgenda) { this.acessaAgenda = acessaAgenda; }

	public String getAcessaSala() {	return acessaSala; }

	public void setAcessaSala(String acessaSala) { this.acessaSala = acessaSala; }

	public String getAcessaProcedimento() {	return acessaProcedimento; }

	public void setAcessaProcedimento(String acessaProcedimento) { this.acessaProcedimento = acessaProcedimento; }

	public String getAcessaVeterinario() { 	return acessaVeterinario; }

	public void setAcessaVeterinario(String acessaVeterinario) { this.acessaVeterinario = acessaVeterinario; }

	public String getAcessaTutor() { return acessaTutor; }

	public void setAcessaTutor(String acessaTutor) { this.acessaTutor = acessaTutor; }

	public String getAcessaProduto() {
		return acessaProduto;
	}

	public void setAcessaProduto(String acessaProduto) {
		this.acessaProduto = acessaProduto;
	}
}
