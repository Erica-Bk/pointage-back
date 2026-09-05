package com.example.pointage.model;

import jakarta.persistence.*;

@Entity
@Table(name = "suplementaire")
public class Suplementaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "heure", nullable = false)
    private Integer heure;

    public Suplementaire() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getHeure() { return heure; }
    public void setHeure(Integer heure) { this.heure = heure; }
}