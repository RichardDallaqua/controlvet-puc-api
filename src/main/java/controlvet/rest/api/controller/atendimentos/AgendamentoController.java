package controlvet.rest.api.controller.atendimentos;

import controlvet.rest.api.dto.atendimentos.AgendamentoDto;
import controlvet.rest.api.dto.cadastros.UsuarioDto;
import controlvet.rest.api.service.atendimentos.AgendamentoService;
import controlvet.rest.api.service.cadastros.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.text.ParseException;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

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

    @PostMapping(path = "/save")
    public ResponseEntity<Object> save(@RequestBody AgendamentoDto dto) throws ParseException {
        try {
            return new ResponseEntity<Object>(this.service.save(dto), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        try {
            Boolean isRemoved = this.service.delete(id);
            if (!isRemoved) {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
              return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (ValidationException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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

