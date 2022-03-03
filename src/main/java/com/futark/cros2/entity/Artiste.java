package com.futark.cros2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Artiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastname;
    private String firstname;

    public void setLastname(String lastname) {
        this.lastname = lastname.toUpperCase();
    }

    public void setFirstname(String firstname) {
        String premiereLettre = firstname.toUpperCase().substring(0,1);
        String resteDuPrenom = firstname.toLowerCase().substring(1,firstname.length());
        this.firstname = premiereLettre + resteDuPrenom;
    }

}
