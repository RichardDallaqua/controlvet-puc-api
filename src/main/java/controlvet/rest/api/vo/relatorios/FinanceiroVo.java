package controlvet.rest.api.vo.relatorios;

import java.math.BigDecimal;
import java.util.List;

public class FinanceiroVo {

    private List<String> quantidadeVendida;
    private List<BigDecimal> valorVendidos;
    private List<String> descricao;

    public FinanceiroVo(List<String> quantidadeVendida, List<BigDecimal> valorVendidos, List<String> descricao) {
        this.quantidadeVendida = quantidadeVendida;
        this.valorVendidos = valorVendidos;
        this.descricao = descricao;
    }

    public List<String> getValorDados() {
        return quantidadeVendida;
    }

    public void setValorDados(List<String> quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public List<String> getDescricao() {
        return descricao;
    }

    public void setDescricao(List<String> descricao) {
        this.descricao = descricao;
    }

    public List<String> getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(List<String> quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public List<BigDecimal> getValorVendidos() {
        return valorVendidos;
    }

    public void setValorVendidos(List<BigDecimal> valorVendidos) {
        this.valorVendidos = valorVendidos;
    }
}
