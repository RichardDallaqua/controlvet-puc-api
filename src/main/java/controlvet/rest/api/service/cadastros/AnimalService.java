package controlvet.rest.api.service.cadastros;

import controlvet.rest.api.dto.cadastros.AnimalDto;
import controlvet.rest.api.dto.generic.ComboDto;
import controlvet.rest.api.entity.cadastros.AnimalEntity;
import controlvet.rest.api.entity.cadastros.PerfilEntity;
import controlvet.rest.api.entity.cadastros.TutorEntity;
import controlvet.rest.api.enums.EspecieEnum;
import controlvet.rest.api.enums.SexoEnum;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.helper.NumberManager;
import controlvet.rest.api.helper.StringManager;
import controlvet.rest.api.helper.csvmanager.CsvGenerator;
import controlvet.rest.api.repository.cadastros.AnimalRepository;
import controlvet.rest.api.vo.cadastros.AnimalVo;
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
public class AnimalService {
    
    @Autowired
    private AnimalRepository repository;

    public AnimalEntity findById(Integer id) throws ValidationException {
        return this.repository.findById(id).orElseThrow(() -> new ValidationException("Animal não encontrado"));
    }

    public List<AnimalVo> findByFilters(String nome) throws ValidationException {
        return this.repository.findByFilters(nome);
    }

    public List<AnimalVo> findByIdTutor(Integer idTutor) throws ValidationException {
        return this.repository.findByIdTutor(idTutor);
    }

    public void save(TutorEntity entityTutor, List<AnimalDto> animalDto) throws Exception {
        deleteAllAnimais(entityTutor);

        for (AnimalDto animal : animalDto) {
            AnimalEntity entity = new AnimalEntity();
            entity.setDataCadastro(new Date());
            entity.setNome(animal.getNome());
            entity.setRaca(animal.getRaca());
            entity.setEspecie(animal.getEspecie());
            entity.setSexo(animal.getSexo());
            entity.setPeso(animal.getPeso());
            entity.setDataNascimento(DataManager.getInstance().stringToDate(animal.getDataNascimento()));
            entity.setTutor(entityTutor);

            this.repository.save(entity);
        }
    }

    public Boolean isUpdate(AnimalDto dto) {
        return (dto.getId() != null && !dto.getId().equals(0));
    }

    public void validarDados(List<AnimalDto> animalVo) throws ValidationException {
        for (AnimalDto animal : animalVo) {
            if (StringManager.getInstance().isNullOrEmpty(animal.getNome())) {
                throw new ValidationException("Existem animais sem nome.");
            }

            if (StringManager.getInstance().isNullOrEmpty(animal.getRaca())) {
                throw new ValidationException("Existem animais sem raça definida.");
            }

            if (NumberManager.getInstance().isNullOrZero(animal.getPeso())) {
                throw new ValidationException("Existem animais sem peso definido.");
            }

            if (StringManager.getInstance().isNullOrEmpty(animal.getDataNascimento())) {
                throw new ValidationException("Existem animais sem Data de Nascimento.");
            }

            if (!animal.getSexo().equals(SexoEnum.MACHO.value()) &&
                    !animal.getSexo().equals(SexoEnum.FEMEA.value())) {
                throw new ValidationException("Existem animais sem um sexo definido.");
            }

            if (!animal.getSexo().equals(EspecieEnum.CANIDEO.value()) &&
                    !animal.getSexo().equals(EspecieEnum.FELINO.value())) {
                throw new ValidationException("Existem animais sem uma espécie definida.");
            }
        }
    }

    public void deleteAllAnimais(TutorEntity entityTutor) {
        List<AnimalEntity> list = this.repository.findEntityByIdTutor(entityTutor.getId());
        for (AnimalEntity animal : list) {
            this.repository.delete(animal);
        }
    }

    public ExportarCsvVo generateCsv(Integer idTutor) throws ValidationException, IOException {
        List<AnimalVo> listaDados = this.repository.findByIdTutor(idTutor);

        if (listaDados.isEmpty()) {
            throw new ValidationException("Não existem dados para imprimir");
        }
        if (!listaDados.isEmpty()) {
            ExportarCsvVo exportarCsv = new ExportarCsvVo();

            String caminhoArquivo = new CsvGenerator().generateCSV(listaDados, "\"Relatório Animais\"");

            Path path = Paths.get(caminhoArquivo);
            exportarCsv.setArquivo(Files.readAllBytes(path));
            exportarCsv.setNomeArquivo(path.getFileName().toString());

            return exportarCsv;
        } else {
            throw new ValidationException("Não existem dados para imprimir");
        }
    }

    public List<AnimalVo> getAnimaisByTutor(Integer idTutor) {
        return this.repository.findByIdTutor(idTutor);
    }
}