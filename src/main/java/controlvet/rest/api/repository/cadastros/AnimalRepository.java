package controlvet.rest.api.repository.cadastros;

import controlvet.rest.api.entity.cadastros.AnimalEntity;
import controlvet.rest.api.vo.cadastros.AnimalVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository extends CrudRepository<AnimalEntity, Integer> {
    
        @Query("select new controlvet.rest.api.vo.cadastros.AnimalVo(a)"
                +"  from AnimalEntity a"
                +" where (CASE WHEN :nome IS NULL OR a.nome LIKE CONCAT('%', :nome, '%') THEN 1 ELSE 0 END) = 1"
                +" order by a.nome")
        public List<AnimalVo> findByFilters(@Param("nome") String nome);

        @Query("select new controlvet.rest.api.vo.cadastros.AnimalVo(a)"
                +"  from AnimalEntity a"
                +" where tutor.id = :idTutor"
                +"   and a.dataDesativacao is null"
                +" order by a.nome")
        public List<AnimalVo> findByIdTutor(@Param("idTutor") Integer idTutor);

        @Query("select a"
                +"  from AnimalEntity a"
                +" where tutor.id = :idTutor")
        public List<AnimalEntity> findEntityByIdTutor(@Param("idTutor") Integer idTutor);

        @Query("select new controlvet.rest.api.vo.cadastros.AnimalVo(a)"
               +" from AnimalEntity a"
               +" where a.dataDesativacao is null"
               +"  and a.tutor.id = :idTutor"
               +" order by a.nome")
        public List<AnimalEntity> listAllAnimaisAtivos(@Param("idTutor") Integer idTutor);
}

