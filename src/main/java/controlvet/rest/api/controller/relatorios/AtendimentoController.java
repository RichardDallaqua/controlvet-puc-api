package controlvet.rest.api.controller.relatorios;

import controlvet.rest.api.service.relatorios.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/relatorio-atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoService service;

    @GetMapping("/getRelatorioSalas")
    public ResponseEntity<byte[]> getRelatorioSalas(@RequestParam(required = true) String dataInicial,
                                                    @RequestParam(required = true) String dataFinal,
                                                    HttpServletRequest req) {
        try {
            return new ResponseEntity(this.service.getRelatorioSalas(dataInicial, dataFinal), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getRelatorioProcedimentos")
    public ResponseEntity<byte[]> getRelatorioProcedimentos(@RequestParam(required = true) String dataInicial,
                                                            @RequestParam(required = true) String dataFinal,
                                                            HttpServletRequest req) {
        try {
            return new ResponseEntity(this.service.getRelatorioProcedimentos(dataInicial, dataFinal), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getRelatorioAtendimentos")
    public ResponseEntity<byte[]> getRelatorioAtendimentos(@RequestParam(required = true) String dataInicial,
                                                           @RequestParam(required = true) String dataFinal,
                                                           HttpServletRequest req) {
        try {
            return new ResponseEntity(this.service.getRelatorioAtendimentos(dataInicial, dataFinal), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
