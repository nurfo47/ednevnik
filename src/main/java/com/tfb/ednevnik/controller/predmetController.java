package com.tfb.ednevnik.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Predmet;
import com.tfb.ednevnik.service.korisnikService;
import com.tfb.ednevnik.service.predmetService;


@Controller
public class predmetController {
    
    @Autowired
    private predmetService predmetService;
    @Autowired
    private korisnikService korisnikService;

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

    @GetMapping("/profesor-dashboard/predmeti")
    public String listPredmetiForNastavnik(Model model, Authentication authentication) {
        String loggedUsername = authentication.getName();
        Korisnik korisnik = korisnikService.findKorisnikByUsername(loggedUsername);
        Set<Predmet> predmeti = korisnik.getPredmeti();
        model.addAttribute("predmeti", predmeti);
        model.addAttribute("korisnik", korisnik);
        return "predmeti-for-korisnik";
    }
}
