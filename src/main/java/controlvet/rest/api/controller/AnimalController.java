package controlvet.rest.api.controller;

import controlvet.rest.api.dto.AnimalDto;
import controlvet.rest.api.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.text.ParseException;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService service;

//    @GetMapping(path = "/findByFilters")
//    public ResponseEntity<Object> findByFilters(@RequestParam(required = false) String nome,
//                                                HttpServletRequest req) {
//        try {
//            return new ResponseEntity<Object>(this.service.findByFilters(nome), HttpStatus.OK);
//        } catch (ValidationException e) {
//            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
//        } catch (Exception e) {
//            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping(path = "/save")
//    public ResponseEntity<Object> save(@RequestBody AnimalDto dto) throws ParseException {
//        try {
//            return new ResponseEntity<Object>(this.service.save(dto), HttpStatus.OK);
//        } catch (ValidationException e) {
//            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
//        } catch (Exception e) {
//            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping(path = "/update")
//    public ResponseEntity<Object> update(@RequestBody AnimalDto dto) {
//        try {
//            return new ResponseEntity<Object>(this.service.save(dto), HttpStatus.OK);
//        } catch (ValidationException e) {
//            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
//        } catch (Exception e) {
//            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping(path = "/disableOrEnableById/{id}")
//    public ResponseEntity<Object> disableOrEnableById(@PathVariable("id") Integer id) {
//        try {
//            return new ResponseEntity<Object>(this.service.disableOrEnableById(id), HttpStatus.OK);
//        } catch (ValidationException e) {
//            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
//        } catch (Exception e) {
//            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

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
}