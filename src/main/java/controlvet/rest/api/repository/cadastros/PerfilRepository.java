package controlvet.rest.api.repository.cadastros;

import java.util.List;

import controlvet.rest.api.entity.cadastros.PerfilEntity;
import controlvet.rest.api.vo.cadastros.PerfilVo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface PerfilRepository extends CrudRepository<PerfilEntity, Integer> {
	
	@Query("select new controlvet.rest.api.vo.cadastros.PerfilVo("
			  +"       a)"
			  +"  from PerfilEntity a"
			  +" where (CASE WHEN :descricao IS NULL OR a.descricao LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
			  +"   and (case when :exibeInativos = 1 OR a.dataDesativacao is null THEN 1 ELSE 0 END) = 1"
			  +" order by a.descricao")
	public List<PerfilVo> findByFilters(@Param("descricao") String descricao, @Param("exibeInativos") Integer exibeInativos);

	public PerfilEntity findBydescricao(String descricao);
}