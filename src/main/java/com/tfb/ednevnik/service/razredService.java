package com.tfb.ednevnik.service;

import java.util.List;
import java.util.Optional;

import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Razred;
public interface razredService {
    List<Razred> getAllRazred();
    Optional<Razred> getRazredById(Long id);
    Razred saveRazred(Razred razred);
    void deleteRazredById(Long id);
    void addKorisniciToRazred(Long razredId, List<Long> korisnikId);
    Razred findById(Long id);
    List<Razred> findByKorisnik(Korisnik korisnik);
}
