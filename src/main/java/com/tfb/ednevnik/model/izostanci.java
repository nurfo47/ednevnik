package com.tfb.ednevnik.model;

import java.time.LocalDate;

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
public class izostanci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate datum;
    @Column(nullable = true)
    private String razlog;
    @Column(nullable = true)
    private boolean opravdanost;

    @ManyToOne
    @JoinColumn(name = "id_raz", nullable = true)
    private razred razred;

    @ManyToOne
    @JoinColumn(name = "id_kor", nullable = true)
    private korisnik korisnik;

    public izostanci(LocalDate datum, String razlog, boolean opravdanost, razred razred,
            korisnik korisnik) {
        this.datum = datum;
        this.razlog = razlog;
        this.opravdanost = opravdanost;
        this.razred = razred;
        this.korisnik = korisnik;
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

    public boolean isOpravdanost() {
        return opravdanost;
    }

    public void setOpravdanost(boolean opravdanost) {
        this.opravdanost = opravdanost;
    }

    public razred getRazred() {
        return razred;
    }

    public void setRazred(razred razred) {
        this.razred = razred;
    }

    public korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
