package controlvet.rest.api.entity.cadastros;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "cadusuarios")
public class UsuarioEntity {

	@Id
	@Column(name = "idcadusuarios")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	public Integer id;

	@Column(name = "dcr_nome")
	public String nome;

	@Column(name = "dcr_login")
	public String login;

	@Column(name = "dcr_senha")
	public String senha;

	@Column(name = "dat_cadastro")
	public Date dataCadastro;

	@Column(name = "dat_desativacao")
	public Date dataDesativacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcadperfis")
	public PerfilEntity perfil;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() { return senha; }

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(Date dataDesativacao) { this.dataDesativacao = dataDesativacao; }

	public PerfilEntity getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEntity perfil) { this.perfil = perfil; }
	
	public Date getDataCadastro() {	return dataCadastro; }

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
