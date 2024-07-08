package com.tfb.ednevnik.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Predmet;
import com.tfb.ednevnik.model.Razred;
import com.tfb.ednevnik.service.korisnikService;
import com.tfb.ednevnik.service.predmetService;
import com.tfb.ednevnik.service.razredService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class razredController {
    
    @Autowired
    private razredService razredService;
    @Autowired
    private korisnikService korisnikService;
    @Autowired
    private predmetService predmetService;

    //Otvaranje stranice za dodavanje razreda
    @GetMapping("/add-razred")
    public String addRazredPage() {
        return "/add-razred";
    }

    //Spremanje podataka iz forme za dodavanje razreda
    @PostMapping("/add-razred")
    public String saveRazred(@ModelAttribute("razred") Razred razred, Model model) {
        razredService.saveRazred(razred);
        model.addAttribute("message", "Dodavanje uspješno");
        
        return "redirect:/razredi?success";
    }

    //Prikaz svih razreda 
    @GetMapping("/razredi")
    public String listAllRazredi(Long razredId, Model model) {
        List<Razred> razredi = razredService.getAllRazred();
        model.addAttribute("razredi", razredi);
        return "razredi";
    }
        
    //Prikaz forme za dodavanje korisnika (ucenika) u razred
     @GetMapping("/razredi/addKorisnici/{id}")
    public String showAddKorisniciToRazred(@PathVariable Long id, Model model) {
        Razred razred = razredService.findById(id);
        List<Korisnik> korisnici = korisnikService.findByTip("UCENIK");
        model.addAttribute("razredId", id); // Proslijedi ID
        model.addAttribute("razred", razred); // Proslijedi objekat Razred
        model.addAttribute("korisnici", korisnici);
        return "dodaj-u-razred";
    }

    //Spremanje korisnika koji su odabrani za dodavanje u razred
    @PostMapping("/razredi/addKorisnici")
    public String addKorisniciToRazred(@RequestParam Long razredId, @RequestParam List<Long> korisnikIds) {
        razredService.addKorisniciToRazred(razredId, korisnikIds);
        return "redirect:/razredi";
    }

    @GetMapping("/razredi/moji-razredi")
    public String listRazrediForNastavnik(Model model, Authentication authentication) {
        String loggedUsername = authentication.getName();
        Korisnik korisnik = korisnikService.findKorisnikByUsername(loggedUsername);
        Set<Razred> razredi = korisnik.getRazredi();
        model.addAttribute("razredi", razredi);
        model.addAttribute("korisnik", korisnik);
        return "razredi-for-korisnik";
    }

    @GetMapping("/razredi/assign-predmeti")
    public String showAssignPredmetiForm(Model model) {
        List<Predmet> predmeti = predmetService.getAllPredmet();
        List<Razred> razredi = razredService.getAllRazred();
        model.addAttribute("predmeti", predmeti);
        model.addAttribute("razredi", razredi);
        return "assignPredmetiRazred";
    }

    @PostMapping("/razredi/assign-predmeti")
    public String assignPredmetiToRazred(@RequestParam Long razredId, @RequestParam List<Long> predmetId) {
        razredService.assignPredmetiToRazred(razredId, predmetId);
        return "redirect:/razredi";
    }


}
    

