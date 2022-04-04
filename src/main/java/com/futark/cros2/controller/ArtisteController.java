package com.futark.cros2.controller;

import com.futark.cros2.entity.Artiste;
import com.futark.cros2.entity.Bd;
import com.futark.cros2.entity.Saga;
import com.futark.cros2.service.ArtisteService;
import com.futark.cros2.service.SagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/artiste")
public class ArtisteController {

    @Autowired
    ArtisteService artisteService;


    @PostMapping("/save")
    ResponseEntity<Artiste> saveArtiste(@RequestBody Artiste artiste){
        return new ResponseEntity<Artiste>(artisteService.saveArtiste(artiste), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Artiste> getArtiste(@PathVariable Long id){
        Artiste artiste = artisteService.getArtiste(id);
        return new ResponseEntity<Artiste>(artiste,HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity <List<Artiste>> getArtistes(){
        return new ResponseEntity<List<Artiste>>(artisteService.getArtistes(),HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Bd>> getPageArtiste
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size,
                    @RequestParam(defaultValue = "lastname") String sortProperty,
                    @RequestParam(defaultValue = "ASC") String sortDirection,

                    @RequestParam(defaultValue = "") String lastname,
                    @RequestParam(defaultValue = "") String firstname,
                    @RequestParam(defaultValue = "") String nickname
            ) {

        return new ResponseEntity(artisteService.filtreArtiste( lastname, firstname, nickname, page,  size,  sortProperty,  sortDirection), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity <Boolean> delArtiste(@PathVariable Long id ){
        return new ResponseEntity<>(artisteService.delArtiste(id),HttpStatus.OK);
    }

}
