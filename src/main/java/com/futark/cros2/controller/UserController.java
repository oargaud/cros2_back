package com.futark.cros2.controller;

import com.futark.cros2.entity.Utilisateur;
import com.futark.cros2.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.beans.Encoder;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostConstruct
    public void initRoleAndUser() {
        utilisateurService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public Utilisateur registerNewUser(@RequestBody Utilisateur user) {
        return utilisateurService.registerNewUser(user);
    }

    @PostMapping({"/saveUser"})
    public Utilisateur saveUser(@RequestBody Utilisateur user) {
        return utilisateurService.saveUser(user);
    }

    @GetMapping({"/users"})
    @PreAuthorize("hasRole('Admin')")
    public List<Utilisateur> getUsers(){
        return utilisateurService.getUsers();
    }

    @GetMapping({"/user/{id}"})
    @PreAuthorize("hasRole('Admin')")
    public Utilisateur getUser(@PathVariable String id){
        return utilisateurService.getUser(id);
    }

    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasRole('Admin')")
    public Boolean delUser(@PathVariable String username){
        return utilisateurService.delUser(username);
    }


//    @GetMapping({"/forAdmin"})
//    @PreAuthorize("hasRole('Admin')")
//    public String forAdmin(){
//        return "This URL is only accessible to the admin";
//    }
//
//    @GetMapping({"/forUser"})
//    @PreAuthorize("hasRole('User')")
//    public String forUser(){
//        return "This URL is only accessible to the user";
//    }



    @GetMapping("validate")
    public void validateUsertest(@RequestParam String id, @RequestParam String code){
        System.out.println("id = "+id);
        System.out.println("code = "+code);
        utilisateurService.validateUser(id,code);

    }


}
