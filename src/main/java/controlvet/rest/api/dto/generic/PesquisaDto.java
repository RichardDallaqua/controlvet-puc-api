package controlvet.rest.api.dto.generic;

public class PesquisaDto {

    private String descricao;

    private boolean listarInativos;

    public PesquisaDto() {

    }

    public PesquisaDto(String descricao, boolean listarInativos) {
        super();
        this.descricao = descricao;
        this.listarInativos = listarInativos;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isListarInativos() {
        return this.listarInativos;
    }

    public void setListarInativos(boolean listarInativos) {
        this.listarInativos = listarInativos;
    }

}
