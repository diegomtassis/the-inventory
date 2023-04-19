package org.diegomtassis.inventory.item.domain.impl;


import org.diegomtassis.inventory.item.domain.api.UserFactory;

import java.time.Instant;

/**
 * Very simple {@link UserFactory} implementation.
 * 
 * @author diegomtassis
 *
 */
public class SimpleUserFactory implements UserFactory<SimpleUser> {

	@Override
	public SimpleUser create(String key, Instant creationInstant) {
		return new SimpleUser(key, creationInstant);
	}
}
