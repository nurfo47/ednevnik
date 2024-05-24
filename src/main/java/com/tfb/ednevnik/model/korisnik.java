package com.tfb.ednevnik.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="korisnik")
public class korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ime;
    private String prezime;

    @Column(unique=true)
    private String email; 

    @Column(unique = true)
    private String username; 
    private String lozinka;

    @Column(nullable = true)
    private String mobitel;

    @Column(unique = true)
    private String jmbg;

    private LocalDate datum;

    
    private String tip;    //Uloga korisnika ucenik, profesor, direktor


    @ManyToOne
    @JoinColumn(name = "id_raz", nullable = true)
    private razred razred;

    @ManyToMany
    @JoinTable(
        name = "Nas_Raz",
        joinColumns = @JoinColumn(name = "korisnik_id"),
        inverseJoinColumns = @JoinColumn(name = "razred_id")
    )
    private Set<razred> razredi = new HashSet<>();

    public korisnik(String ime, String prezime, String email, String username, String lozinka, String mobitel,
            String jmbg, LocalDate datum, String tip, com.tfb.ednevnik.model.razred razred,
            Set<com.tfb.ednevnik.model.razred> razredi) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.lozinka = lozinka;
        this.mobitel = mobitel;
        this.jmbg = jmbg;
        this.datum = datum;
        this.tip = tip;
        this.razred = razred;
        this.razredi = razredi;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getMobitel() {
        return mobitel;
    }

    public void setMobitel(String mobitel) {
        this.mobitel = mobitel;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public razred getRazred() {
        return razred;
    }

    public void setRazred(razred razred) {
        this.razred = razred;
    }

    public Set<razred> getRazredi() {
        return razredi;
    }

    public void setRazredi(Set<razred> razredi) {
        this.razredi = razredi;
    }
}
