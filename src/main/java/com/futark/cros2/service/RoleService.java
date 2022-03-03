package com.futark.cros2.service;


import com.futark.cros2.entity.Role;
import com.futark.cros2.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role createNewRole(Role role) {
        return roleRepo.save(role);
    }

    public List<Role> getRoles(){
        return roleRepo.findAll();
    }
}
