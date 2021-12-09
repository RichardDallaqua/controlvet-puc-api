package controlvet.rest.api.controller.atendimentos.consulta;

import controlvet.rest.api.dto.atendimentos.consulta.ConsultaDto;
import controlvet.rest.api.dto.cadastros.TutorDto;
import controlvet.rest.api.service.atendimentos.consulta.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @GetMapping(path = "/findByFilters")
    public ResponseEntity<Object> findByFiltros(@RequestParam(required = false) String dataPesquisa,
                                                HttpServletRequest req) {
        try {
            return new ResponseEntity<Object>(this.service.findByFiltros(dataPesquisa), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/findByagendamento")
    public ResponseEntity<Object> findByagendamento(@RequestParam(required = false) Integer idAgendamento,
                                                HttpServletRequest req) {
        try {
            return new ResponseEntity<Object>(this.service.findByIdAgendamento(idAgendamento), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody ConsultaDto dto) {
        try {
            return new ResponseEntity<>(this.service.save(dto), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/generateCsv")
    public ResponseEntity<byte[]> generateCsv(@RequestParam(required = false) String dataPesquisa,
                                              HttpServletRequest req) {
        try {
            return new ResponseEntity(this.service.generateCsv(dataPesquisa), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
