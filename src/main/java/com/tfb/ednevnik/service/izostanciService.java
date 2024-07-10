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
    Izostanci getById(Long id);
    void updateIzostanci(Izostanci izostanci);
    List<Izostanci> getIzostanciByKorisnik(Long korisnikId);
    List<Izostanci> findIzostanciByKorisnikAndRazred(Korisnik korisnik, Razred razred);
    List<Izostanci> findIzostanciByKorisnik(Korisnik korisnik);
    long countIzostanciByKorisnikId(Long korisnikId);
    long countOpravdaniIzostanciByKorisnikId(Long korisnikId);
    long countNeopravdaniIzostanciByKorisnikId(Long korisnikId);
    void deleteById(Long izostanakId);
}
