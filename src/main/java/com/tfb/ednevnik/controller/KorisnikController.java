package com.tfb.ednevnik.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Razred;
import com.tfb.ednevnik.service.korisnikService;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;



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

    @GetMapping("/profesor-dashboard")
    public String profesorDashboard(Model model){
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String authenticatedEmail = userDetails.getUsername();
            Korisnik korisnik = korisnikService.findKorisnikByUsername(authenticatedEmail);
            if (korisnik != null) {
                model.addAttribute("korisnik", korisnik);
            } else {
                // Handle user not found case
                System.out.println("User not found with username: " + authenticatedEmail);
            }
        }
    }
        return "profesor-dashboard";
    }

    @GetMapping("/user-dashboard")
    public String userDashboard(Model model){
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String authenticatedEmail = userDetails.getUsername();
            Korisnik korisnik = korisnikService.findKorisnikByUsername(authenticatedEmail);
            if (korisnik != null) {
                model.addAttribute("korisnik", korisnik);
            } else {
                // Handle user not found case
                System.out.println("User not found with username: " + authenticatedEmail);
            }
        }
    }
        return "user-dashboard";
    }

    @GetMapping("/toggleSActivation/{id}")
    public String toggleSActivation(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        korisnikService.toggleActivation(id);
        redirectAttributes.addFlashAttribute("activationChanged", true);
        return "redirect:/korisnici-ucenik";
    }

    @GetMapping("/toggleTActivation/{id}")
    public String toggleTActivation(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        korisnikService.toggleActivation(id);
        redirectAttributes.addFlashAttribute("activationChanged", true);
        return "redirect:/korisnici-nastavnik";
    }

    @GetMapping("/uredi")
    public String editUserForm(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String authenticatedEmail = userDetails.getUsername();
            Korisnik korisnik = korisnikService.findKorisnikByUsername(authenticatedEmail);

            if (korisnik != null){
                model.addAttribute("korisnik", korisnik);
                return "uredi-korisnik";
            }


        }
        return "redirect:/korisnik-profil";
    }

    @SuppressWarnings("deprecation")
    @PostMapping("/uredi-korisnik/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("korisnik") Korisnik updateKorisnik){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String authenticatedUsername = userDetails.getUsername();

            Korisnik korisnik = korisnikService.findKorisnikByUsername(authenticatedUsername);
            Korisnik korisnikToUpdate = korisnikService.findKorisnikById(id);

            if (korisnik.getId() == korisnikToUpdate.getId()){
                //Update user profile information
                korisnikToUpdate.setEmail(updateKorisnik.getEmail());
                korisnikToUpdate.setMobitel(updateKorisnik.getMobitel());
                //Update password if new password is provided
                String novaLozinka = updateKorisnik.getLozinka();
                if (!StringUtils.isEmpty(novaLozinka)){
                    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    String encodedPassword = passwordEncoder.encode(novaLozinka);
                    korisnikToUpdate.setLozinka(encodedPassword);
                }
                korisnikService.save(korisnikToUpdate);
                return "redirect:/korisnik-profil/" + korisnik.getId();

            }else {
                return "redirect:/error";
            }
    }
    return "redirect:/login";
    
}
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id")Long id, Model model){
        Korisnik korisnik = korisnikService.findKorisnikById(id);
        List<Razred> razredi = korisnikService.getAllRazredi();
        model.addAttribute("korisnik", korisnik);
        model.addAttribute("razredi", razredi);
        return "update-korisnik";
    }

    @PostMapping("/update/{id}")
    public String updateKorisnik(@PathVariable("id") Long id, @RequestParam("razredId") Long razredId, RedirectAttributes redirectAttributes) {
        korisnikService.updateKorisnikAndRazred(id, razredId);
        redirectAttributes.addFlashAttribute("message", "Uspješno izmjenjeno");
        return "redirect:/korisnici";
    }
}
