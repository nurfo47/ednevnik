package com.tfb.ednevnik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.tfb.ednevnik.admindto.adminDto;
import com.tfb.ednevnik.admindto.korisnikDto;

//import com.tfb.ednevnik.service.adminService;
import com.tfb.ednevnik.service.korisnikService;




@Controller
public class adminController {
   // @Autowired
    //private adminService adminService;

    @Autowired
    private korisnikService korisnikService;

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "register";
    }

    @PostMapping("admin-dashboard/registration")
    public String saveKorisnik(@ModelAttribute("korisnik") korisnikDto korisnikDto, Model model){
        korisnikService.saveKorisnik(korisnikDto);
        model.addAttribute("message", "Registracija uspjesna");
        return "redirect:/admin-dashboard?success";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/admin-dashboard")
    public String adminDashboard(){
        return "admin-dashboard";
    }

    @GetMapping("/korisnici")
    public String pregledKorisnika(){
        return "korisnici";
    }
}
