package controlvet.rest.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.xml.bind.ValidationException;

import controlvet.rest.api.dto.PerfilDto;
import controlvet.rest.api.dto.UsuarioDto;
import controlvet.rest.api.entity.PerfilEntity;
import controlvet.rest.api.entity.SalaEntity;
import controlvet.rest.api.helper.BooleanManager;
import controlvet.rest.api.helper.NumberManager;
import controlvet.rest.api.helper.StringManager;
import controlvet.rest.api.helper.csvmanager.CsvGenerator;
import controlvet.rest.api.repository.UsuarioRepository;
import controlvet.rest.api.vo.UsuarioVo;
import controlvet.rest.api.vo.generic.ExportarCsvVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controlvet.rest.api.dto.PerfilDto;
import controlvet.rest.api.dto.generic.PesquisaDto;
import controlvet.rest.api.entity.PerfilEntity;
import controlvet.rest.api.repository.PerfilRepository;
import controlvet.rest.api.vo.PerfilVo;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository repository;

	protected PerfilEntity findById(Integer id) throws ValidationException {
		return this.repository.findById(id).orElseThrow(() -> new ValidationException("Perfil não encontrado"));
	}

	public List<PerfilVo> findByFilters(String descricao, Boolean listarInativos) throws ValidationException {
		return this.repository.findByFilters(descricao, Boolean.TRUE.equals(listarInativos) ? 1 : 0);
	}

	public PerfilEntity save(PerfilDto dto) throws ValidationException {
		PerfilEntity entity = new PerfilEntity();

		validarDados(dto);

		if (isUpdate(dto)) {
			entity = this.repository.findById(dto.getId()).get();
		} else {
			entity.setDataCadastro(new Date());
		}
		entity.setDescricao(dto.getDescricao());
		entity.setAcessaAgenda(BooleanManager.getInstance().parseString(dto.isAcessaAgenda()));
		entity.setAcessaPerfil(BooleanManager.getInstance().parseString(dto.isAcessaPerfil()));
		entity.setAcessaConsultas(BooleanManager.getInstance().parseString(dto.isAcessaConsultas()));
		entity.setAcessaProcedimento(BooleanManager.getInstance().parseString(dto.isAcessaProcedimento()));
		entity.setAcessaSala(BooleanManager.getInstance().parseString(dto.isAcessaSala()));
		entity.setAcessaTutor(BooleanManager.getInstance().parseString(dto.isAcessaTutor()));
		entity.setAcessaVeterinario(BooleanManager.getInstance().parseString(dto.isAcessaVeterinario()));
		entity.setAcessaUsuario(BooleanManager.getInstance().parseString(dto.isAcessaUsuario()));
		entity.setAcessaProduto(BooleanManager.getInstance().parseString(dto.isAcessaProduto()));

		return repository.save(entity);
	}

	private boolean validarDados (PerfilDto dto) throws ValidationException {
		StringManager sm = new StringManager();
		if (dto == null) {
			throw new ValidationException("Informações inválidas");
		}

		if (sm.isNullOrEmpty(dto.getDescricao())) {
			throw new ValidationException("Valor informado para a descrição é inválido.");
		}

		PerfilEntity perfil = this.repository.findBydescricao(dto.getDescricao());

		if (perfil != null && perfil.getDescricao().equals(dto.getDescricao()) && !perfil.getId().equals(dto.getId())) {
			throw new ValidationException("Já existe um perfil com essa descrição.");
		}
		return true;
	}

	public PerfilEntity disableOrEnableById(Integer id) throws ValidationException {
		PerfilEntity entity = new PerfilEntity();
		entity = this.repository.findById(id).get();
		entity.setDataDesativacao(entity.getDataDesativacao()== null ? new Date() : null );

		return repository.save(entity);
	}

	public Boolean isUpdate(PerfilDto dto) {
		return (dto.getId() != null);
	}

	public ExportarCsvVo generateCsv(String descricao, Boolean listarInativos) throws ValidationException, IOException {

		ExportarCsvVo exportarCsv = new ExportarCsvVo();

		List<PerfilVo> listaDados = this.repository.findByFilters(descricao, Boolean.TRUE.equals(listarInativos) ? 1 : 0);

		if (!listaDados.isEmpty()) {
			String caminhoArquivo = new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Perfis\"");

			Path path = Paths.get(caminhoArquivo);
			exportarCsv.setArquivo(Files.readAllBytes(path));
			exportarCsv.setNomeArquivo(path.getFileName().toString());
		} else {
			throw new ValidationException("Não existem dados para imprimir");
		}
		return exportarCsv;
	}
}