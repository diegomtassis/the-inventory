package com.inventory.domain.api;

import java.time.Instant;

/**
 * User entity.
 * 
 * @author diegomtassis
 *
 */
public interface User {

	/**
	 * @return the user key
	 */
	String getKey();

	/**
	 * Change users name.
	 * 
	 * @param newName
	 */
	void changeName(String newName);

	/**
	 * @return the name
	 */
	String getName();

	/**
	 * @return creation instant
	 */
	Instant getCreationInstant();

	/**
	 * @return last update instant
	 */
	Instant getLastUpdateInstant();

}
