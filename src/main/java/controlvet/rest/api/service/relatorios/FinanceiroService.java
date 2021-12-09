package controlvet.rest.api.service.relatorios;

import controlvet.rest.api.entity.cadastros.ProdutoEntity;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.service.atendimentos.consulta.ItensConsultaService;
import controlvet.rest.api.vo.relatorios.FinanceiroVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinanceiroService {

    @Autowired
    private ItensConsultaService itensConsultaService;

    public FinanceiroVo getProdutosVendidos(String dataInicial, String dataFinal) throws Exception {

        DataManager dm = DataManager.getInstance();

        List<ProdutoEntity> list = this.itensConsultaService.getProdutosVendidos(dm.stringToDate(dataInicial),
                                                                                 dm.stringToDate(dataFinal));

        List<String> quantidadeVendida = new ArrayList<>();
        List<BigDecimal> valorVendido = new ArrayList<>();
        List<String> descricao = new ArrayList<>();

        for (ProdutoEntity produto : list) {
            quantidadeVendida.add(this.itensConsultaService.getNumeroVendas(produto.getId(),
                                                                            dm.stringToDate(dataInicial),
                                                                            dm.stringToDate(dataFinal)).toString());
            valorVendido.add(
                    new BigDecimal(this.itensConsultaService.
                            getFaturamentoBrutoProdutosVendidos(produto.getId(),
                                                                dm.stringToDate(dataInicial),
                                                                dm.stringToDate(dataFinal))).setScale(2, RoundingMode.HALF_UP));
            descricao.add(produto.getDescricao());
        }

        return new FinanceiroVo(quantidadeVendida, valorVendido, descricao);
    }

}
