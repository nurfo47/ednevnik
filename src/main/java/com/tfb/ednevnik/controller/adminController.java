package com.tfb.ednevnik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;

import com.tfb.ednevnik.admindto.adminDto;
import com.tfb.ednevnik.service.adminService;



@Controller
public class adminController {
    @Autowired
    private adminService adminService;

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "register";
    }

    @PostMapping("/registration")
    public String saveAdmin(@ModelAttribute("admin") adminDto adminDto, Model model){
        adminService.save(adminDto);
        model.addAttribute("message", "Registracija uspjesna");
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard(){
        return "admin-dashboard";
    }

    @GetMapping("/user-dashboard")
    public String userDashboard(){
        return "user-dashboard";
    }
}
