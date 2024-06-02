package com.tfb.ednevnik.service;

import java.util.List;

import com.tfb.ednevnik.model.Predmet;

public interface predmetService {
    List<Predmet> getAllPredmet();
    Predmet getPredmetById(Long id);
    Predmet savePredmet(Predmet predmet);
}
