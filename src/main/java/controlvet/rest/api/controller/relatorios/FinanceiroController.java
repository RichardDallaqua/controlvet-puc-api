package controlvet.rest.api.controller.relatorios;

import controlvet.rest.api.service.relatorios.FinanceiroService;
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
@RequestMapping("/relatorio-financeiro")
public class FinanceiroController {
        @Autowired
        private FinanceiroService service;

        @GetMapping("/getProdutosVendidos")
        public ResponseEntity<byte[]> getProdutosVendidos(@RequestParam(required = true) String dataInicial,
                                                          @RequestParam(required = true) String dataFinal,
                                                          HttpServletRequest req) {
            try {
                return new ResponseEntity(this.service.getProdutosVendidos(dataInicial, dataFinal), HttpStatus.OK);
            } catch (ValidationException e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
            } catch (Exception e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


}
