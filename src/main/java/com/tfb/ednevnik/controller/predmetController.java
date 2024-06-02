package com.tfb.ednevnik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tfb.ednevnik.model.Predmet;
import com.tfb.ednevnik.service.predmetService;


@Controller
public class predmetController {
    
    @Autowired
    private predmetService predmetService;

    @GetMapping("/add-predmet")
    public String addPredmetPage(){
        return "add-predmet";
    }

    @PostMapping("/add-predmet")
    public String savePredmet(@ModelAttribute("predmet") Predmet predmet, Model model){
        predmetService.savePredmet(predmet);
        model.addAttribute("message", "Uspješno dodano");
        return "redirect:/predmeti?success";
    }

    @GetMapping("/predmeti")
    public String getAllPredmeti(Model model){
        model.addAttribute("predmet", predmetService.getAllPredmet());
        return "predmeti";
    }
}
