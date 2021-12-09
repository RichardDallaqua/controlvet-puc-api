package controlvet.rest.api.service.relatorios;

import controlvet.rest.api.entity.atendimentos.AgendamentoEntity;
import controlvet.rest.api.entity.cadastros.ProcedimentoEntity;
import controlvet.rest.api.entity.cadastros.ProdutoEntity;
import controlvet.rest.api.entity.cadastros.SalaEntity;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.service.atendimentos.AgendamentoService;
import controlvet.rest.api.service.atendimentos.consulta.ItensConsultaService;
import controlvet.rest.api.service.cadastros.SalaService;
import controlvet.rest.api.vo.relatorios.AtendimentoVo;
import controlvet.rest.api.vo.relatorios.FinanceiroVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AtendimentoService {

    @Autowired
    private AgendamentoService agendamentoService;

    public AtendimentoVo getRelatorioSalas(String dataInicial, String dataFinal) throws Exception {

        DataManager dm = DataManager.getInstance();
        
        List<SalaEntity> list = this.agendamentoService.getSalasAgendamentos(dm.stringToDate(dataInicial),
                                                                             dm.stringToDate(dataFinal));

        List<String> quantidade = new ArrayList<>();
        List<String> descricao = new ArrayList<>();

        for (SalaEntity sala : list) {
            quantidade.add(this.agendamentoService.getQuantidadeSalaPorAgendamento(sala.getId(),
                                                                                   dm.stringToDate(dataInicial),
                                                                                   dm.stringToDate(dataFinal)).toString());
            descricao.add(sala.getDescricao());
        }

        return new AtendimentoVo(quantidade, descricao);
    }

    public AtendimentoVo getRelatorioProcedimentos(String dataInicial, String dataFinal) throws Exception {
        
        DataManager dm = DataManager.getInstance();
        
        List<ProcedimentoEntity> list = this.agendamentoService.getProcedimentosAgendamentos(dm.stringToDate(dataInicial),
                                                                                             dm.stringToDate(dataFinal));

        List<String> quantidade = new ArrayList<>();
        List<String> descricao = new ArrayList<>();

        for (ProcedimentoEntity procedimento : list) {
            quantidade.add(this.agendamentoService.getQuantidadeSalaPorAgendamento(procedimento.getId(),
                                                                                   dm.stringToDate(dataInicial),
                                                                                   dm.stringToDate(dataFinal)).toString());
            descricao.add(procedimento.getDescricao());
        }

        return new AtendimentoVo(quantidade, descricao);
    }

    public AtendimentoVo getRelatorioAtendimentos(String dataInicial, String dataFinal) throws Exception {

        DataManager dm = DataManager.getInstance();

        List<AgendamentoEntity> list = this.agendamentoService.getAgendamentosDoMes(dm.stringToDate(dataInicial),
                                                                                    dm.stringToDate(dataFinal));

        List<String> quantidade = new ArrayList<>();
        List<String> descricao = new ArrayList<>();

        for (AgendamentoEntity agendamento : list) {
            quantidade.add(this.agendamentoService.getQuantidadeAgendamentoPorDia(agendamento.getDataAgendamento()).toString());
            descricao.add(dm.formatDate(agendamento.getDataAgendamento(),"dd/MM"));
        }

        return new AtendimentoVo(quantidade, descricao);
    }
}
