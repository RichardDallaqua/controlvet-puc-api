package controlvet.rest.api.repository;

import java.util.List;

import controlvet.rest.api.entity.TutorEntity;
import controlvet.rest.api.vo.TutorVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TutorRepository extends CrudRepository<TutorEntity, Integer> {

    @Query("select new controlvet.rest.api.vo.TutorVo(a)"
            +"  from TutorEntity a"
            +" where (CASE WHEN :nome IS NULL OR a.nome LIKE CONCAT('%', :nome, '%') THEN 1 ELSE 0 END) = 1"
            +"   and (case when :exibeInativos = 1 OR a.dataDesativacao is null THEN 1 ELSE 0 END) = 1"
            +" order by a.nome")
    public List<TutorVo> findByFilters(@Param("nome") String nome, @Param("exibeInativos") Integer exibeInativos);

    public TutorEntity findBynome(String nome);

    public TutorEntity findBycpf(String cpf);
}