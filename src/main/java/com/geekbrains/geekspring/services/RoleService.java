package com.geekbrains.geekspring.services;

import com.geekbrains.geekspring.entities.Role;
import com.geekbrains.geekspring.entities.Student;
import com.geekbrains.geekspring.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return (List<Role>)roleRepository.findAll();
    }
}
