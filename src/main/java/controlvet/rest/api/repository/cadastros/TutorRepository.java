package controlvet.rest.api.repository.cadastros;

import java.util.List;

import controlvet.rest.api.entity.cadastros.TutorEntity;
import controlvet.rest.api.vo.cadastros.TutorVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends CrudRepository<TutorEntity, Integer> {

    @Query("select new controlvet.rest.api.vo.cadastros.TutorVo(a)"
            +"  from TutorEntity a"
            +" where (CASE WHEN :nome IS NULL OR a.nome LIKE CONCAT('%', :nome, '%') THEN 1 ELSE 0 END) = 1"
            +"   and (case when :exibeInativos = 1 OR a.dataDesativacao is null THEN 1 ELSE 0 END) = 1"
            +" order by a.nome")
    public List<TutorVo> findByFilters(@Param("nome") String nome, @Param("exibeInativos") Integer exibeInativos);

    @Query("select a"
           +" from TutorEntity a"
           +" where a.dataDesativacao is null")
    public List<TutorEntity> listAllTutoresAtivos();

    public TutorEntity findBynome(String nome);

    public TutorEntity findBycpf(String cpf);
}