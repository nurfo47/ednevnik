package com.tfb.ednevnik.service;

import java.util.List;

import com.tfb.ednevnik.admindto.korisnikDto;
import com.tfb.ednevnik.model.korisnik;

public interface korisnikService {
    List<korisnik> getAllKorisnik();
    void saveKorisnik(korisnikDto korisnikDto);
    korisnik findKorisnikById(long id);
    void deleteKorisnikById(long id);
    korisnik updateKorisnik(korisnik korisnik);
}
