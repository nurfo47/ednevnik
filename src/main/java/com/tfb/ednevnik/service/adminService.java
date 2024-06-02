package com.tfb.ednevnik.service;

import com.tfb.ednevnik.admindto.adminDto;
import com.tfb.ednevnik.model.Admin;

public interface adminService {
    void save(adminDto adminDto);
    Admin findById(long id);
}
