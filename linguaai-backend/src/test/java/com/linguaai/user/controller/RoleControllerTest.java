package com.linguaai.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linguaai.user.entity.Role;
import com.linguaai.user.exception.RoleNotFoundException;
import com.linguaai.user.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class RoleControllerTest {

    private RoleService service;
    private RoleController controller;
    private ObjectMapper mapper;

    @BeforeEach
    void setup() {
        service = Mockito.mock(RoleService.class);
        controller = new RoleController(service);
        mapper = new ObjectMapper();
    }

    @Test
    void shouldReturnAllRoles() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ADMIN");
        when(service.getAllRoles()).thenReturn(List.of(role));
        ResponseEntity<List<Role>> response = controller.getRoles();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getName()).isEqualTo("ADMIN");
    }

    @Test
    void shouldReturnRoleByName() {
        Role role = new Role();
        role.setId(2L);
        role.setName("USER");
        when(service.findByName("USER")).thenReturn(Optional.of(role));
        ResponseEntity<Role> response = controller.getRoleByName("USER");
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody().getName()).isEqualTo("USER");
    }

    @Test
    void shouldThrowWhenRoleNotFoundByName() {
        when(service.findByName("MISSING")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> controller.getRoleByName("MISSING"))
                .isInstanceOf(RoleNotFoundException.class);
    }

    @Test
    void shouldReturnRoleById() {
        Role role = new Role();
        role.setId(4L);
        role.setName("VIEWER");

        when(service.findById(4L)).thenReturn(Optional.of(role));

        ResponseEntity<Role> response = controller.getRoleById(4L);

        assertThat(response.getBody().getName()).isEqualTo("VIEWER");
    }

    @Test
    void shouldThrowWhenRoleNotFoundById() {
        when(service.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> controller.getRoleById(999L))
                .isInstanceOf(RoleNotFoundException.class);
    }

    @Test
    void shouldUpdateRole() {
        Role updated = new Role();
        updated.setId(5L);
        updated.setName("UPDATED");

        ObjectNode body = mapper.createObjectNode();
        body.put("name", "UPDATED");

        when(service.updateRole(5L, body)).thenReturn(updated);

        ResponseEntity<Role> response = controller.updateRole(5L, body);

        assertThat(response.getBody().getName()).isEqualTo("UPDATED");
    }

    @Test
    void shouldDeleteRole() {
        doNothing().when(service).deleteRole(6L);

        ResponseEntity<Role> response = controller.deleteRole(6L);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        verify(service).deleteRole(6L);
    }
}