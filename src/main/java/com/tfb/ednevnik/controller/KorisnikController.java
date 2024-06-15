package com.tfb.ednevnik.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import com.tfb.ednevnik.model.Predmet;
import com.tfb.ednevnik.model.Razred;

import com.tfb.ednevnik.service.korisnikService;
import com.tfb.ednevnik.service.predmetService;
import com.tfb.ednevnik.service.razredService;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class KorisnikController {
    
    @Autowired
    private korisnikService korisnikService;
    @Autowired
    private predmetService predmetService;
    @Autowired
    private razredService razredService;
    
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

    //List Razrednici
    @GetMapping("/korisnici-razrednik")
    public String listKorisniciRazrednik(Model model) {
        model.addAttribute("korisnici", korisnikService.getAllKorisnik());
        return "korisnici-razrednik";
    }

    //Korisnicki profil
    @GetMapping("/korisnik-profil")
    public String getUserProfile(Model model) {
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

        if (korisnik != null) {
            List<Razred> razred = korisnikService.getAssignedRazredi(korisnik);
            model.addAttribute("korisnik", korisnik);
            model.addAttribute("razred", razred);
        }
        return "korisnik-profil";
    }

    //Pristup korisnickim profilima od strane admina preko id-a
    @GetMapping("/korisnik-profil/{id}")
    public String getUserProfileById(@PathVariable Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

        if (!isAdmin) {
            return "redirect:/access-denied"; // Redirect non-admin users to an access denied page
        }

        Korisnik korisnik = korisnikService.findKorisnikById(id);

        if (korisnik != null) {
            List<Razred> razred = korisnikService.getAssignedRazredi(korisnik);
            List<Razred> razredi = razredService.findRazredByKorisnik(korisnik);

            model.addAttribute("korisnik", korisnik);
            model.addAttribute("razred", razred);
            model.addAttribute("razredi", razredi);
        }

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

    @GetMapping("/razrednik-dashboard")
    public String razrednikDashboard(Model model){
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String authenticatedEmail = userDetails.getUsername();
            Korisnik korisnik = korisnikService.findKorisnikByUsername(authenticatedEmail);
            if (korisnik != null) {
                List<Razred> razredi = razredService.findRazredByKorisnik(korisnik);
                model.addAttribute("korisnik", korisnik);
                model.addAttribute("razredi", razredi);
            } else {
                // Handle user not found case
                System.out.println("User not found with username: " + authenticatedEmail);
            }
        }
    }
        return "razrednik-dashboard";
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

    @GetMapping("/toggleRActivation/{id}")
    public String toggleRActivation(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        korisnikService.toggleActivation(id);
        redirectAttributes.addFlashAttribute("activationChanged", true);
        return "redirect:/korisnici-razrednik";
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
                return "redirect:/korisnik-profil";

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

    //Get all users which are in same class
    @GetMapping("/razred/{id}/ucenici")
    public String getKorisniciByRazred(@PathVariable Long id, Model model) {
        List<Korisnik> korisnici = korisnikService.getKorisniciByRazred(id);
        Razred razred = razredService.findById(id);
        Korisnik razrednik = razredService.findKorisnikByRazred(id);
        model.addAttribute("korisnici", korisnici);
        model.addAttribute("razred", razred);
        model.addAttribute("razrednik", razrednik);
        return "ucenici-razreda";
    }

    //Lista svih ucenika u razredu
    @GetMapping("/razredi/{id}/ucenici")
    public String getUceniciByRazred(@PathVariable Long id, Model model) {
        List<Korisnik> korisnici = korisnikService.getKorisniciByRazred(id);
        Razred razred = razredService.findById(id);
        model.addAttribute("korisnici", korisnici);
        model.addAttribute("razred", razred);
        return "ucenici-u-razredu";
    }

    //Assign Predmet to Nastavnik
    @GetMapping("/assign-predmeti")
    public String showAssignPredmetiForm(Model model) {
        List<Korisnik> nastavnici = korisnikService.getAllKorisnik(); // Get all Korisnici
        List<Predmet> predmeti = predmetService.getAllPredmet(); // Get all Predmeti
        model.addAttribute("korisnici", nastavnici);
        model.addAttribute("predmeti", predmeti);
        return "assign-predmeti"; // Return the HTML form
}
    @PostMapping("/korisnici-nastavnik/assign-predmeti")
    public String assignPredmetiToKorisnik(@RequestParam Long korisnikId, @RequestParam List<Long> predmetId) {
        korisnikService.assignPredmetiToKorisnik(korisnikId, predmetId); // Call the service method to handle the assignment
        return "redirect:/korisnici-nastavnik";
    }

    //List all users who have assigned selected subject
    @GetMapping("/predmeti/{predmetId}/korisnici")
    public String listKorisniciForPredmet(@PathVariable Long predmetId, Model model) {

        Predmet predmet = predmetService.getPredmetById(predmetId);
        List<Korisnik> korisnici = korisnikService.findByPredmet(predmet);
        model.addAttribute("predmet", predmet);
        model.addAttribute("korisnici", korisnici);
        
        return "list-korisnici-for-predmet";
    }

    @GetMapping("/assign-razredi")
    public String showAssignRazrediForm(Model model) {
        List<Korisnik> nastavnici = korisnikService.getAllKorisnik();
        List<Razred> razredi = razredService.getAllRazred();
        model.addAttribute("korisnici", nastavnici);
        model.addAttribute("razredi", razredi);
        return "assign-razredi";
    }

    @PostMapping("/korisnici-nastavnik/assign-razredi")
    public String assignRazrediToKorisnik(@RequestParam Long korisnikId, @RequestParam List<Long> razredId) {
        korisnikService.assignRazrediToKorisnik(korisnikId, razredId);
        return "redirect:/korisnici-nastavnik";
    }
    

}
