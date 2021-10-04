package controlvet.rest.api.vo;

import controlvet.rest.api.entity.SalaEntity;
import controlvet.rest.api.helper.DataManager;
import controlvet.rest.api.helper.csvmanager.Classes;
import controlvet.rest.api.helper.csvmanager.CsvInterface;

public class SalaVo {
   @CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
   private Integer id;
   @CsvInterface(colClass = Classes.STRING, property = "descricao", header = "\"Sala\"")
   private String descricao;
   @CsvInterface(colClass = Classes.STRING, property = "dataCadastro", header = "\"Data Cadastro\"")
   private String dataCadastro;
   private String dataDesativacao;

   public SalaVo (SalaEntity entity) {
       this.id = entity.getId();
       this.descricao = entity.getDescricao();
       this.dataCadastro = entity.getDataCadastro() == null ? null : DataManager.getInstance().formatDate(entity.getDataCadastro());
       this.dataDesativacao = entity.getDataDesativacao() == null ? null : DataManager.getInstance().formatDate(entity.getDataDesativacao());
   }

   public Integer getId() { return id; }
   public void setId(Integer id) { this.id = id; }
   public String getDescricao() { return descricao; }
   public void setDescricao(String descricao) { this.descricao = descricao; }
   public String getDataCadastro() { return dataCadastro; }
   public void setDataCadastro(String dataCadastro) { this.dataCadastro = dataCadastro; }
   public String getDataDesativacao() { return dataDesativacao; }
   public void setDataDesativacao(String dataDesativacao) { this.dataDesativacao = dataDesativacao; }
}
