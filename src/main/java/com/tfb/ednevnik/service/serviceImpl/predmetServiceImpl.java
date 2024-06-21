package com.tfb.ednevnik.service.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfb.ednevnik.model.Predmet;
import com.tfb.ednevnik.repository.predmetRepository;
import com.tfb.ednevnik.service.predmetService;

@Service
public class predmetServiceImpl implements predmetService{

    @Autowired
    private predmetRepository predmetRepository;

    @Override
    public List<Predmet> getAllPredmet() {
        return predmetRepository.findAll();
    }

    @Override
    public Predmet getPredmetById(Long id) {
        return predmetRepository.findById(id).orElse(null);
    }

    @Override
    public Predmet savePredmet(Predmet predmet) {
        return predmetRepository.save(predmet);
    }

    @Override
    public Set<Predmet> findAllById(Long predmetId) {
        return predmetRepository.findAllById(predmetId);
    }

    @Override
    public List<Predmet> findPredmetByKorisnik(Long korisnikId) {
        return predmetRepository.findKorisnikById(korisnikId);
    }

    @Override
    public List<Predmet> findAllByRazredId(Long razredId) {
        return predmetRepository.findByRazredi_Id(razredId);
    }

    @Override
    public Predmet findByKorisnikId(Long korisnikId) {
        return predmetRepository.findByKorisnikId(korisnikId);
    }
   
    
}
