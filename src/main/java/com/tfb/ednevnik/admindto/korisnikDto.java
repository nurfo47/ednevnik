package com.tfb.ednevnik.admindto;

import java.time.LocalDate;


public class korisnikDto {
    private String ime;
    private String prezime;
    private String email; 
    private String username; 
    private String lozinka;
    private String mobitel;
    private String jmbg;
    private LocalDate datum;
    private String tip;    //Uloga korisnika ucenik, profesor, direktor
    
    public korisnikDto(String ime, String prezime, String email, String username, String lozinka, String mobitel,
            String jmbg, LocalDate datum, String tip) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.lozinka = lozinka;
        this.mobitel = mobitel;
        this.jmbg = jmbg;
        this.datum = datum;
        this.tip = tip;
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




}
