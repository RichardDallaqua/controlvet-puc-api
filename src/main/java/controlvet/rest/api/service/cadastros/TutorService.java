package controlvet.rest.api.service.cadastros;

import controlvet.rest.api.dto.cadastros.TutorDto;
import controlvet.rest.api.dto.generic.ComboDto;
import controlvet.rest.api.entity.cadastros.TutorEntity;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.helper.StringManager;
import controlvet.rest.api.helper.csvmanager.CsvGenerator;
import controlvet.rest.api.repository.cadastros.TutorRepository;
import controlvet.rest.api.vo.cadastros.AnimalVo;
import controlvet.rest.api.vo.cadastros.TutorVo;
import controlvet.rest.api.vo.generic.ExportarCsvVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    @Autowired
    private AnimalService animalService;

    public List<TutorVo> findByFilters(String descricao, Boolean listarInativos) throws ValidationException {
        List<TutorVo> list = this.repository.findByFilters(descricao,
                (listarInativos == null ? false : listarInativos) ? 1 : 0);

        Integer idMaxItem = 1;
        for (TutorVo tutorVo : list) {
            tutorVo.setAnimalVo(this.animalService.findByIdTutor(tutorVo.getId()));
            for (AnimalVo animal : tutorVo.getAnimalVo()) {
                idMaxItem = animal.getId();
            }
            tutorVo.setIdMaxItem(idMaxItem);
        }

        return list;
    }

    public TutorEntity findById(Integer id) throws ValidationException {
        return this.repository.findById(id).orElseThrow(() -> new ValidationException("Perfil não encontrado."));
    }

    public TutorEntity save(TutorDto dto) throws Exception {
        validarDados(dto);

        TutorEntity entity = new TutorEntity();
        if (dto.getId() != null && !dto.getId().equals(0)) {
            entity = this.repository.findById(dto.getId()).get();
        } else {
            entity.setDataCadastro(new Date());
        }

        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.setCpf(dto.getCpf());
        entity.setEndereco(dto.getEndereco());
        entity.setDataNascimento(DataManager.getInstance().stringToDate(dto.getDataNascimento()));

        this.repository.save(entity);

        if (dto.getAnimalVo() != null && !dto.getAnimalVo().isEmpty()) {
            this.animalService.save(entity, dto.getAnimalVo());
        } else {
            this.animalService.deleteAllAnimais(entity);
        }

        return entity;
    }

    private void validarDados(TutorDto dto) throws ValidationException {

        if (dto == null) {
            throw new ValidationException("Tutor inválido.");
        }

        if (StringManager.getInstance().isNullOrEmpty(dto.getNome())) {
            throw new ValidationException("Informe um nome.");
        }

        if (StringManager.getInstance().isNullOrEmpty(dto.getTelefone()) || dto.getTelefone().length() < 10
                || dto.getTelefone().length() > 11) {
            throw new ValidationException("Informe um telefone válido.");
        }

        if (StringManager.getInstance().isNullOrEmpty(dto.getEndereco())) {
            throw new ValidationException("Informe um endereço.");
        }

        if (!StringManager.getInstance().isNullOrEmpty(dto.getCpf())) {
            TutorEntity tutor = this.repository.findBycpf(dto.getCpf());

            if (tutor != null && tutor.getCpf().equals(dto.getCpf()) && !tutor.getId().equals(dto.getId())) {
                throw new ValidationException("Já existe um tutor com esse CPF.");
            }
        }

        if (dto.getAnimalVo() != null && !dto.getAnimalVo().isEmpty()) {
            this.animalService.validarDados(dto.getAnimalVo());
        }
    }

    public TutorVo disableOrEnableById(Integer id) throws ValidationException {
        TutorEntity entity = this.repository.findById(id).get();
        entity.setDataDesativacao(entity.getDataDesativacao() == null ? new Date() : null);
        return new TutorVo(this.repository.save(entity));
    }

    public ExportarCsvVo generateCsv(String descricao, Boolean listarInativos) throws ValidationException, IOException {
        List<TutorVo> listaDados = this.repository.findByFilters(descricao,
                                                                  Boolean.TRUE.equals(listarInativos) ? 1 : 0);

        if (listaDados.isEmpty()) {
            throw new ValidationException("Não existem dados para imprimir");
        }
        if (!listaDados.isEmpty()) {
            ExportarCsvVo exportarCsv = new ExportarCsvVo();

            String caminhoArquivo = new CsvGenerator().generateCSV(listaDados, "\"Relatório Tutores\"");

            Path path = Paths.get(caminhoArquivo);
            exportarCsv.setArquivo(Files.readAllBytes(path));
            exportarCsv.setNomeArquivo(path.getFileName().toString());

            return exportarCsv;
        } else {
            throw new ValidationException("Não existem dados para imprimir");
        }
    }

    public List<ComboDto> getTutoresComboDto() {
        List<TutorEntity> tutores = this.repository.listAllTutoresAtivos();

        List<ComboDto> retorno = new ArrayList<>();

        for (TutorEntity tutor : tutores) {
            ComboDto dto = new ComboDto();

            String descricao = tutor.getNome();
            if (tutor.getCpf() != null) {
                descricao = descricao + " - CPF: " + StringManager.getInstance().formatString(tutor.getCpf(), "###.###.###-##");
            }

            dto.setLabel(descricao);
            dto.setValue(tutor.getId());

            retorno.add(dto);
        }
        return retorno;
    }

}