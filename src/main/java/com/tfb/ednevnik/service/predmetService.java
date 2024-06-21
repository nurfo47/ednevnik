package com.tfb.ednevnik.service;

import java.util.List;
import java.util.Set;

import com.tfb.ednevnik.model.Predmet;

public interface predmetService {
    List<Predmet> getAllPredmet();
    Predmet getPredmetById(Long id);
    Predmet savePredmet(Predmet predmet);
    Set<Predmet> findAllById(Long predmetId);
    List<Predmet> findPredmetByKorisnik(Long korisnikId);
    List<Predmet> findAllByRazredId(Long razredId);
    Predmet findByKorisnikId(Long korisnikId);
    
}
