package com.tfb.ednevnik.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="predmet")
public class Predmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String naziv;
    private String grupa;
    
     // Many to many relationship with Razred
    @ManyToMany(mappedBy = "predmeti")
    private List<Razred> razredi;

    @ManyToMany(mappedBy = "predmeti")
    private Set<Korisnik> korisnik = new HashSet<>();

    //One to many for table ocjene
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ocjene> ocjeneList;

    public Predmet(String naziv, String grupa, List<Razred> razredi, Set<Korisnik> korisnik,
        List<Ocjene> ocjeneList) {
        this.naziv = naziv;
        this.grupa = grupa;
        this.razredi = razredi;
        this.korisnik = korisnik;
        this.ocjeneList = ocjeneList;
    }

    public Predmet(){
        
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
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

    public Set<Korisnik> getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Set<Korisnik> korisnik) {
        this.korisnik = korisnik;
    }

    public List<Ocjene> getOcjeneList() {
        return ocjeneList;
    }

    public void setOcjeneList(List<Ocjene> ocjeneList) {
        this.ocjeneList = ocjeneList;
    }
}
