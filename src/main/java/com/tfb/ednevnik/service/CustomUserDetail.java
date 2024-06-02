package com.tfb.ednevnik.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
            return List.of(new SimpleGrantedAuthority("ROLE_" + admin.getRole()));
        } else if (korisnik != null) {
            return List.of(new SimpleGrantedAuthority("ROLE_" + korisnik.getTip()));
        } else {
            return List.of();
        }
    }

    @Override
    public String getPassword() {
        return admin != null ? admin.getPassword() : korisnik.getLozinka();
    }
  

    @Override
    public String getUsername() {
        return admin != null ? admin.getUsername() : korisnik.getUsername();
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
