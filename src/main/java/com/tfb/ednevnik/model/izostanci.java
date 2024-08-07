package com.tfb.ednevnik.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="izostanci")
public class Izostanci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datum;
    @Column(nullable = true)
    private String razlog;
    @Column(nullable = true)
    private String opravdanost;

    @ManyToOne
    @JoinColumn(name = "id_raz", nullable = true)
    private Razred razred;

    @ManyToOne
    @JoinColumn(name = "id_kor", nullable = true)
    private Korisnik korisnik;

    public Izostanci(LocalDate datum, String razlog, String opravdanost, Razred razred,
            Korisnik korisnik) {
        this.datum = datum;
        this.razlog = razlog;
        this.opravdanost = opravdanost;
        this.razred = razred;
        this.korisnik = korisnik;
    }
    public Izostanci(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getRazlog() {
        return razlog;
    }

    public void setRazlog(String razlog) {
        this.razlog = razlog;
    }

    public String getOpravdanost() {
        return opravdanost;
    }

    public void setOpravdanost(String opravdanost) {
        this.opravdanost = opravdanost;
    }

    public Razred getRazred() {
        return razred;
    }

    public void setRazred(Razred razred) {
        this.razred = razred;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
