package com.linguaai.user.service.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linguaai.user.entity.Role;
import com.linguaai.user.exception.RoleAlreadyExistsException;
import com.linguaai.user.exception.RoleNotFoundException;
import com.linguaai.user.repository.RoleRepository;
import com.linguaai.user.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class RoleServiceUnitTest {

    private RoleRepository repository;
    private RoleService service;
    private ObjectMapper mapper;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(RoleRepository.class);
        service = new RoleService(repository);
        mapper = new ObjectMapper();
    }

    @Test
    void shouldCreateNewRole() {
        Role role = new Role();
        role.setName("ADMIN");

        when(repository.findByName("ADMIN")).thenReturn(Optional.empty());
        when(repository.save(role)).thenReturn(role);
        Role saved = service.createNewRole(role);

        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isEqualTo("ADMIN");
        verify(repository).save(role);
    }

    @Test
    void shouldThrowWhenRoleAlreadyExists() {
        Role role = new Role();
        role.setName("USER");
        when(repository.findByName("USER")).thenReturn(Optional.of(role));
        assertThatThrownBy(() -> service.createNewRole(role)).isInstanceOf(RoleAlreadyExistsException.class);
    }

    @Test
    void shouldFindRoleByName() {
        Role role = new Role();
        role.setName("VIEWER");
        when(repository.findByName("VIEWER")).thenReturn(Optional.of(role));
        Optional<Role> found = service.findByName("VIEWER");

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("VIEWER");
    }

    @Test
    void shouldUpdateRoleName() {
        Role role = new Role();
        role.setId(1L);
        role.setName("OLD");

        when(repository.findById(1L)).thenReturn(Optional.of(role));
        when(repository.findByName("NEW")).thenReturn(Optional.empty());
        when(repository.save(any(Role.class))).thenAnswer(inv -> inv.getArgument(0));
        ObjectNode body = mapper.createObjectNode();
        body.put("name", "NEW");
        Role updated = service.updateRole(1L, body);
        assertThat(updated.getName()).isEqualTo("NEW");
        verify(repository).save(role);
    }

    @Test
    void shouldThrowWhenUpdatingNonExistingRole() {
        when(repository.findById(999L)).thenReturn(Optional.empty());
        ObjectNode body = mapper.createObjectNode();
        body.put("name", "ANY");
        assertThatThrownBy(() -> service.updateRole(999L, body))
                .isInstanceOf(RoleNotFoundException.class);
    }

    @Test
    void shouldDeleteRole() {
        Role role = new Role();
        role.setId(1L);
        role.setName("TEMP");
        when(repository.findById(1L)).thenReturn(Optional.of(role));
        service.deleteRole(1L);
        verify(repository).delete(role);
    }

    @Test
    void shouldThrowWhenDeletingNonExistingRole() {
        when(repository.findById(123L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.deleteRole(123L))
                .isInstanceOf(RoleNotFoundException.class);
    }
}