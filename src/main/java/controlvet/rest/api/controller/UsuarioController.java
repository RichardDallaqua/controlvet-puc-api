package controlvet.rest.api.controller;

import controlvet.rest.api.dto.UsuarioDto;
import controlvet.rest.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.text.ParseException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping(path = "/findByFilters")
    public ResponseEntity<Object> findByFiltros(@RequestParam(required = false) String descricao,
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

    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<Object> consultar(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<Object>(this.service.findById(id), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Object> save(@RequestBody UsuarioDto dto) throws ParseException {
        try {
            return new ResponseEntity<Object>(this.service.save(dto), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Object> update(@RequestBody UsuarioDto dto) {
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

    @GetMapping("/loginValidation")
    public ResponseEntity<byte[]> loginValidation(@RequestParam(required = false) String usuario,
                                                  @RequestParam(required = false) String senha,
                                                  @RequestParam(required = false) String nome,
                                                  @RequestParam(required = false) String isGoogle,
                                                  HttpServletRequest req) {
        try {
            return new ResponseEntity(this.service.loginValidation(usuario, senha, nome, isGoogle), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
