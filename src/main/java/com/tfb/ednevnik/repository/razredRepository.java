package com.tfb.ednevnik.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tfb.ednevnik.model.Razred;
import com.tfb.ednevnik.model.Korisnik;

import java.util.List;



@Repository
public interface razredRepository extends JpaRepository<Razred, Long>{
        Optional<Razred> findById(long id);
        Razred getById(long id);
        List<Razred> findByProfesori(Korisnik profesori);

        @SuppressWarnings("null")
        @EntityGraph(attributePaths = {"profesori"})
        List<Razred> findAll();
        @Query("SELECT DISTINCT r FROM Razred r JOIN r.profesori k WHERE k.tip = 'RAZREDNIK' AND r.id = :razredId")
        List<Razred> findAllWithRazrednik(@Param("razredId") long razredId);
        
}
