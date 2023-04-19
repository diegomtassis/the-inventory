package org.diegomtassis.inventory.item.domain.impl;

import org.diegomtassis.inventory.Authentication;
import org.diegomtassis.inventory.commons.ServerException;
import org.diegomtassis.inventory.item.domain.api.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

public class InMemoryUserRepositoryTest {

    private InMemoryUserRepository repository;

    private UserFactory<SimpleUser> factory;

    @BeforeEach
    public void setUp() {
        this.repository = new InMemoryUserRepository();
        this.factory = new SimpleUserFactory();
    }

    @Test
    public void testSave_NewUser() {

        String userId = Authentication.SLY;
        Assertions.assertFalse(this.repository.retrieve(userId).isPresent());

        // setup
        SimpleUser user = this.factory.create(userId, Instant.now());

        // exercise
        SimpleUser savedUser = this.repository.save(user);

        // verify
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(savedUser.getKey(), user.getKey());
        Assertions.assertTrue(this.repository.retrieve(user.getKey()).isPresent());
    }

    @Test
    public void testSave_ExistingUser() {

        String userId = Authentication.ARNIE;
        Assertions.assertFalse(this.repository.retrieve(userId).isPresent());

        // setup
        SimpleUser originalUser = this.factory.create(userId, Instant.now());
        SimpleUser savedUser = this.repository.save(originalUser);
        Assertions.assertNotNull(savedUser);

        // exercise
        savedUser.changeName("bar");
        savedUser = this.repository.save(savedUser);

        // verify
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals("bar", savedUser.getName());
    }

    @Test
    public void testSave_NewUserAlreadyExisting_ExceptionIsthrown() {

        String userId = Authentication.BRUCE;
        Assertions.assertFalse(this.repository.retrieve(userId).isPresent());

        // setup
        SimpleUser originalUser = this.factory.create(userId, Instant.now());
        originalUser = this.repository.save(originalUser);
        Assertions.assertNotNull(originalUser);

        // exercise
        try {
            this.repository.save(this.factory.create(userId, Instant.now()));

        } catch (ServerException e){

            // verify
            return;
        }

        Assertions.fail();
    }

    @Test
    public void testList() {

        String userId = Authentication.VAN_DAMME;
        Assertions.assertFalse(this.repository.retrieve(userId).isPresent());

        // setup
        SimpleUser user = this.factory.create(userId, Instant.now());
        user = this.repository.save(user);
        Assertions.assertNotNull(user);

        // exercise
        List<SimpleUser> users = this.repository.list();

        // verify
        Assertions.assertNotNull(users);
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals(userId, users.iterator().next().getKey());
    }

}
