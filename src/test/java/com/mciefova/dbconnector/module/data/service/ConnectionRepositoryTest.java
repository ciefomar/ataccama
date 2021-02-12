package com.mciefova.dbconnector.module.data.service;

import com.mciefova.dbconnector.module.data.model.entities.ConnectionEntity;
import io.github.benas.randombeans.api.EnhancedRandom;
import java.util.HashSet;
import java.util.Set;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * Test class for {@link ConnectionRepository}.
 */
@DataJpaTest
public class ConnectionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ConnectionRepository repository;

    @Test
    public void id() {
        for (int i = 0; i < 10; i++) {
            entityManager.persistAndFlush(createConnection());
        }

        final Set<Long> usedIds = new HashSet<>();

        for (ConnectionEntity connection : repository.findAll()) {
            assertThat(usedIds, not(hasItem(connection.getId())));
            usedIds.add(connection.getId());
        }
    }

    @Test
    public void existsByName() {
        String name = "name";

        entityManager.persistAndFlush(createConnection());
        assertFalse(repository.existsByName(name));

        ConnectionEntity connection = createConnection();
        connection.setName(name);
        entityManager.persistAndFlush(connection);

        assertTrue(repository.existsByName(name));
        assertFalse(repository.existsByName(name + "!"));
    }

    @Test
    public void existsByNameAndIdNot() {
        String name = "name";

        ConnectionEntity connection = createConnection();
        connection.setName(name);
        entityManager.persistAndFlush(connection);
        assertFalse(repository.existsByNameAndIdNot(name, connection.getId()));

        ConnectionEntity another = createConnection();
        entityManager.persistAndFlush(another);
        assertTrue(repository.existsByNameAndIdNot(name, another.getId()));
    }

    private ConnectionEntity createConnection() {
        String[] excludeFields = {"id"};
        return EnhancedRandom
                .random(ConnectionEntity.class, excludeFields);
    }
}
