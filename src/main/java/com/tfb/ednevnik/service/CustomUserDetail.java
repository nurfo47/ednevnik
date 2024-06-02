package com.tfb.ednevnik.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Admin;

public class CustomUserDetail implements UserDetails{

    private Admin admin;
    private Korisnik korisnik;

    
    public CustomUserDetail(Admin admin, Korisnik korisnik) {
        this.admin = admin;
        this.korisnik = korisnik;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (admin != null) {
            return List.of(() -> admin.getRole());
        } else if (korisnik != null) {
            return List.of(() -> korisnik.getTip());
        } else {
            return List.of();
        }
    }

    @Override
    public String getPassword() {
        if (admin != null) {
            return admin.getPassword();
        } else if (korisnik != null) {
            return korisnik.getLozinka();
        } else {
            
            return "Korisnik nije pronađen!"; // or throw an exception, or return a default value
        }
    }
  

    @Override
    public String getUsername() {
        if (admin != null) {
            return admin.getUsername();
        } else if (korisnik != null) {
            return korisnik.getUsername();
        } else {
            
            return "Korisnik nije pronađen!"; // or throw an exception, or return a default value
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
