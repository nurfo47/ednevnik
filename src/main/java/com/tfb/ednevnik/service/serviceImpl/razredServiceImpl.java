package com.tfb.ednevnik.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Razred;
import com.tfb.ednevnik.repository.korisnikRepository;
import com.tfb.ednevnik.repository.razredRepository;
import com.tfb.ednevnik.service.razredService;

@Service
public class razredServiceImpl implements razredService{
    
    @Autowired
    private razredRepository razredRepository;
    @Autowired
    private korisnikRepository korisnikRepository;

    @Override
    public List<Razred> getAllRazred() {
        return razredRepository.findAll();
    }

    @Override
    public Optional<Razred> getRazredById(Long id) {
        return razredRepository.findById(id);
    }

    @Override
    public Razred saveRazred(Razred razred) {
        return razredRepository.save(razred);
    }

    @Override
    public void deleteRazredById(Long id) {
        razredRepository.deleteById(id);
    }

    @Override
    public void addKorisniciToRazred(Long razredId, List<Long> korisnikId) {
        Razred razred = findById(razredId);
        List<Korisnik> korisnici = korisnikRepository.findAllById(korisnikId);
        for (Korisnik korisnik : korisnici){
            if ("UCENIK".equals(korisnik.getTip())) {
                korisnik.setRazred(razred);
            }
        }
        korisnikRepository.saveAll(korisnici);
    }

    @Override
    public Razred findById(Long id) {
        return razredRepository.findById(id).orElseThrow(() -> new RuntimeException("Razred nije pronađen"));
    }

    @Override
    public List<Razred> findByKorisnik(Korisnik korisnik) {
        return razredRepository.findByProfesori(korisnik);
    }


}
