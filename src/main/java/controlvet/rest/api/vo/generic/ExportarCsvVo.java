package controlvet.rest.api.vo.generic;

public class ExportarCsvVo {

    private byte[] arquivo;

    private String nomeArquivo;

    public byte[] getArquivo() {
        return this.arquivo;
    }

    public String getNomeArquivo() {
        return this.nomeArquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
}