package com.tfb.ednevnik.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ocjene")
public class Ocjene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float ocjena;
    private String oblast;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datum;

    @ManyToOne
    @JoinColumn(name = "id_pred", nullable = true)
    private Predmet predmet;

    @ManyToOne
    @JoinColumn(name = "id_kor", nullable = true)
    private Korisnik korisnik;

    public Ocjene(float ocjena, String oblast, LocalDate datum, Predmet predmet,
           Korisnik korisnik) {
        this.ocjena = ocjena;
        this.oblast = oblast;
        this.datum = datum;
        this.predmet = predmet;
        this.korisnik = korisnik;
    }

    public Ocjene(){
        
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

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
