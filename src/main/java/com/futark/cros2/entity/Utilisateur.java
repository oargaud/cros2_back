package com.futark.cros2.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Utilisateur {

    @Id
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private boolean islocked;
    private boolean isvalidated;
    private String validationkey;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "UTILISATEUR_ROLE",
            joinColumns = {
                    @JoinColumn(name = "UTILISATEUR_USERNAME")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> roles;

    public Utilisateur() {
    }

    public Utilisateur(String username, String firstname, String lastname, String password, String email, boolean islocked, Set<Role> roles) {
        this.username = username;
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.password = password;
        this.email = email;
        this.islocked = islocked;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        String premiereLettre = firstname.toUpperCase().substring(0,1);
        String resteDuPrenom = firstname.toLowerCase().substring(1,firstname.length());
        this.firstname = premiereLettre + resteDuPrenom;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname.toUpperCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIslocked() {
        return islocked;
    }

    public void setIslocked(boolean islocked) {
        this.islocked = islocked;
    }

    public boolean isIsvalidated() {
        return isvalidated;
    }

    public void setIsvalidated(boolean isvalidated) {
        this.isvalidated = isvalidated;
    }

    public String getValidationkey() {
        return validationkey;
    }

    public void setValidationkey(String validationkey) {
        this.validationkey = validationkey;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

