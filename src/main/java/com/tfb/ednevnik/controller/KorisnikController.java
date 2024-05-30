package com.tfb.ednevnik.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.ednevnik.model.Korisnik;
//import com.tfb.ednevnik.repository.korisnikRepository;
import com.tfb.ednevnik.service.korisnikService;
import org.springframework.ui.Model;

@Controller
public class KorisnikController {
    
    @Autowired
    private korisnikService korisnikService;
    //list Ucenici
    @GetMapping("/korisnici-ucenik")
    public String listKorisnici(Model model) {
        model.addAttribute("korisnici", korisnikService.getAllKorisnik());
        return "korisnici-ucenik";
    }

    //List Nastavnici
    @GetMapping("/korisnici-nastavnik")
    public String listKorisniciProf(Model model) {
        model.addAttribute("korisnici", korisnikService.getAllKorisnik());
        return "korisnici-nastavnik";
    }

    //Korisnicki profil
    @GetMapping("/korisnik-profil/{id}")
    public String getUserProfile(@PathVariable Long id, Model model) {
        Korisnik korisnik = korisnikService.findKorisnikById(id);
        model.addAttribute("korisnik", korisnik);
        return "korisnik-profil";
    }

    @GetMapping("/toggleActivation/{id}")
    public ResponseEntity<String> toggleActivation(@PathVariable Long id) {
        korisnikService.toggleActivation(id);
        return ResponseEntity.ok("Aktivnost korisničkog računa je uspješno promjenjena!");
    }

    
}
