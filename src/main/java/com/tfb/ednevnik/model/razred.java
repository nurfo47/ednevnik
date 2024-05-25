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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="razred")
public class razred {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private String skolskaGodina;

    @OneToMany(mappedBy = "razred")
    private Set<korisnik> ucenici = new HashSet<>();

    @ManyToMany(mappedBy = "razredi")
    private Set<korisnik> profesori = new HashSet<>();

    @OneToMany(mappedBy = "razred", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<izostanci> izostanciList;

    @OneToMany(mappedBy = "razred", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<predmet> predmetiList;

    // Many to many relationship with Predmet
    @ManyToMany
    @JoinTable(
        name = "razred_predmet",
        joinColumns = @JoinColumn(name = "razred_id"),
        inverseJoinColumns = @JoinColumn(name = "predmet_id")
    )
    private List<predmet> predmeti;


    public razred(String naziv, String skolskaGodina, Set<korisnik> ucenici, Set<korisnik> profesori, List<izostanci> izostanciList, List<predmet> predmetiList, List<predmet> predmeti) {
        this.naziv = naziv;
        this.skolskaGodina = skolskaGodina;
        this.ucenici = ucenici;
        this.profesori = profesori;
        this.izostanciList = izostanciList;
        this.predmetiList = predmetiList;
        this.predmeti = predmeti;
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

    public String getSkolskaGodina() {
        return skolskaGodina;
    }

    public void setSkolskaGodina(String skolskaGodina) {
        this.skolskaGodina = skolskaGodina;
    }

    public Set<korisnik> getUcenici() {
        return ucenici;
    }

    public void setUcenici(Set<korisnik> ucenici) {
        this.ucenici = ucenici;
    }

    public Set<korisnik> getProfesori() {
        return profesori;
    }

    public void setProfesori(Set<korisnik> profesori) {
        this.profesori = profesori;
    }

    public List<izostanci> getIzostanciList() {
        return izostanciList;
    }

    public void setIzostanciList(List<izostanci> izostanciList) {
        this.izostanciList = izostanciList;
    }

    public List<predmet> getPredmetiList() {
        return predmetiList;
    }

    public void setPredmetiList(List<predmet> predmetiList) {
        this.predmetiList = predmetiList;
    }

    public List<predmet> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<predmet> predmeti) {
        this.predmeti = predmeti;
    }
}
