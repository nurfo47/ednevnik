package com.tfb.ednevnik.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tfb.ednevnik.admindto.korisnikDto;
import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Razred;
import com.tfb.ednevnik.service.korisnikService;

import jakarta.persistence.EntityNotFoundException;

import com.tfb.ednevnik.repository.korisnikRepository;
import com.tfb.ednevnik.repository.razredRepository;
@Service
public class korisnikServiceImpl implements korisnikService{
    
    @Autowired
    private korisnikRepository korisnikRepository;
    @Autowired
    private razredRepository razredRepository;

    
    public korisnikServiceImpl(korisnikRepository korisnikRepository, razredRepository razredRepository) {
        this.korisnikRepository = korisnikRepository;
        this.razredRepository = razredRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveKorisnik(korisnikDto korisnikDto) {
        Korisnik korisnik = new Korisnik();
        korisnik.setIme(korisnikDto.getIme());
        korisnik.setPrezime(korisnikDto.getPrezime());
        korisnik.setEmail(korisnikDto.getEmail());
        korisnik.setUsername(korisnikDto.getUsername());
        korisnik.setLozinka(passwordEncoder.encode(korisnikDto.getLozinka()));
        korisnik.setMobitel(korisnikDto.getMobitel());
        korisnik.setDatum(korisnikDto.getDatum());
        korisnik.setJmbg(korisnikDto.getJmbg());
        korisnik.setTip(korisnikDto.getTip());
        if (korisnikDto.getRazredId() != null) {
            Razred razred = razredRepository.findById(korisnikDto.getRazredId()).orElse(null);
            korisnik.setRazred(razred);
        }
        korisnikRepository.save(korisnik);

}

    @Override
    public List<Korisnik> getAllKorisnik() {
        return korisnikRepository.findAll();
    }

    @Override
    public Korisnik findKorisnikById(long id) {
        return korisnikRepository.findById(id);
    }

    @Override
    public void deleteKorisnikById(long id) {
        korisnikRepository.deleteById(id);
    }

    @Override
    public Korisnik updateKorisnik(Korisnik korisnik) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateKorisnik'");
    }


    @Override
    public void toggleActivation(Long id) {
        Optional<Korisnik> optionalKorisnik = korisnikRepository.findById(id);
        if (optionalKorisnik.isPresent()) {
            Korisnik korisnik = optionalKorisnik.get();
            korisnik.setActive(!korisnik.isActive()); // Promjeni status
            korisnikRepository.save(korisnik); // Spremi
        } else {
            throw new EntityNotFoundException("Korisnik sa " + id + " nije pronađen");
        }
    }

    @Override
    public Korisnik findKorisnikByEmail(String email) {
        return korisnikRepository.findByEmail(email);
    }

    @Override
    public Korisnik findKorisnikByUsername(String username) {
        return korisnikRepository.findByUsername(username);
    }

    @Override
    public Korisnik save(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    @Override
    public void updateKorisnikAndRazred(long korisnikId, long razredId) {
        Korisnik korisnik = korisnikRepository.findById(korisnikId);
        Razred razred = razredRepository.getById(razredId);
        korisnik.setRazred(razred);
        korisnikRepository.save(korisnik);
    }

    @Override
    public List<Razred> getAllRazredi() {
        return razredRepository.findAll();
    }

    @Override
    public List<Korisnik> getKorisniciByRazred(Long razredId) {
        return korisnikRepository.findByRazredId(razredId);
    }
    }

