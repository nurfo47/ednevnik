package com.tfb.ednevnik.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class Razred {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = true)
    private String naziv;
    @Column(nullable = true)
    private String skolskaGodina;

    @OneToMany(mappedBy = "razred")
    private Set<Korisnik> ucenici = new HashSet<>();

    @ManyToMany(mappedBy = "razred")
    private Set<Korisnik> profesori = new HashSet<>();

    @OneToMany(mappedBy = "razred", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Izostanci> izostanciList;

    @OneToMany(mappedBy = "razredi", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Predmet> predmetList;

    // Many to many relationship with Predmet
    @ManyToMany
    @JoinTable(
        name = "razred_predmet",
        joinColumns = @JoinColumn(name = "razred_id"),
        inverseJoinColumns = @JoinColumn(name = "predmet_id")
    )
    private List<Predmet> predmeti;


    public Razred(String naziv, String skolskaGodina, Set<Korisnik> ucenici, Set<Korisnik> profesori, List<Izostanci> izostanciList, List<Predmet> predmetList, List<Predmet> predmeti) {
        this.naziv = naziv;
        this.skolskaGodina = skolskaGodina;
        this.ucenici = ucenici;
        this.profesori = profesori;
        this.izostanciList = izostanciList;
        this.predmetList = predmetList;
        this.predmeti = predmeti;
    }

    public Razred(){
        
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

    public Set<Korisnik> getUcenici() {
        return ucenici;
    }

    public void setUcenici(Set<Korisnik> ucenici) {
        this.ucenici = ucenici;
    }

    public Set<Korisnik> getProfesori() {
        return profesori;
    }

    public void setProfesori(Set<Korisnik> profesori) {
        this.profesori = profesori;
    }

    public List<Izostanci> getIzostanciList() {
        return izostanciList;
    }

    public void setIzostanciList(List<Izostanci> izostanciList) {
        this.izostanciList = izostanciList;
    }

    public List<Predmet> getPredmetiLst() {
        return predmetList;
    }

    public void setPredmetList(List<Predmet> predmetList) {
        this.predmetList = predmetList;
    }

    public List<Predmet> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<Predmet> predmeti) {
        this.predmeti = predmeti;
    }
}
