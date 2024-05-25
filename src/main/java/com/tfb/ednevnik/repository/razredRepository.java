package com.tfb.ednevnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfb.ednevnik.model.Razred;


@Repository
public interface razredRepository extends JpaRepository<Razred, Long>{
    Razred findById(long id);
}
