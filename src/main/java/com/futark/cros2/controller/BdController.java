package com.futark.cros2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.futark.cros2.entity.Bd;
import com.futark.cros2.entity.Photo;
import com.futark.cros2.repository.PhotoRepo;
import com.futark.cros2.service.BdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/bd")
public class BdController {


    @Autowired
    BdService bdService;

    @Autowired
    PhotoRepo photoRepo;

//    @PostMapping("/save")
//    public ResponseEntity<Bd> saveBd(@RequestBody Bd bd){
//        return new ResponseEntity<Bd>(bdService.saveBd(bd),HttpStatus.OK) ;
//    }

    @PostMapping("/save")
    public ResponseEntity<Bd> createBd(@RequestParam("photo") Optional<MultipartFile> photo, @RequestParam String bd) throws IOException {
        return new ResponseEntity(bdService.saveBd(bd,photo), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bd> getBd(@PathVariable Long id){
        return new ResponseEntity<Bd>(bdService.getBd(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Bd>getAllBd(){
        return bdService.getBds();
    }

    @RequestMapping(
            value = "/page",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Page<Bd>> getPageBd
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size,
                    @RequestParam(defaultValue = "titre") String sortProperty,
                    @RequestParam(defaultValue = "ASC") String sortDirection,

                    @RequestParam(defaultValue = "") String titre,
                    @RequestParam(defaultValue = "") String tome,
                    @RequestParam(defaultValue = "") String statut,
                    @RequestParam(defaultValue = "") String saga,
                    @RequestParam(defaultValue = "") String edition,
                    @RequestParam(defaultValue = "") String illustrateur,
                    @RequestParam(defaultValue = "") String scenariste
            ) {

        return new ResponseEntity(bdService.filtreBd( titre,  tome, statut, saga, edition,  illustrateur, scenariste,  page,  size,  sortProperty,  sortDirection), HttpStatus.OK);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Boolean> delBd(@PathVariable Long id){
        return new ResponseEntity<>(bdService.delBd(id),HttpStatus.OK);
    }

    @DeleteMapping("/delall")
    public Boolean delAllBd(){
        return bdService.delAllBd();
    }

    @GetMapping("/possede")
    public ResponseEntity<Long> countPossede(){
        return new ResponseEntity<Long>(bdService.totalPossede(),HttpStatus.OK);
    }

}
