package com.tfb.ednevnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfb.ednevnik.model.admin;



@Repository
public interface adminRepository extends JpaRepository<admin, Long> {
    admin findByEmail(String email);
    admin findByUsername(String username);
}
