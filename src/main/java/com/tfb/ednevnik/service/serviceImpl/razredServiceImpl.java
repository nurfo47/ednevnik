package com.tfb.ednevnik.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfb.ednevnik.model.Razred;
import com.tfb.ednevnik.repository.razredRepository;
import com.tfb.ednevnik.service.razredService;

@Service
public class razredServiceImpl implements razredService{
    
    @Autowired
    razredRepository razredRepository;

    @Override
    public List<Razred> getAllRazred() {
        return razredRepository.findAll();
    }

    @Override
    public Optional<Razred> getRazredById(Long id) {
        return razredRepository.findById(id);
    }

    @Override
    public Razred saveRazred(Razred razred) {
        return razredRepository.save(razred);
    }

    @Override
    public void deleteRazredById(Long id) {
        razredRepository.deleteById(id);
    }


}
