package com.tfb.ednevnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfb.ednevnik.model.korisnik;



@Repository
public interface korisnikRepository extends JpaRepository<korisnik, Long>{
    korisnik findById(long id);
    korisnik findByUsername(String username);
    korisnik findByJmbg(String jmbg);
}
