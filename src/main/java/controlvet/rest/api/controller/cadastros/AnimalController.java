package controlvet.rest.api.controller.cadastros;

import controlvet.rest.api.service.cadastros.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @GetMapping("/generateCsv")
    public ResponseEntity<byte[]> generateCsv(@RequestParam(required = false) Integer idTutor,
                                              HttpServletRequest req) {
        try {
            return new ResponseEntity(this.service.generateCsv(idTutor), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAnimaisByTutor")
    public ResponseEntity<Object> generateCsv(@RequestParam(required = true) Integer idTutor) {
        try {
            return new ResponseEntity(this.service.getAnimaisByTutor(idTutor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByFilters")
    public ResponseEntity<Object> generateCsv(@RequestParam(required = false) String nomeTutor) {
        try {
            return new ResponseEntity(this.service.findByFilters(nomeTutor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}