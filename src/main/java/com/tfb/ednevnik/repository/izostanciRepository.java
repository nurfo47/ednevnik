package com.tfb.ednevnik.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    //Brojac svih izostanaka korisnika
    @Query("SELECT COUNT(i) FROM Izostanci i WHERE i.korisnik.id = :korisnikId AND i.korisnik.tip = 'UCENIK'")
    long countIzostanciByKorisnikIdAndTipUcenik(@Param("korisnikId") Long korisnikId);

    //Brojac opravdanih izostanaka
    @Query("SELECT COUNT(i) FROM Izostanci i WHERE i.korisnik.id = :korisnikId AND i.opravdanost = 'OPRAVDANO'")
    long countOpravdaniIzostanciByKorisnikId(Long korisnikId);

    //Brojac neopravdanih izostanaka
    @Query("SELECT COUNT(i) FROM Izostanci i WHERE i.korisnik.id = :korisnikId AND i.opravdanost = 'NEOPRAVDANO'")
    long countNeopravdaniIzostanciByKorisnikId(Long korisnikId);
}
