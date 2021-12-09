package controlvet.rest.api.repository.atendimentos;

import controlvet.rest.api.entity.atendimentos.AgendamentoEntity;
import controlvet.rest.api.entity.cadastros.ProcedimentoEntity;
import controlvet.rest.api.entity.cadastros.ProdutoEntity;
import controlvet.rest.api.entity.cadastros.SalaEntity;
import controlvet.rest.api.entity.cadastros.TutorEntity;
import controlvet.rest.api.vo.atendimentos.AgendamentoVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface AgendamentoRepository extends CrudRepository<AgendamentoEntity, Integer> {
    //@formatter:off
    @Query("select new controlvet.rest.api.vo.atendimentos.AgendamentoVo(a)"
            +"  from AgendamentoEntity a"
            +" where a.dataAgendamento = :dataAgendamento"
            +" order by a.horaAgendamento")
    public List<AgendamentoVo> findByFiltros(@Param("dataAgendamento") Date dataAgendamento);

    @Query("select a.sala" +
           "  from AgendamentoEntity a" +
           " where a.dataAgendamento between :dataAgendamentoInicial and :dataAgendamentoFinal" +
           " group by 1")
    public List<SalaEntity> getSalasAgendamentos(@Param("dataAgendamentoInicial") Date dataAgendamentoInicial,
                                                 @Param("dataAgendamentoFinal") Date dataAgendamentoFinal);

    @Query("select COUNT(a.sala)" +
           "  from AgendamentoEntity a " +
           " where a.sala.id = :idSala" +
           "   and a.dataAgendamento between :dataAgendamentoInicial and :dataAgendamentoFinal")
    public Integer getQuantidadeSalaPorAgendamento(@Param("idSala") Integer idSala,
                                                   @Param("dataAgendamentoInicial") Date dataAgendamentoInicial,
                                                   @Param("dataAgendamentoFinal") Date dataAgendamentoFinal);

    @Query("select a.procedimento" +
           "  from AgendamentoEntity a" +
           " where a.dataAgendamento between :dataAgendamentoInicial and :dataAgendamentoFinal" +
           " group by 1")
    public List<ProcedimentoEntity> getProcedimentosAgendamentos(@Param("dataAgendamentoInicial") Date dataAgendamentoInicial,
                                                                 @Param("dataAgendamentoFinal") Date dataAgendamentoFinal);

    @Query("select COUNT(a.procedimento)" +
           "  from AgendamentoEntity a " +
           " where a.procedimento.id = :idProcedimento" +
           "   and a.dataAgendamento between :dataAgendamentoInicial and :dataAgendamentoFinal")
    public Integer getQuantidadeProcedimentoPorAgendamento(@Param("idProcedimento") Integer idProcedimento,
                                                           @Param("dataAgendamentoInicial") Date dataAgendamentoInicial,
                                                           @Param("dataAgendamentoFinal") Date dataAgendamentoFinal);

    @Query("select a" +
           "  from AgendamentoEntity a" +
           " where a.dataAgendamento between :dataAgendamentoInicial and :dataAgendamentoFinal " +
           "   and a.atendido = 1" +
           " group by a.dataAgendamento")
    public List<AgendamentoEntity> getAgendamentosDoMes(@Param("dataAgendamentoInicial") Date dataAgendamentoInicial,
                                                        @Param("dataAgendamentoFinal") Date dataAgendamentoFinal);

    @Query("select COUNT(a.dataAgendamento)" +
           " from AgendamentoEntity a " +
           " where a.dataAgendamento = :dataAgendamento" +
           "   and a.atendido = 1")
    public Integer getQuantidadeAgendamentoPorDia(@Param("dataAgendamento") Date dataAgendamento);

    @Query("select a" +
           "  from AgendamentoEntity a" +
           " where a.dataAgendamento = :dataAgendamento" +
           "   and a.sala.id = :idSala" +
           "   and a.id <> :idAgendamento")
    public List<AgendamentoEntity> getAgendamentosPorDataESala(@Param("idSala") Integer idSala,
                                                               @Param("dataAgendamento") Date dataAgendamento,
                                                               @Param("idAgendamento") Integer idAgendamento);
}
