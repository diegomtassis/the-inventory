
package org.diegomtassis.inventory.item.domain.impl;


import org.diegomtassis.inventory.Authentication;
import org.diegomtassis.inventory.domain.DomainLayerHelper;
import org.diegomtassis.inventory.item.domain.api.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class SimpleItemTest {

	private DomainLayerHelper helper;

	private SimpleItemFactory itemFactory;

	@BeforeEach
	public void setUp() {

		this.itemFactory = new SimpleItemFactory();
		this.helper = new DomainLayerHelper(new SimpleUserFactory(), new InMemoryUserRepository(), this.itemFactory,
				new InMemoryItemRepository());
	}

	@Test
	public void testCreate() throws InterruptedException {

		// setup
		Instant beforeCreation = Instant.now();
		Thread.sleep(1);

		// exercise
		Item item = new SimpleItem(this.helper.addUser(Authentication.SLY));

		// verify
		Assertions.assertTrue(item.getCreationInstant().isAfter(beforeCreation));
	}

	@Test
	public void testUpdateLastUpdateInstant() throws InterruptedException {

		// setup
		Item item = new SimpleItem(this.helper.addUser(Authentication.SLY));
		Instant afterCreation = Instant.now();
		Thread.sleep(1);

		// exercise
		item.addLabel("foo");

		// verify
		Assertions.assertTrue(item.getLastUpdateInstant().isAfter(afterCreation));
	}
}
