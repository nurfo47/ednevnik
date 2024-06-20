package com.tfb.ednevnik.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.ednevnik.model.Izostanci;
import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.service.izostanciService;
import com.tfb.ednevnik.service.korisnikService;
import com.tfb.ednevnik.service.razredService;


@Controller
public class izostanciController {
    

    @Autowired
    private izostanciService izostanciService;

    @Autowired
    private korisnikService korisnikService;

    @Autowired
    private razredService razredService;
// Forma za unos izostanaka
    @GetMapping("/razred/{razredId}/ucenici/{korisnikId}/add")
    public String showAddIzostanciForm(@PathVariable Long razredId, @PathVariable Long korisnikId, Model model) {

        Korisnik ucenik = korisnikService.findKorisnikById(korisnikId);
        
        model.addAttribute("ucenik", ucenik);
        model.addAttribute("razredId",razredId);
        model.addAttribute("ucenikId", korisnikId);
        return "add-izostanci";
}

//Save Izostanci
    @PostMapping("/save")
    public String saveIzostanci(@RequestParam Long razredId,
                                @RequestParam Long ucenikId,
                                @RequestParam LocalDate datum,
                                @RequestParam String opravdanost,
                                @RequestParam String razlog) {
        // Create new Izostanci instance and set fields
        Izostanci izostanci = new Izostanci();
        izostanci.setDatum(datum);
        izostanci.setOpravdanost(opravdanost);
        izostanci.setRazlog(razlog);
        izostanci.setRazred(razredService.findById(razredId));
        izostanci.setKorisnik(korisnikService.findKorisnikById(ucenikId));
        
        // Save Izostanci using service
        izostanciService.saveIzostanci(izostanci);
        
        // Redirect to a confirmation page or dashboard
        return "redirect:/razrednik-dashboard";
    }

    //List izostanci za ucenika
    @GetMapping("/user-dashboard/izostanci")
    public String listOcjeneForPredmetForUcenik(Model model) {
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
        
        // Dobiti ocjene za predmete
        List<Izostanci> izostanci = izostanciService.findAllIzostanci();
        
        model.addAttribute("korisnik", korisnik);
        model.addAttribute("izostanci", izostanci);
        return "ucenik-izostanci";
    }
}
