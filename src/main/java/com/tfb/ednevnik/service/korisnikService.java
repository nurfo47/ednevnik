package com.tfb.ednevnik.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tfb.ednevnik.model.korisnik;
@Service
public interface korisnikService {
    List<korisnik> getAllKorisnik();
    korisnik saveKorisnik(korisnik korisnik);
    korisnik findKorisnikById(long id);
    void deleteKorisnikById(long id);
    korisnik updateKorisnik(korisnik korisnik);
}
