package com.tfb.ednevnik.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfb.ednevnik.model.Predmet;
@Repository
public interface predmetRepository extends JpaRepository<Predmet, Long>{
    Set<Predmet> findAllById(Long id);

    
}
