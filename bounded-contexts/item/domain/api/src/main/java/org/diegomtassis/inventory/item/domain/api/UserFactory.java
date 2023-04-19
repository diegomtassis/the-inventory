package org.diegomtassis.inventory.item.domain.api;

import java.time.Instant;

/**
 * Factory for users.
 * 
 * @author diegomtassis
 *
 * @param <T>
 */
public interface UserFactory<T extends User> {

	/**
	 * Creates a new user.
	 * 
	 * @param key
	 * @param creationInstant
	 * @return a new user
	 */
	T create(String key, Instant creationInstant);
}
