package controlvet.rest.api.controller;

import controlvet.rest.api.dto.PerfilDto;
import controlvet.rest.api.dto.generic.PesquisaDto;
import controlvet.rest.api.entity.PerfilEntity;
import controlvet.rest.api.entity.UsuarioEntity;
import controlvet.rest.api.repository.PerfilRepository;
import controlvet.rest.api.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    private PerfilService service;

    @GetMapping(path = "/findByFilters")
    public ResponseEntity<Object> findByFilters(@RequestParam(required = false) String descricao,
                                                @RequestParam(required = false) Boolean listarInativos,
                                                HttpServletRequest req) {
        try {
            return new ResponseEntity<Object>(this.service.findByFilters(descricao, listarInativos), HttpStatus.OK);
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

    @PostMapping(path = "/save")
    public ResponseEntity<Object> save(@RequestBody PerfilDto dto) throws ParseException {
        try {
            return new ResponseEntity<Object>(this.service.save(dto), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Object> update(@RequestBody PerfilDto dto) {
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
    
}
