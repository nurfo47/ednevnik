package com.tfb.ednevnik.service;

import java.util.List;
import java.util.Set;

import com.tfb.ednevnik.model.Izostanci;
import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Razred;

public interface izostanciService {
    Set<Izostanci> getAllIzostanci(Long id);
    List<Izostanci> findAllIzostanci();
    Izostanci saveIzostanci(Izostanci izostanci);
    List<Izostanci> getIzostanciByKorisnik(Long korisnikId);
    List<Izostanci> findIzostanciByKorisnikAndRazred(Korisnik korisnik, Razred razred);
}
