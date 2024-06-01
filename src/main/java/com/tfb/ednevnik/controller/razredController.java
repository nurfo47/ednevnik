package com.tfb.ednevnik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.tfb.ednevnik.model.Razred;
import com.tfb.ednevnik.service.razredService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class razredController {
    
    @Autowired
    private razredService razredService;

    @GetMapping("/add-razred")
    public String addRazredPage() {
        return "/add-razred";
    }

    @PostMapping("/add-razred")
    public String saveRazred(@ModelAttribute("razred") Razred razred, Model model) {
        razredService.saveRazred(razred);
        model.addAttribute("message", "Dodavanje uspješno");
        
        return "redirect:/razredi?success";
    }

    @GetMapping("/razredi")
    public String listAllRazredi(Model model) {
        model.addAttribute("razred", razredService.getAllRazred());
        return "/razredi";
    }
    
    
    
}
