package com.tfb.ednevnik.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.tfb.ednevnik.service.CustomUserDetail;
import com.tfb.ednevnik.model.Korisnik;
import com.tfb.ednevnik.model.Admin;
import com.tfb.ednevnik.repository.adminRepository;
import com.tfb.ednevnik.repository.korisnikRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private adminRepository adminRepository;

    @Autowired
    private korisnikRepository korisnikRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);
        Korisnik korisnik = korisnikRepository.findByUsername(username);

        if (admin != null) {
            return new CustomUserDetail(admin, null);
        } else if (korisnik != null) {
            if (!korisnik.isActive()) {
                throw new DisabledException("Korisnički račun je deaktiviran!");
            }
            return new CustomUserDetail(null, korisnik);
        } else {
            throw new UsernameNotFoundException("Korisnik nije pronađen: " + username);
        }
        
    }
}
