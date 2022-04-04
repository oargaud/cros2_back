package com.futark.cros2.controller;

import com.futark.cros2.entity.Bd;
import com.futark.cros2.entity.Control;
import com.futark.cros2.entity.Edition;
import com.futark.cros2.service.ControlService;
import com.futark.cros2.service.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/control")
public class ControlController {

    @Autowired
    ControlService controlService;


    @PostMapping("/save")
    ResponseEntity<Control> saveControl(@RequestBody Control control){
        return new ResponseEntity<Control>(controlService.saveControl(control), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Control> getControl(@PathVariable Long id){
        return new ResponseEntity<Control>(controlService.getControl(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity <List<Control>> getControls(){
        return new ResponseEntity<List<Control>>(controlService.getControls(),HttpStatus.OK);
    }

//    @GetMapping("/page")
//    public ResponseEntity<Page<Control>> getPageControl
//            (
//                    @RequestParam(defaultValue = "0") Integer page,
//                    @RequestParam(defaultValue = "10") Integer size,
//                    @RequestParam(defaultValue = "name") String sortProperty,
//                    @RequestParam(defaultValue = "ASC") String sortDirection,
//
//                    @RequestParam(defaultValue = "") String name
//            ) {
//
//        return new ResponseEntity(controlService.filtreEdition( name, page,  size,  sortProperty,  sortDirection), HttpStatus.OK);
//    }

    @DeleteMapping("/id")
    ResponseEntity <Boolean> delControl(@PathVariable Long id ){
        return new ResponseEntity<>(controlService.delControl(id),HttpStatus.OK);
    }

}
