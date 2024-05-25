package com.tfb.ednevnik.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tfb.ednevnik.model.Razred;
@Service
public interface razredService {
    List<Razred> getAllRazred();
    Razred getRazredById(Long id);
    Razred saveRazred(Razred razred);
    void deleteRazredById(Long id);
}
