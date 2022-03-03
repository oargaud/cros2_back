package com.futark.cros2.controller;


import com.futark.cros2.entity.JwtRequest;
import com.futark.cros2.entity.JwtResponse;
import com.futark.cros2.entity.Utilisateur;
import com.futark.cros2.repository.UtilisateurRepo;
import com.futark.cros2.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UtilisateurRepo utilisateurRepo;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//        Utilisateur utilisateur;
//        try {
//            utilisateur = utilisateurRepo.findById(jwtRequest.getUserName()).get();
//
//        }
//        catch(Exception e){
//            throw new Exception("Login ou mot de passe invalide", e);
//        }
//
//        if(utilisateur!=null && !utilisateur.isIsvalidated()){
//            throw new Exception("L'email assoscié au compte n'a pas été validé");
//        }
//
//        if(utilisateur!=null && utilisateur.isIslocked()){
//            throw new Exception("Ce compte est momentanément verrouillé");
//        }
        try {
            return jwtService.createJwtToken(jwtRequest);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
}
