package controlvet.rest.api.controller.cadastros;

import controlvet.rest.api.dto.cadastros.SalaDto;
import controlvet.rest.api.service.cadastros.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.text.ParseException;

@RestController
@RequestMapping("/sala")
public class SalaController {

    @Autowired
    private SalaService service;

    @GetMapping(path = "/findByFilters")
    public ResponseEntity<Object> findByFilters(@RequestParam(required = false) String descricao,
                                                @RequestParam(required = false) Boolean listarInativos,
                                                HttpServletRequest req) {
        try {
            return new ResponseEntity<Object>(this.service.findByFiltros(descricao, listarInativos), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Object> save(@RequestBody SalaDto dto) throws ParseException {
        try {
            return new ResponseEntity<Object>(this.service.save(dto), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Object> update(@RequestBody SalaDto dto) {
        try {
            return new ResponseEntity<Object>(this.service.save(dto), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/disableOrEnableById/{id}")
    public ResponseEntity<Object> disableOrEnableById(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<Object>(this.service.disableOrEnableById(id), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/generateCsv")
    public ResponseEntity<byte[]> generateCsv(@RequestParam(required = false) String descricao,
                                              @RequestParam(required = false) Boolean listarInativos,
                                              HttpServletRequest req) {
        try {
            return new ResponseEntity(this.service.generateCsv(descricao, listarInativos), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}