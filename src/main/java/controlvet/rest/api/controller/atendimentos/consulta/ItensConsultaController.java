package controlvet.rest.api.controller.atendimentos.consulta;

import controlvet.rest.api.service.atendimentos.consulta.ConsultaService;
import controlvet.rest.api.service.atendimentos.consulta.ItensConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ItensConsultaController {

    @Autowired
    private ItensConsultaService service;

    @GetMapping("/getItensByConsulta")
    public ResponseEntity<Object> getItensByConsulta(@RequestParam(required = true) Integer idConsulta) {
        try {
            return new ResponseEntity(this.service.findByIdConsulta(idConsulta), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
