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
public class Predmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    
     // Many to many relationship with Razred
    @ManyToMany(mappedBy = "predmeti")
    private List<Razred> razredi;

    @ManyToOne
    @JoinColumn(name = "id_kor", nullable = true)
    private Korisnik korisnik;

    //One to many for table ocjene
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ocjene> ocjeneList;

    public Predmet(String naziv, List<Razred> razredi, com.tfb.ednevnik.model.Korisnik korisnik,
            List<Ocjene> ocjeneList) {
        this.naziv = naziv;
        this.razredi = razredi;
        this.korisnik = korisnik;
        this.ocjeneList = ocjeneList;
    }

    public Predmet(){
        
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

    public List<Razred> getRazredi() {
        return razredi;
    }

    public void setRazredi(List<Razred> razredi) {
        this.razredi = razredi;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public List<Ocjene> getOcjeneList() {
        return ocjeneList;
    }

    public void setOcjeneList(List<Ocjene> ocjeneList) {
        this.ocjeneList = ocjeneList;
    }
}
