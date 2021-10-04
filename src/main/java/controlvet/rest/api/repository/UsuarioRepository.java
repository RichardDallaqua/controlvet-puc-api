package controlvet.rest.api.repository;

import controlvet.rest.api.entity.UsuarioEntity;
import controlvet.rest.api.vo.UsuarioVo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Integer> {

    @Query("select new controlvet.rest.api.vo.UsuarioVo("
            + "      a)"
            +"  from UsuarioEntity a"
            +" where (CASE WHEN :descricao IS NULL OR a.nome LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
            +"   and (case when :exibeInativos = 1 OR a.dataDesativacao is null THEN 1 ELSE 0 END) = 1"
            +" order by a.nome")
    public List<UsuarioVo> findByFiltros(@Param("descricao") String descricao, @Param("exibeInativos") Integer exibeInativos);

    public UsuarioEntity findBylogin(String login);
}