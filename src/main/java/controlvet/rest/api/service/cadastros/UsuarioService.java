package controlvet.rest.api.service.cadastros;

import controlvet.rest.api.dto.cadastros.UsuarioDto;
import controlvet.rest.api.entity.cadastros.UsuarioEntity;
import controlvet.rest.api.helper.NumberManager;
import controlvet.rest.api.helper.StringManager;
import controlvet.rest.api.helper.csvmanager.CsvGenerator;
import controlvet.rest.api.repository.cadastros.UsuarioRepository;
import controlvet.rest.api.vo.cadastros.UsuarioVo;

import controlvet.rest.api.vo.generic.ExportarCsvVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.xml.bind.ValidationException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilService perfilService;

    public UsuarioEntity findById(Integer id) throws ValidationException {
        return this.repository.findById(id).orElseThrow(() -> new ValidationException("Usuário não encontrado"));
    }

    public List<UsuarioVo> findByFiltros(String descricao, Boolean listarInativos) throws ValidationException {
        return this.repository.findByFiltros(descricao, Boolean.TRUE.equals(listarInativos) ? 1 : 0);
    }

    public UsuarioVo loginValidation(String login, String senha, String usuario, String isGoogle) throws ValidationException {
        UsuarioEntity entity = this.repository.findBylogin(login);

        if (entity == null || login.isEmpty() || senha.isEmpty() || !senha.equals(entity.getSenha())){
            if (Boolean.parseBoolean(isGoogle)) {
                entity = cadastraUserGoogle(usuario, login, senha);
            } else {
                throw new ValidationException("Usuário ou senha inválidos.");
            }
        }

        if (entity.getDataDesativacao() != null)  {
            throw new ValidationException("O usuário informado está desativado no sistema.");
        }

       return new UsuarioVo(entity);
    }

    public UsuarioEntity cadastraUserGoogle(String nome, String login, String senha) throws ValidationException {
        UsuarioEntity entity = new UsuarioEntity();

        entity.setDataCadastro(new Date());
        entity.setNome(nome);
        entity.setSenha(senha);
        entity.setLogin(login);
        entity.setPerfil(this.perfilService.findById(0));

        return this.repository.save(entity);
    }

    public UsuarioEntity save(UsuarioDto dto) throws ValidationException {
        UsuarioEntity entity = new UsuarioEntity();

        validarDados(dto);

        if (isUpdate(dto)) {
            entity = this.repository.findById(dto.getId()).get();
        } else {
        	entity.setDataCadastro(new Date());
        }
        entity.setNome(dto.getNome());
        entity.setSenha(dto.getSenha());
        entity.setLogin(dto.getLogin());
        entity.setPerfil(perfilService.findById(dto.getIdPerfil()));

        return repository.save(entity);
    }

    public UsuarioEntity disableOrEnableById(Integer id) throws ValidationException {
        UsuarioEntity entity = new UsuarioEntity();
        entity = this.repository.findById(id).get();
        entity.setDataDesativacao(entity.getDataDesativacao()== null ? new Date() : null );

        return repository.save(entity);
    }

    public Boolean isUpdate(UsuarioDto dto) {
        return (dto.getId() != null && !dto.getId().equals(0));
    }

    public ExportarCsvVo generateCsv(String descricao, Boolean listarInativos) throws ValidationException, IOException {

        ExportarCsvVo exportarCsv = new ExportarCsvVo();

        List<UsuarioVo> listaDados = this.repository.findByFiltros(descricao, Boolean.TRUE.equals(listarInativos) ? 1 : 0);

        if (!listaDados.isEmpty()) {
            String caminhoArquivo = new CsvGenerator().generateCSV(listaDados, "\"Relatório Cadastro de Usuário\"");

            Path path = Paths.get(caminhoArquivo);
            exportarCsv.setArquivo(Files.readAllBytes(path));
            exportarCsv.setNomeArquivo(path.getFileName().toString());
        } else {
            throw new ValidationException("Não existem dados para imprimir");
        }
        return exportarCsv;
    }

    private boolean validarDados (UsuarioDto dto) throws ValidationException {
        StringManager sm = new StringManager();
        if (dto == null) {
            throw new ValidationException("Informações invalidas");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario = this.repository.findBylogin(dto.getLogin());

        if (usuario != null && (!dto.getId().equals(usuario.getId()))) {
            throw new ValidationException("Já existe esse login cadastrado no sistema");
        }

        if (sm.isNullOrEmpty(dto.getNome())) {
            throw new ValidationException("Valor informado para o nome é inválido.");
        }

        if (sm.isNullOrEmpty(dto.getSenha())) {
            throw new ValidationException("Valor informado para a senha é inválido.");
        }

        if (sm.isNullOrEmpty(dto.getLogin())) {
            throw new ValidationException("Valor informado para o login é inválido.");
        }

        if (NumberManager.getInstance().isNullOrZero(dto.getIdPerfil())) {
            throw new ValidationException("Valor informado para o perfil é inválido.");
        }

        return true;
    }
}