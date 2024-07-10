package com.tfb.ednevnik.service.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfb.ednevnik.model.Izostanci;
import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Razred;
import com.tfb.ednevnik.repository.izostanciRepository;
import com.tfb.ednevnik.service.izostanciService;

@Service
public class izostanciServiceImpl implements izostanciService {

    @Autowired
    private izostanciRepository izostanciRepository;
    @Override
    public Set<Izostanci> getAllIzostanci(Long id) {
        return izostanciRepository.getAllById(id);
    }

    @Override
    public Izostanci saveIzostanci(Izostanci izostanci) {
        return izostanciRepository.save(izostanci);
    }

    @Override
    public List<Izostanci> getIzostanciByKorisnik(Long korisnikId) {
        return izostanciRepository.findAllByKorisnikId(korisnikId);
    }

    @Override
    public List<Izostanci> findIzostanciByKorisnikAndRazred(Korisnik korisnik, Razred razred) {
        return izostanciRepository.findByKorisnikAndRazred(korisnik, razred);
    }

    @Override
    public List<Izostanci> findAllIzostanci() {
        return izostanciRepository.findAll();
    }

    @Override
    public List<Izostanci> findIzostanciByKorisnik(Korisnik korisnik) {
        return izostanciRepository.findByKorisnik(korisnik);
    }
    //Brojac svih izostanaka jednog korisnika
    @Override
    public long countIzostanciByKorisnikId(Long korisnikId) {
        return izostanciRepository.countIzostanciByKorisnikIdAndTipUcenik(korisnikId);
    }
    //Brojac opravdanih izostanaka
    @Override
    public long countOpravdaniIzostanciByKorisnikId(Long korisnikId) {
        return izostanciRepository.countOpravdaniIzostanciByKorisnikId(korisnikId);
    }

    @Override
    public long countNeopravdaniIzostanciByKorisnikId(Long korisnikId) {
        return izostanciRepository.countNeopravdaniIzostanciByKorisnikId(korisnikId);
    }

    @Override
    public Izostanci getById(Long id) {
        return izostanciRepository.findById(id).orElse(null);
    }

    @Override
    public void updateIzostanci(Izostanci izostanci) {
        izostanciRepository.save(izostanci);
    }

    @Override
    public void deleteById(Long izostanakId) {
        izostanciRepository.deleteById(izostanakId);
    }
    
}
