package com.futark.cros2.controller;

import com.futark.cros2.entity.Bd;
import com.futark.cros2.entity.Edition;
import com.futark.cros2.entity.Saga;
import com.futark.cros2.service.EditionService;
import com.futark.cros2.service.SagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/edition")
public class EditionController {

    @Autowired
    EditionService editionService;


    @PostMapping("/save")
    ResponseEntity<Edition> saveEdition(@RequestBody Edition saga){
        return new ResponseEntity<Edition>(editionService.saveEdition(saga), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Edition> getEdition(@PathVariable Long id){
        return new ResponseEntity<Edition>(editionService.getEdition(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity <List<Edition>> getEditions(){
        return new ResponseEntity<List<Edition>>(editionService.getEditions(),HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Bd>> getPageEdition
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size,
                    @RequestParam(defaultValue = "name") String sortProperty,
                    @RequestParam(defaultValue = "ASC") String sortDirection,

                    @RequestParam(defaultValue = "") String name
            ) {

        return new ResponseEntity(editionService.filtreEdition( name, page,  size,  sortProperty,  sortDirection), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity <Boolean> delEdition(@PathVariable Long id ){
        return new ResponseEntity<>(editionService.delEdition(id),HttpStatus.OK);
    }

}
