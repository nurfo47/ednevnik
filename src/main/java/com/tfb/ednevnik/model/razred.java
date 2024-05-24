package com.tfb.ednevnik.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public razred(String naziv, String skolskaGodina, Set<korisnik> ucenici, Set<korisnik> profesori) {
        this.naziv = naziv;
        this.skolskaGodina = skolskaGodina;
        this.ucenici = ucenici;
        this.profesori = profesori;
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
}
