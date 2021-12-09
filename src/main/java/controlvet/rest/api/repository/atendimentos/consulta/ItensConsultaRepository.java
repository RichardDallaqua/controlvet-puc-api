package controlvet.rest.api.repository.atendimentos.consulta;

import controlvet.rest.api.entity.atendimentos.consulta.ItensConsultaEntity;
import controlvet.rest.api.entity.cadastros.ProdutoEntity;
import controlvet.rest.api.vo.atendimentos.consulta.ItensConsultaVo;
import controlvet.rest.api.vo.cadastros.AnimalVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ItensConsultaRepository extends CrudRepository<ItensConsultaEntity, Integer> {

    @Query("select new controlvet.rest.api.vo.atendimentos.consulta.ItensConsultaVo(a)"
            +"  from ItensConsultaEntity a"
            +" where a.consulta.id = :idConsulta"
            +" order by a.id")
    public List<ItensConsultaVo> findByIdConsulta(@Param("idConsulta") Integer idConsulta);

    @Query("select a"
            +"  from ItensConsultaEntity a"
            +" where a.consulta.id = :idConsulta"
            +" order by a.id")
    public List<ItensConsultaEntity> findByItensConsultaByIdConsulta(@Param("idConsulta") Integer idConsulta);

    @Query("select a.produto" +
           "  from ItensConsultaEntity a" +
           " where a.consulta.agendamento.dataAgendamento between :dataInicial and :dataFinal" +
           " group by 1")
    public List<ProdutoEntity> getProdutosConsulta(@Param("dataInicial") Date dataInicial,
                                                   @Param("dataFinal") Date dataFinal);

    @Query("select COUNT(a.produto)" +
           "  from ItensConsultaEntity a" +
           " where a.produto.id = :idProduto" +
           "   and a.consulta.agendamento.dataAgendamento between :dataInicial and :dataFinal")
    public Integer getNumeroVendasPorProduto(@Param("idProduto") Integer idProduto,
                                             @Param("dataInicial") Date dataInicial,
                                             @Param("dataFinal") Date dataFinal);

    @Query("select SUM(a.valorUnitario * quantidade)" +
           "  from ItensConsultaEntity a " +
           " where a.produto.id = :idProduto" +
           "   and a.consulta.agendamento.dataAgendamento between :dataInicial and :dataFinal")
    public Double getValorFaturamentoBrutoPorProduto(@Param("idProduto") Integer idProduto,
                                                     @Param("dataInicial") Date dataInicial,
                                                     @Param("dataFinal") Date dataFinal);
}
