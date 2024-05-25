package com.tfb.ednevnik.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="predmet")
public class predmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    
     // Many to many relationship with Razred
    @ManyToMany(mappedBy = "predmeti")
    private List<razred> razredi;

    @ManyToOne
    @JoinColumn(name = "id_kor", nullable = true)
    private korisnik korisnik;

    //One to many for table ocjene
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ocjene> ocjeneList;

    public predmet(String naziv, List<razred> razredi, com.tfb.ednevnik.model.korisnik korisnik,
            List<ocjene> ocjeneList) {
        this.naziv = naziv;
        this.razredi = razredi;
        this.korisnik = korisnik;
        this.ocjeneList = ocjeneList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<razred> getRazredi() {
        return razredi;
    }

    public void setRazredi(List<razred> razredi) {
        this.razredi = razredi;
    }

    public korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public List<ocjene> getOcjeneList() {
        return ocjeneList;
    }

    public void setOcjeneList(List<ocjene> ocjeneList) {
        this.ocjeneList = ocjeneList;
    }
}
