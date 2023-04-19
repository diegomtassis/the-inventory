package org.diegomtassis.inventory.item.domain.api;

import java.util.List;
import java.util.Optional;

/**
 * Repository for users.
 * 
 * @author diegomtassis
 *
 * @param <T>
 */
public interface UserRepository<T extends User> {

	/**
	 * Saves an user.
	 * 
	 * @param user
	 * @return the saved user
	 */
	T save(T user);

	/**
	 * Retrieves an user.
	 * 
	 * @param key
	 * @return the retrieved user
	 */
	Optional<T> retrieve(String key);

	/**
	 * @return all the existing users
	 */
	List<T> list();

}
