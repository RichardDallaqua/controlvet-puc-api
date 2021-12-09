package controlvet.rest.api.repository.atendimentos.consulta;

import controlvet.rest.api.entity.atendimentos.consulta.ConsultaEntity;
import controlvet.rest.api.entity.cadastros.PerfilEntity;
import controlvet.rest.api.entity.cadastros.TutorEntity;
import controlvet.rest.api.vo.atendimentos.consulta.ConsultaVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ConsultaRepository extends CrudRepository<ConsultaEntity, Integer> {

        @Query("select new controlvet.rest.api.vo.atendimentos.consulta.ConsultaVo(a)"
                +"  from ConsultaEntity a"
                +" where a.agendamento.dataAgendamento = :dataAgendamento"
                +" order by a.agendamento.horaAgendamento")
        public List<ConsultaVo> findByFiltros(@Param("dataAgendamento") Date dataAgendamento);

        @Query("select a"
                +" from ConsultaEntity a"
                +" where a.agendamento.id = :idAgendamento")
        public ConsultaEntity findByagendamento(Integer idAgendamento);

}
