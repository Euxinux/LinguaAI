package com.linguaai.user.service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linguaai.commons.AbstractIntegrationTest;
import com.linguaai.user.entity.Role;
import com.linguaai.user.exception.RoleAlreadyExistsException;
import com.linguaai.user.exception.RoleNotFoundException;
import com.linguaai.user.repository.RoleRepository;
import com.linguaai.user.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class RoleServiceTest extends AbstractIntegrationTest {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private RoleService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldCreateAndFindRole() {
        Role role = new Role();
        role.setName("ADMIN_TEST");
        Role saved = service.createNewRole(role);
        assertThat(saved.getId()).isNotNull();
        assertThat(service.findByName("ADMIN_TEST")).isPresent();
    }

    @Test
    void shouldThrowWhenDuplicateRoleCreated() {
        Role role = new Role();
        role.setName("USER");
        service.createNewRole(role);

        Role duplicate = new Role();
        duplicate.setName("USER");

        assertThatThrownBy(() -> service.createNewRole(duplicate))
                .isInstanceOf(RoleAlreadyExistsException.class);
    }

    @Test
    void shouldUpdateRoleName() {
        Role role = new Role();
        role.setName("VIEWER_TEST");
        Role saved = service.createNewRole(role);
        ObjectNode body = mapper.createObjectNode();
        body.put("name", "EDITOR_TEST");
        Role updated = service.updateRole(saved.getId(), body);
        assertThat(updated.getName()).isEqualTo("EDITOR_TEST");
    }

    @Test
    void shouldThrowWhenUpdatingNonExistingRole() {
        ObjectNode body = mapper.createObjectNode();
        body.put("name", "ANY");
        assertThatThrownBy(() -> service.updateRole(999L, body))
                .isInstanceOf(RoleNotFoundException.class);
    }
}