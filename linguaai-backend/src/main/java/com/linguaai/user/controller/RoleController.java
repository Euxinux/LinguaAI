package com.linguaai.user.controller;

import com.linguaai.user.entity.Role;
import com.linguaai.user.exception.RoleNotFoundException;
import com.linguaai.user.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v2/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roleList = service.getAllRoles();
        return ResponseEntity.ok(roleList);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable("name") String name) {
        Role role = service.getRoleId(name)
                .orElseThrow(() -> new RoleNotFoundException(name));
        return ResponseEntity.ok(role);
    }

    @PostMapping
    public ResponseEntity<Role> createNewRole(@RequestBody Role role) {
        Role saved = service.createNewRole(role);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }
}