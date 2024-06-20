package com.tfb.ednevnik.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfb.ednevnik.model.Izostanci;
import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Razred;
@Repository
public interface izostanciRepository extends JpaRepository<Izostanci, Long>{
    Set<Izostanci> getAllById(Long id);
    List<Izostanci> findAllByKorisnikId(Long id);
    List<Izostanci> findByKorisnikAndRazred(Korisnik korisnik, Razred razred);
    List<Izostanci> findByKorisnik(Korisnik korisnik);
}
