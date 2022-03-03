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
    @JoinColumn(name = "saga_id")
    private Saga saga;

    @ManyToOne
    @JoinColumn(name = "edition_id")
    private Edition edition;

    @ManyToOne
    @JoinColumn(name = "photo_id")
    private Photo couv;

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
    @JoinColumn(name = "lieu_id")
    private Lieu stockage;

    @ManyToOne
    @JoinColumn(name = "pret_id")
    private Pret pret;
}
