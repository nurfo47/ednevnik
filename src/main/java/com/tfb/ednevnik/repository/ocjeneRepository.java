package com.tfb.ednevnik.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Ocjene;
import com.tfb.ednevnik.model.Predmet;

@Repository
public interface ocjeneRepository extends JpaRepository<Ocjene, Long>{
    Set<Ocjene> getAllById(Long id);
    List<Ocjene> findAllByKorisnikId(Long id);
    List<Ocjene> findByKorisnikAndPredmet(Korisnik korisnik, Predmet predmet);

     @Query("SELECT AVG(o.ocjena) FROM Ocjene o WHERE o.predmet.id = :predmetId AND o.korisnik.id = :korisnikId")
    Double findAverageOcjenaByPredmetIdAndKorisnikId(@Param("predmetId") Long predmetId, @Param("korisnikId") Long korisnikId);
}
