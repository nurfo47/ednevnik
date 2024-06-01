package com.tfb.ednevnik.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tfb.ednevnik.admindto.korisnikDto;
import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.service.korisnikService;

import jakarta.persistence.EntityNotFoundException;

import com.tfb.ednevnik.repository.korisnikRepository;
@Service
public class korisnikServiceImpl implements korisnikService{
    
    @Autowired
    private korisnikRepository korisnikRepository;

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
    }

