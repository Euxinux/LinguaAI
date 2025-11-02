package com.linguaai.user.service;

import com.linguaai.user.entity.Role;
import com.linguaai.user.exception.RoleAlreadyExistsException;
import com.linguaai.user.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository roleRepository) {
        this.repository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    public Optional<Role> getRoleId(String name) {
        return repository.findByName(name.toUpperCase());
    }

    public Role createNewRole(Role role) {
        if (repository.findByName(role.getName()).isPresent()) {
            throw new RoleAlreadyExistsException("Role with name " + role.getName() + " already exists.");
        }
        return repository.save(role);
    }
}