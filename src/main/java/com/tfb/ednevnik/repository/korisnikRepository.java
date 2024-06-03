package com.tfb.ednevnik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Predmet;


@Repository
public interface korisnikRepository extends JpaRepository<Korisnik, Long>{
    Korisnik findById(long id);
    Korisnik findByUsername(String username);
    Korisnik findByJmbg(String jmbg);
    List<Korisnik> findByTip(String tip);
    Korisnik findByEmail(String email);
    List<Korisnik> findByRazredId(Long id);
    @SuppressWarnings("null")
    List<Korisnik> findAllById(@SuppressWarnings("null") Iterable<Long> ids);
    List<Korisnik> findByPredmeti(Predmet predmet);
    
}
