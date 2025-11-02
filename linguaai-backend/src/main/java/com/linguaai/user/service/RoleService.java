package com.linguaai.user.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.linguaai.user.entity.Role;
import com.linguaai.user.exception.RoleAlreadyExistsException;
import com.linguaai.user.exception.RoleNotFoundException;
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

    public Optional<Role> findByName(String name) {
        return repository.findByName(name.toUpperCase());
    }

    public Role createNewRole(Role role) {
        if (repository.findByName(role.getName()).isPresent()) {
            throw new RoleAlreadyExistsException("name " + role.getName());
        }
        return repository.save(role);
    }

    public Optional<Role> findById(Long id) {
        return repository.findById(id);
    }

    public Role updateRole(Long id, JsonNode body) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("ID: " + id));
        if (body.has("name")) {
            String name = body.get("name").asText().toUpperCase();
            if (repository.findByName(name).isPresent()) {
                throw new RoleAlreadyExistsException("ID: " + id);
            }
            role.setName(body.get("name").asText().toUpperCase());
        }
        return repository.save(role);
    }

    public void deleteRole(Long id) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("ID: " + id));
        repository.delete(role);
    }
}