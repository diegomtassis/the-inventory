package org.diegomtassis.inventory.item.domain.impl;

import org.diegomtassis.inventory.commons.domain.Validate;
import org.apache.commons.lang3.StringUtils;
import org.diegomtassis.inventory.item.domain.api.User;

import java.time.Instant;

/**
 * Simple {@link User} implementation.
 * 
 * @author diegomtassis
 *
 */
public class SimpleUser implements User {

	private String persistenceId;

	private String key;

	private String name;

	private Instant creationInstant;

	private Instant lastUpdateInstant;

	/**
	 * Constructor for new users.
	 * 
	 * @param key
	 * @param creationDate
	 */
	public SimpleUser(String key, Instant creationDate) {

		this(key, key, creationDate);
	}

	/**
	 * Constructor for existing users.
	 * 
	 * @param key
	 * @param name
	 * @param creationInstant
	 */
	public SimpleUser(String key, String name, Instant creationInstant) {

		this();

		Validate.isNotBlank(key, "User can not be null");
		this.key = key;

		Validate.isNotBlank(name, "Name can not be null");
		this.name = name;

		Validate.notNull(creationInstant, "Creation date can not be null");
		this.creationInstant = creationInstant;
		this.lastUpdateInstant = this.creationInstant;
	}

	/**
	 * Constructor for persistence.
	 */
	private SimpleUser() {
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Instant getCreationInstant() {
		return this.creationInstant;
	}

	@Override
	public Instant getLastUpdateInstant() {
		return this.lastUpdateInstant;
	}

	@Override
	public void changeName(String newName) {
		this.name = newName;
	}

	public void setPersistenceId(String id) {
		this.persistenceId = id;
	}

	public Boolean isNew() {
		return StringUtils.isBlank(this.persistenceId);
	}
}
