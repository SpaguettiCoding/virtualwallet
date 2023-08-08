package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.models.Role;
import com.alkemy.cysjava.virtualwallet.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findRoleByName(String roleName){
        return roleRepository.findByName(roleName);
    }

}
