package com.tfb.ednevnik.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ocjene")
public class ocjene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float ocjena;
    private String oblast;
    private LocalDate datum;

    @ManyToOne
    @JoinColumn(name = "id_pred", nullable = true)
    private predmet predmet;

    @ManyToOne
    @JoinColumn(name = "id_kor", nullable = true)
    private korisnik korisnik;

    public ocjene(float ocjena, String oblast, LocalDate datum, com.tfb.ednevnik.model.predmet predmet,
            com.tfb.ednevnik.model.korisnik korisnik) {
        this.ocjena = ocjena;
        this.oblast = oblast;
        this.datum = datum;
        this.predmet = predmet;
        this.korisnik = korisnik;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getOcjena() {
        return ocjena;
    }

    public void setOcjena(float ocjena) {
        this.ocjena = ocjena;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(predmet predmet) {
        this.predmet = predmet;
    }

    public korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
