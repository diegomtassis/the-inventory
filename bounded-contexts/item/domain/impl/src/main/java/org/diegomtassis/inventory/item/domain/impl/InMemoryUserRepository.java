package org.diegomtassis.inventory.item.domain.impl;


import org.diegomtassis.inventory.commons.Assert;
import org.diegomtassis.inventory.commons.domain.Validate;
import org.diegomtassis.inventory.item.domain.api.UserRepository;

import java.time.Instant;
import java.util.*;

/**
 * {@link UserRepository} implementation in memory.
 * 
 * @author diegomtassis
 *
 */
public class InMemoryUserRepository implements UserRepository<SimpleUser> {

	private Map<String, SimpleUser> users = new HashMap<>();

	@Override
	public SimpleUser save(SimpleUser user) {

		Validate.notNull(user, "User  can not be null");
		Validate.isNotBlank(user.getKey(), "User key can not be blank");

		Optional<SimpleUser> existingUser = this.retrieve(user.getKey());
		if (existingUser.isPresent()) {
			Assert.state(!user.isNew(), "User " + user.getKey() + " is new but it is already in the system.");
			Assert.state(user == existingUser.get(), "Two entities for the same user");
			return user;
		}

		SimpleUser savedUser = new SimpleUser(user.getKey(), user.getName(), user.getCreationInstant());
		savedUser.setPersistenceId(Instant.now().toString());
		this.users.put(user.getKey(), savedUser);

		return savedUser;
	}

	@Override
	public Optional<SimpleUser> retrieve(String key) {

		Validate.isNotBlank(key, "Key can not be empty");
		return Optional.ofNullable(this.users.get(key));
	}

	@Override
	public List<SimpleUser> list() {
		return new ArrayList<>(this.users.values());
	}

}
