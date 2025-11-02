package com.linguaai.user.repository;

import com.linguaai.commons.AbstractPostgresTest;
import com.linguaai.user.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

@DataJpaTest
@ActiveProfiles("test")
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoleRepositoryTest extends AbstractPostgresTest {

    @Autowired
    private RoleRepository repository;

    @Test
    void shouldSaveAndFindById() {
        Role role = new Role();
        role.setName("ADMIN_TEST");
        Role saved = repository.save(role);
        Optional<Role> found = repository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("ADMIN_TEST");
    }

    @Test
    void shouldFindByName() {
        Role role = new Role();
        role.setName("USER_TEST");
        repository.save(role);
        Optional<Role> found = repository.findByName("USER_TEST");
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("USER_TEST");
    }

    @Test
    void shouldReturnEmptyWhenNameNotExists() {
        Optional<Role> found = repository.findByName(anyString());
        assertThat(found).isEmpty();
    }
}