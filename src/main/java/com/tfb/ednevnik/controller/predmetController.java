package com.tfb.ednevnik.controller;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    //Prikaz predemta za ucenike u razredu kojima nastavnik predaje
    @GetMapping("/{korisnikId}/predmeti")
    public String listRazredniPredmeti(@PathVariable Long korisnikId, Model model, Authentication authentication) {
        String loggedUsername = authentication.getName();
        Korisnik ucenik = korisnikService.findKorisnikById(korisnikId);
        Korisnik korisnik = korisnikService.findKorisnikByUsername(loggedUsername);
        Set<Predmet> predmeti = korisnik.getPredmeti();
        model.addAttribute("predmeti", predmeti);
        model.addAttribute("korisnik", ucenik);
        return "predmeti-for-korisnik";
    }

    @GetMapping("/razredi/{razredId}/ucenici/{korisnikId}/predmeti")
    public String listPredmetiForUcenikInRazred(@PathVariable Long razredId, @PathVariable Long korisnikId, Model model) {
        List<Predmet> predmeti = predmetService.findAllByRazredId(razredId);
        model.addAttribute("predmeti", predmeti);
        model.addAttribute("korisnikId", korisnikId);
        model.addAttribute("razredId", razredId);
        return "predmeti-for-ucenik";
    }


    @GetMapping("/user-dashboard/predmeti")
    public String listPredmetiForUcenik(Model model) {
        // Get the authenticated user
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = null;
    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        username = userDetails.getUsername();
    } else if (authentication != null) {
        username = authentication.getName();
    }

    // Fetch the Korisnik entity using the username
    Korisnik korisnik = korisnikService.findKorisnikByUsername(username);

    if (korisnik != null && korisnik.getRazred() != null) {
        Long razredId = korisnik.getRazred().getId();
        List<Predmet> predmeti = predmetService.findAllByRazredId(razredId);
        model.addAttribute("predmeti", predmeti);
    }
    return "ucenik-predmeti";
    }
    
}
