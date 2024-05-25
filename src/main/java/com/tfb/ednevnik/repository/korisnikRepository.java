package com.tfb.ednevnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfb.ednevnik.model.Korisnik;

@Repository
public interface korisnikRepository extends JpaRepository<Korisnik, Long>{
    Korisnik findById(long id);
    Korisnik findByUsername(String username);
    Korisnik findByJmbg(String jmbg);
    Korisnik findByTip(String tip);
}
