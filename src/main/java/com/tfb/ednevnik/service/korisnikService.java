package com.tfb.ednevnik.service;

import java.util.List;

import com.tfb.ednevnik.admindto.korisnikDto;
import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Razred;

public interface korisnikService {
    List<Korisnik> getAllKorisnik();
    List<Razred> getAllRazredi();
    void saveKorisnik(korisnikDto korisnikDto);
    Korisnik findKorisnikById(long id);
    void deleteKorisnikById(long id);
    Korisnik updateKorisnik(Korisnik korisnik);
    Korisnik findKorisnikByEmail(String email);
    Korisnik findKorisnikByUsername(String username);
    Korisnik save(Korisnik korisnik);
    void toggleActivation(Long id);
    void updateKorisnikAndRazred(long korisnikId, long razredId);
}
