package com.tfb.ednevnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfb.ednevnik.model.razred;


@Repository
public interface razredRepository extends JpaRepository<razred, Long>{
    razred findById(long id);
}
