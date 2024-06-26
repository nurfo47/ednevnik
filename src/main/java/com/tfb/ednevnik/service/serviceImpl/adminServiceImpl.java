package com.tfb.ednevnik.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tfb.ednevnik.admindto.adminDto;
import com.tfb.ednevnik.model.Admin;
import com.tfb.ednevnik.repository.adminRepository;

import com.tfb.ednevnik.service.adminService;

@Service
public class adminServiceImpl implements adminService {

    @Autowired
    private adminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void save(adminDto adminDto) {
        Admin admin = new Admin();
        admin.setUsername(adminDto.getUsername());
        admin.setEmail(adminDto.getEmail());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        admin.setRole(adminDto.getRole());
        adminRepository.save(admin);
    }
    @Override
    public Admin findById(long id) {
        return adminRepository.findById(id);
    }
 


}
