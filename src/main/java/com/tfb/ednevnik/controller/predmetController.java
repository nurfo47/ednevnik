package com.tfb.ednevnik.controller;
import java.util.ArrayList;
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
import com.tfb.ednevnik.model.Ocjene;
import com.tfb.ednevnik.model.Predmet;
import com.tfb.ednevnik.model.PredmetiWithOcjene;
import com.tfb.ednevnik.model.Razred;
import com.tfb.ednevnik.service.korisnikService;
import com.tfb.ednevnik.service.ocjeneService;
import com.tfb.ednevnik.service.predmetService;
import com.tfb.ednevnik.service.razredService;
@Controller
public class predmetController {
    
    @Autowired
    private predmetService predmetService;
    @Autowired
    private korisnikService korisnikService;
    @Autowired
    private ocjeneService ocjeneService;
    @Autowired
    private razredService razredService;

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

    //Ispis predmeta i ocjena
    @GetMapping("/user-dashboard/predmeti-ocjene")
    public String listPredmetiAndOcjeneForUcenik(Model model) {
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

            // Prepare the combined data
            List<PredmetiWithOcjene> predmetiWithOcjene = new ArrayList<>();
            for (Predmet predmet : predmeti) {
                List<Ocjene> ocjene = ocjeneService.findOcjeneByKorisnikAndPredmet(korisnik, predmet);
                Double averageOcjena = ocjeneService.findAverageOcjenaByPredmetIdAndKorisnikId(predmet.getId(), korisnik.getId());

                PredmetiWithOcjene predmetWithOcjene = new PredmetiWithOcjene();
                predmetWithOcjene.setPredmet(predmet);
                predmetWithOcjene.setOcjene(ocjene);
                predmetWithOcjene.setAverageOcjena(averageOcjena);
                predmetiWithOcjene.add(predmetWithOcjene);
            }

            model.addAttribute("predmetiWithOcjene", predmetiWithOcjene);
        }
        return "ucenik-predmeti-ocjene";
    }
    
    //Ispis predmeta i ocjena za nastavnika
    @GetMapping("/{korisnikId}/predmeti-ocjene")
    public String listPredmetiAndOcjeneForNastavnikAndUcenik(@PathVariable Long korisnikId, Model model, Authentication authentication) {
        String loggedUsername = authentication.getName();
        Korisnik ucenik = korisnikService.findKorisnikById(korisnikId);
        Korisnik nastavnik = korisnikService.findKorisnikByUsername(loggedUsername);
        
        Set<Predmet> predmeti = nastavnik.getPredmeti();
        List<PredmetiWithOcjene> predmetiWithOcjene = new ArrayList<>();
        
        for (Predmet predmet : predmeti) {
            List<Ocjene> ocjene = ocjeneService.findOcjeneByKorisnikAndPredmet(ucenik, predmet);
            Double averageOcjena = ocjeneService.findAverageOcjenaByPredmetIdAndKorisnikId(predmet.getId(), korisnikId);
            PredmetiWithOcjene predmetWithOcjene = new PredmetiWithOcjene(predmet, ocjene, averageOcjena);
            predmetiWithOcjene.add(predmetWithOcjene);
        }
        
        model.addAttribute("predmetiWithOcjene", predmetiWithOcjene);
        model.addAttribute("korisnik", ucenik);
        return "nastavnik-predmeti-ocjene";
    }

    // Prikaz predmeta i ocjena za razrednika i admina
    @GetMapping("/razredi/{razredId}/ucenici/{korisnikId}/predmeti-ocjene")
    public String listPredmetiAndOcjeneForRazrednik(@PathVariable Long razredId, @PathVariable Long korisnikId, Model model) {
        Korisnik korisnik = korisnikService.findKorisnikById(korisnikId);
        Razred razred = razredService.findById(razredId);
        
        List<Predmet> predmeti = predmetService.findAllByRazredId(razredId);
        List<PredmetiWithOcjene> predmetiWithOcjene = new ArrayList<>();
        
        for (Predmet predmet : predmeti) {
            List<Ocjene> ocjene = ocjeneService.findOcjeneByKorisnikAndPredmet(korisnik, predmet);
            Double averageOcjena = ocjeneService.findAverageOcjenaByPredmetIdAndKorisnikId(predmet.getId(), korisnikId);
            PredmetiWithOcjene predmetWithOcjene = new PredmetiWithOcjene(predmet, ocjene, averageOcjena);
            predmetiWithOcjene.add(predmetWithOcjene);
        }
        
        model.addAttribute("predmetiWithOcjene", predmetiWithOcjene);
        model.addAttribute("korisnik", korisnik);
        model.addAttribute("razred", razred);
        return "predmeti-for-ucenik";
    }
}
