package controlvet.rest.api.repository.cadastros;

import controlvet.rest.api.entity.cadastros.ProdutoEntity;
import controlvet.rest.api.vo.cadastros.ProdutoVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends CrudRepository<ProdutoEntity, Integer> {

        @Query("select new controlvet.rest.api.vo.cadastros.ProdutoVo(a)"
                +"  from ProdutoEntity a"
                +" where (CASE WHEN :descricao IS NULL OR a.descricao LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
                +"   and (case when :exibeInativos = 1 OR a.dataDesativacao is null THEN 1 ELSE 0 END) = 1"
                +" order by a.id")
        public List<ProdutoVo> findByFiltros(@Param("descricao") String descricao, @Param("exibeInativos") Integer exibeInativos);

        public ProdutoEntity findBydescricao(String descricao);
}
