package com.futark.cros2.service;

import com.futark.cros2.entity.Role;
import com.futark.cros2.entity.Utilisateur;
import com.futark.cros2.repository.RoleRepo;
import com.futark.cros2.repository.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepo utilisateurRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @Value("${myApiUrl}")
    private String myApiUrl;

    public void initRoleAndUser() {


//        Role a = roleRepo.save(new Role("SuperAdmin","Acces Max"));
//        Role b =roleRepo.save(new Role("Admin","Administration des comptes"));
//        Role c =roleRepo.save(new Role("User","Compte de base"));
//
//        roleRepo.save(new Role("Bd","Donne acces au bd"));
//        roleRepo.save(new Role("BdAdmin","Donne acces a l'administration des bds"));
//        roleRepo.save(new Role("Control","Donne acces au control"));
//        roleRepo.save(new Role("ControlAdmin","Donne acces a l'administration des controls"));
//
//
//        utilisateurRepo.save(new Utilisateur("admin","admin","admin",getEncodedPassword("admin"),"admin@gmail.com",false,new HashSet<Role>(){{add(b);}}));
//        utilisateurRepo.save(new Utilisateur("user","user","user",getEncodedPassword("user"),"user@gmail.com",false,new HashSet<Role>(){{add(c);}}));
//        utilisateurRepo.save(new Utilisateur("gohor","Olivier","ARGAUD",getEncodedPassword("killian69"),"gohor@gmail.com",false,new HashSet<Role>(){{add(a);add(b);add(c);}}));


    }

    public Utilisateur registerNewUser(Utilisateur user){
        try {
            Role role = roleRepo.findById("User").get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRoles(userRoles);
            user.setPassword(getEncodedPassword(user.getPassword()));
            user.setValidationkey(passwordEncoder.encode(user.getFirstname()+user.getLastname()));

            String msg = "<br>\n" +
                    "<br>\n" +
                    "<div>Bonjour "+user.getFirstname()+"</div>\n" +
                    "<br>\n" +
                    "<br>\n" +
                    "<div>Merci de finaliser votre inscription en cliquant sur le lien ci dessous</div>\n" +
                    "<br>\n" +
                    "<a href=\""+ myApiUrl +"/validate?id="+user.getUsername()+"&code="+user.getValidationkey()+"\">valider votre compte</a>";

            mailService.sendMail(user.getEmail(),"validation compte Cros la grange",msg);

            return utilisateurRepo.save(user);
        }
        catch (Exception  e){
            return null;
        }

    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<Utilisateur> getUsers(){
        return utilisateurRepo.findAll();
    }

    public Utilisateur getUser(String id){
        return utilisateurRepo.findById(id).get();
    }

    public Utilisateur saveUser(Utilisateur user){
        return utilisateurRepo.save(user);
    }

    public Boolean delUser(String username){
        try {
            utilisateurRepo.deleteById(username);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void validateUser(String username, String code){
        Utilisateur utilisateur = utilisateurRepo.findById(username).get();
        utilisateur.setIsvalidated(utilisateur.getValidationkey().equals(code));
        utilisateurRepo.save(utilisateur);
    }

}
