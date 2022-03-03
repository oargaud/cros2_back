package com.futark.cros2.service;

import com.futark.cros2.repository.UtilisateurRepo;
import com.futark.cros2.entity.JwtRequest;
import com.futark.cros2.entity.JwtResponse;
import com.futark.cros2.entity.Utilisateur;
import com.futark.cros2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UtilisateurRepo utilisateurRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        try {
            authenticate(userName, userPassword);
        }
        catch (Exception e){
            throw new Exception("Login ou mot de passe invalide", e);
        }


        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        Utilisateur utilisateur;
        try {
            utilisateur = utilisateurRepo.findById(userName).get();
        }
        catch(Exception e){
            throw new Exception("Login ou mot de passe invalide", e);
        }

        if(utilisateur!=null && !utilisateur.isIsvalidated()){
            throw new Exception("L'email assoscié au compte n'a pas été validé");
        }

        if(utilisateur!=null && utilisateur.isIslocked()){
            throw new Exception("Ce compte est momentanément verrouillé");
        }


        return new JwtResponse(utilisateur, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepo.findById(username).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Set getAuthority(Utilisateur user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("Compte désactivé", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Login ou mot de passe invalide", e);
        }
    }
}
