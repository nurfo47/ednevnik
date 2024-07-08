package com.tfb.ednevnik.service.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Ocjene;
import com.tfb.ednevnik.model.Predmet;
import com.tfb.ednevnik.repository.ocjeneRepository;
import com.tfb.ednevnik.service.ocjeneService;

@Service
public class ocjeneServiceImpl implements ocjeneService {

    @Autowired
    private ocjeneRepository ocjeneRepository;
    @Override
    public Set<Ocjene> getAllOcjene(Long id) {
        return ocjeneRepository.getAllById(id);
    }

    @Override
    public void saveOcjena(Ocjene ocjena) {
        ocjeneRepository.save(ocjena);
    }

    @Override
    public List<Ocjene> getOcjeneByKorisnik(Long korisnikId) {
       return ocjeneRepository.findAllByKorisnikId(korisnikId);
    }

    @Override
    public List<Ocjene> findOcjeneByKorisnikAndPredmet(Korisnik korisnik, Predmet predmet) {
        return ocjeneRepository.findByKorisnikAndPredmet(korisnik, predmet);
    }

    @Override
    public Double findAverageOcjenaByPredmetIdAndKorisnikId(Long predmetId, Long korisnikId) {
        return ocjeneRepository.findAverageOcjenaByPredmetIdAndKorisnikId(predmetId, korisnikId);
    }

    @Override
    public void updateOcjena(Ocjene ocjena) {
        ocjeneRepository.save(ocjena);
    }

    @Override
    public Ocjene getById(Long id) {
        return ocjeneRepository.findById(id).orElse(null);
    }

    
    
}
