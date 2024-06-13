package com.tfb.ednevnik.controller;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.ednevnik.service.ocjeneService;
import com.tfb.ednevnik.service.predmetService;
import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Ocjene;
import com.tfb.ednevnik.model.Predmet;
import com.tfb.ednevnik.service.korisnikService;

@Controller
public class ocjeneController {
    
    @Autowired
    private ocjeneService ocjeneService;

    @Autowired
    private korisnikService korisnikService;

    @Autowired
    private predmetService predmetService;

    //Prikaz forme za unos ocjene iz određenog predmeta
    @GetMapping("/razredi/{razredId}/ucenici/{korisnikId}/ocjene/new")
    public String showAddOcjenaForm(@PathVariable Long razredId, @PathVariable Long korisnikId, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Korisnik authenticatedKorisnik = korisnikService.findKorisnikByUsername(username);

        //Provjeri da li je korisnik uloge nastavnik, ako nije vrati error stranicu
        if (!authenticatedKorisnik.getTip().equals("NASTAVNIK")) {
            return "error";
        }
        Korisnik korisnik = korisnikService.findKorisnikById(korisnikId);

        //Filtriraj predmete na osnovu korisnika kojima su dodjeljeni
        List<Predmet> allPredmeti = predmetService.findAllByRazredId(razredId);
        List<Predmet> filteredPredmeti = allPredmeti.stream()
            .filter(predmet -> predmet.getKorisnik().contains(authenticatedKorisnik))
            .collect(Collectors.toList());
        model.addAttribute("korisnik", korisnik);
        model.addAttribute("predmeti", filteredPredmeti);
        model.addAttribute("razredId", razredId);
        return "add-ocjena";
    }

    //Spremanje ocjene
    @PostMapping("/razredi/{razredId}/ucenici/{korisnikId}/ocjene")
    public String saveOcjena(@PathVariable Long razredId, @PathVariable Long korisnikId,
                             @RequestParam Long predmetId, @RequestParam float ocjena,
                             @RequestParam String oblast, @RequestParam LocalDate datum) {
        Ocjene newOcjena = new Ocjene();
        newOcjena.setOcjena(ocjena);
        newOcjena.setOblast(oblast);
        newOcjena.setDatum(datum);
        newOcjena.setPredmet(predmetService.getPredmetById(predmetId));
        newOcjena.setKorisnik(korisnikService.findKorisnikById(korisnikId));
        ocjeneService.saveOcjena(newOcjena);
        return "redirect:/razredi/" + razredId + "/ucenici";
    }

    //Prikaz svih ocjena iz predmeta za jednog ucenika od strane admina
    @GetMapping("/admin-dashboard/{korisnikId}/predmeti/{predmetId}/ocjene")
    public String listOcjeneForPredmet(@PathVariable Long korisnikId, @PathVariable Long predmetId, Model model) {
        // Dohvati korisnika i predmet
        Korisnik korisnik = korisnikService.findKorisnikById(korisnikId);
        Predmet predmet = predmetService.getPredmetById(predmetId);
        
        // Dohvati sve ocjene za korisnika i predmet
        List<Ocjene> ocjene = ocjeneService.findOcjeneByKorisnikAndPredmet(korisnik, predmet);
        
        // Pass the grades to the view
        model.addAttribute("korisnik", korisnik);
        model.addAttribute("predmet", predmet);
        model.addAttribute("ocjene", ocjene);
        
        return "ocjene-predmeta";
}

@GetMapping("/user-dashboard/predmeti/{predmetId}/ocjene")
public String listOcjeneForPredmetForUcenik(@PathVariable Long predmetId, Model model) {
    // Autentikacija korisnika
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = null;
    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        username = userDetails.getUsername();
    } else if (authentication != null) {
        username = authentication.getName();
    }

    // Dobiti korisnika preko username
    Korisnik korisnik = korisnikService.findKorisnikByUsername(username);
    
    // Dobiti predmet id 
    Predmet predmet = predmetService.getPredmetById(predmetId);
    
    // Dobiti ocjene za predmete
    List<Ocjene> ocjene = ocjeneService.findOcjeneByKorisnikAndPredmet(korisnik, predmet);
    
    model.addAttribute("korisnik", korisnik);
    model.addAttribute("predmet", predmet);
    model.addAttribute("ocjene", ocjene);
    
    return "ocjene-predmeta-ucenika";
}

}
