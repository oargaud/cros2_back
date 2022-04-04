package com.futark.cros2.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String tome;

    private String statut;

    @ManyToOne
    private Saga saga;

    @ManyToOne
    private Edition edition;

    @ManyToOne
    private Photo photo;

    @ManyToMany
    @JoinTable(
            name = "bd_artiste_illustrateurs",
            joinColumns = @JoinColumn(name = "bd_id"),
            inverseJoinColumns = @JoinColumn(name = "artiste_id"))
    private List<Artiste> illustrateurs;

    @ManyToMany
    @JoinTable(
            name = "bd_artiste_scenaristes",
            joinColumns = @JoinColumn(name = "bd_id"),
            inverseJoinColumns = @JoinColumn(name = "artiste_id"))
    private List<Artiste> scenaristes;

    @ManyToOne
    private Lieu stockage;

    @ManyToOne
    private Pret pret;
}
