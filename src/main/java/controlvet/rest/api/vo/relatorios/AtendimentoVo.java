package controlvet.rest.api.vo.relatorios;

import java.util.List;

public class AtendimentoVo {

    private List<String> quantidade;
    private List<String> descricao;

    public AtendimentoVo(List<String> quantidade, List<String> descricao) {
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public List<String> getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(List<String> quantidade) {
        this.quantidade = quantidade;
    }

    public List<String> getDescricao() {
        return descricao;
    }

    public void setDescricao(List<String> descricao) {
        this.descricao = descricao;
    }
}
