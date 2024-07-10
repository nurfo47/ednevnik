package com.tfb.ednevnik.controller;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @GetMapping("/razredi/{razredId}/ucenici/{korisnikId}/ocjene/add")
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

    @GetMapping("/ocjene/{ocjenaId}/edit-ocjena")
    public String showEditOcjenaForm(@PathVariable Long ocjenaId, Model model){
        Ocjene ocjene = ocjeneService.getById(ocjenaId);
        model.addAttribute("ocjena", ocjene);
        return "edit-ocjena";
    }

    @PostMapping("/edit-ocjena/{ocjenaId}")
    public String editOcjena(@PathVariable Long ocjenaId, Model model, Ocjene updatedOcjena){
        Ocjene ocjene = ocjeneService.getById(ocjenaId);
        ocjene.setOcjena(updatedOcjena.getOcjena());
        ocjene.setOblast(updatedOcjena.getOblast());
        ocjene.setDatum(updatedOcjena.getDatum());
        ocjeneService.saveOcjena(ocjene);
        return "redirect:/" + ocjene.getKorisnik().getId() + "/predmeti-ocjene";
    }

}
