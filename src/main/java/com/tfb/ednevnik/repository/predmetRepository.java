package com.tfb.ednevnik.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfb.ednevnik.model.Predmet;
@Repository
public interface predmetRepository extends JpaRepository<Predmet, Long>{
    Set<Predmet> findAllById(Long id);
    @SuppressWarnings("null")
    List<Predmet> findKorisnikById(Long korisnikId);
    List<Predmet> findByRazredi_Id(Long razredId);

    
}
