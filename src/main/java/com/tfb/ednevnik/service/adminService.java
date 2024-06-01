package com.tfb.ednevnik.service;

import com.tfb.ednevnik.admindto.adminDto;
import com.tfb.ednevnik.model.admin;

public interface adminService {
    void save(adminDto adminDto);
    admin findById(long id);
}
