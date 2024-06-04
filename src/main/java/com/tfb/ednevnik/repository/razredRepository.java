package com.tfb.ednevnik.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfb.ednevnik.model.Razred;
import com.tfb.ednevnik.model.Korisnik;
import java.util.List;



@Repository
public interface razredRepository extends JpaRepository<Razred, Long>{
        Optional<Razred> findById(long id);
        Razred getById(long id);
        List<Razred> findByProfesori(Korisnik profesori);
}
