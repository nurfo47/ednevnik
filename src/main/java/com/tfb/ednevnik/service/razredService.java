package com.tfb.ednevnik.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tfb.ednevnik.model.razred;
@Service
public interface razredService {
    List<razred> getAllRazred();
    razred getRazredById(Long id);
    razred saveRazred(razred razred);
    void deleteRazredById(Long id);
}
