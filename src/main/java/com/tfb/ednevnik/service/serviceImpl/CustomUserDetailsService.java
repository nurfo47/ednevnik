package com.tfb.ednevnik.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.tfb.ednevnik.service.CustomUserDetail;
import com.tfb.ednevnik.model.admin;
import com.tfb.ednevnik.repository.adminRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private adminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        admin admin = adminRepository.findByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("Korisnik nije pronađen: ");
    }
    return new CustomUserDetail(admin);
    
}
}
