package com.futark.cros2.controller;

import com.futark.cros2.entity.Bd;
import com.futark.cros2.entity.Saga;
import com.futark.cros2.service.SagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/saga")
public class SagaController {

    @Autowired
    SagaService sagaService;


    @PostMapping("/save")
    ResponseEntity<Saga> saveSaga(@RequestBody Saga saga){
        return new ResponseEntity<Saga>(sagaService.saveSaga(saga), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Saga> getSaga(@PathVariable Long id){
        return new ResponseEntity<Saga>(sagaService.getSaga(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity <List<Saga>> getSagas(){
        return new ResponseEntity<List<Saga>>(sagaService.getSagas(),HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Bd>> getPageSaga
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size,
                    @RequestParam(defaultValue = "name") String sortProperty,
                    @RequestParam(defaultValue = "ASC") String sortDirection,

                    @RequestParam(defaultValue = "") String name
            ) {

        return new ResponseEntity(sagaService.filtreSaga( name, page,  size,  sortProperty,  sortDirection), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity <Boolean> delSaga(@PathVariable Long id ){
        return new ResponseEntity<>(sagaService.delSaga(id),HttpStatus.OK);
    }

}
