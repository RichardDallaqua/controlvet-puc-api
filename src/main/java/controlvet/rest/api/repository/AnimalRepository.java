package controlvet.rest.api.repository;

import controlvet.rest.api.entity.AnimalEntity;
import controlvet.rest.api.vo.AnimalVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository extends CrudRepository<AnimalEntity, Integer> {
    
        @Query("select new controlvet.rest.api.vo.AnimalVo(a)"
                +"  from AnimalEntity a"
                +" where (CASE WHEN :nome IS NULL OR a.nome LIKE CONCAT('%', :nome, '%') THEN 1 ELSE 0 END) = 1"
                +" order by a.nome")
        public List<AnimalVo> findByFilters(@Param("nome") String nome);

        @Query("select new controlvet.rest.api.vo.AnimalVo(a)"
                +"  from AnimalEntity a"
                +" where tutor.id = :idTutor")
        public List<AnimalVo> findByIdTutor(@Param("idTutor") Integer idTutor);

        @Query("select a"
                +"  from AnimalEntity a"
                +" where tutor.id = :idTutor")
        public List<AnimalEntity> findEntityByIdTutor(@Param("idTutor") Integer idTutor);
}

