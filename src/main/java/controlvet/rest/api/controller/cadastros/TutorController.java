package controlvet.rest.api.controller.cadastros;

import controlvet.rest.api.dto.cadastros.TutorDto;
import controlvet.rest.api.service.cadastros.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    private TutorService service;

    @GetMapping("/findByFilters")
    public ResponseEntity<Object> findByFilters(@RequestParam(required = false) String descricao,
                                                @RequestParam(required = false) Boolean listarInativos, HttpServletRequest req) {
        try {
            return new ResponseEntity<>(this.service.findByFilters(descricao, listarInativos), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody TutorDto dto) {
        try {
            return new ResponseEntity<>(this.service.save(dto), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody TutorDto dto) {
        try {
            this.service.save(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/disableOrEnableById/{id}")
    public ResponseEntity<Object> disableOrEnableById(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(this.service.disableOrEnableById(id), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/generateCsv")
    public ResponseEntity<Object> generateCsv(@RequestParam(required = false) String descricao,
                                              @RequestParam(required = false) Boolean listarInativos, HttpServletRequest req) {
        try {
            return new ResponseEntity<>(this.service.generateCsv(descricao, listarInativos), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTutoresComboDto")
    public ResponseEntity<Object> generateCsv() {
        try {
            return new ResponseEntity(this.service.getTutoresComboDto(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
