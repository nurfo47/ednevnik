package com.tfb.ednevnik.service;

import java.util.List;
import java.util.Set;

import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Ocjene;
import com.tfb.ednevnik.model.Predmet;

public interface ocjeneService {
    Set<Ocjene> getAllOcjene(Long id);
    void saveOcjena(Ocjene ocjena);
    List<Ocjene> getOcjeneByKorisnik(Long korisnikId);
    List<Ocjene> findOcjeneByKorisnikAndPredmet(Korisnik korisnik, Predmet predmet);

    //Get average ocjena for predmet
    Double findAverageOcjenaByPredmetIdAndKorisnikId(Long predmetId, Long korisnikId);
    
}
