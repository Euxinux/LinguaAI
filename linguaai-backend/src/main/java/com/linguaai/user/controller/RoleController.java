package com.linguaai.user.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.linguaai.user.entity.Role;
import com.linguaai.user.exception.RoleNotFoundException;
import com.linguaai.user.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("${api.url}/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        log.info("Request received: GET /roles - Fetching all roles");
        List<Role> roleList = service.getAllRoles();
        log.debug("Number of roles found: {}", roleList.size());
        return ResponseEntity.ok(roleList);
    }

    @GetMapping("/names/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable("name") String name) {
        log.info("Request received: GET /roles/by-name/{} - Fetching role by name", name);
        Role role = service.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("NAME: " + name));
        log.debug("Role found: id={}, name={}", role.getId(), role.getName());
        return ResponseEntity.ok(role);
    }

    @PostMapping
    public ResponseEntity<Role> createNewRole(@RequestBody Role role) {
        log.info("Request received: POST /roles - Creating new role with name={}", role.getName());
        Role saved = service.createNewRole(role);
        log.info("New role created successfully with id={} and name={}", saved.getId(), saved.getName());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id) {
        log.info("Request received: GET /roles/{} - Fetching role by ID", id);
        Role role = service.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("ID: " + id));
        log.debug("Role found: id={}, name={}", role.getId(), role.getName());
        return ResponseEntity.ok(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(
            @PathVariable("id") Long id,
            @RequestBody JsonNode body) {
        log.info("Request received: PUT /roles/{} - Updating role", id);
        Role updated = service.updateRole(id, body);
        log.debug("Role updated successfully: id={}, name={}", updated.getId(), updated.getName());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteRole(
            @PathVariable("id") Long id) {
        log.info("Request received: DELETE /roles/{} - Deleting role", id);
        service.deleteRole(id);
        log.debug("Role deleted successfully: id={}", id);
        return ResponseEntity.noContent().build();
    }
}