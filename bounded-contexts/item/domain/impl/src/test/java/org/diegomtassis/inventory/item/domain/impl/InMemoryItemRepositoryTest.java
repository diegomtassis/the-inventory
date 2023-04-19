package org.diegomtassis.inventory.item.domain.impl;

import org.apache.commons.lang3.StringUtils;
import org.diegomtassis.inventory.Authentication;
import org.diegomtassis.inventory.item.domain.api.ItemFactory;
import org.diegomtassis.inventory.item.domain.api.ItemSearchCriteria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author diegomtassis
 *
 */
public class InMemoryItemRepositoryTest {

	private InMemoryItemRepository repository;

	private ItemFactory<SimpleItem> factory;

	@BeforeEach
	public void setUp() {
		this.repository = new InMemoryItemRepository();
		this.factory = new SimpleItemFactory();
	}

	@Test
	public void testSave_NewItem() {

		// setup
		SimpleItem itemToBeSaved = this.factory.create(new SimpleUser(Authentication.SLY, Instant.now()));

		// exercise
		SimpleItem savedItem = this.repository.save(itemToBeSaved);

		// verify
		Assertions.assertNotNull(savedItem);
		Assertions.assertFalse(StringUtils.isBlank(savedItem.getId()));
		Assertions.assertTrue(this.repository.retrieve(savedItem.getId()).isPresent());
	}

	@Test
	public void testSave_ExistingItem() {

		// setup
		SimpleItem originalItem = this.repository
				.save(this.factory.create(new SimpleUser(Authentication.ARNIE, Instant.now())));
		originalItem.addLabel("foo").addLabel("bar");
		originalItem.setProperty("fooProp", "fooValue");

		// exercise
		SimpleItem savedItem = this.repository.save(originalItem);

		// verify
		Assertions.assertNotNull(savedItem);
		Assertions.assertFalse(StringUtils.isBlank(savedItem.getId()));
		Assertions.assertTrue(this.repository.retrieve(originalItem.getId()).isPresent());
		Assertions.assertTrue(savedItem.getLabels().contains("foo"));
		Assertions.assertTrue(savedItem.getLabels().contains("bar"));
		Assertions.assertEquals(originalItem.getProperties().get("fooProp"), savedItem.getProperties().get("fooProp"));
	}

	@Test
	public void testList() {

		// setup

		// exercise
		long numberOfItemsBefore = this.repository.list(null, null).getTotalElements();
		this.repository.save(this.factory.create(new SimpleUser(Authentication.DOLPH, Instant.now())));
		List<SimpleItem> items = this.repository.list(null, null).getElements();

		// verify
		Assertions.assertNotNull(items);
		Assertions.assertEquals(numberOfItemsBefore + 1, items.size());
	}

	@Test
	public void testDelete_ExistingItem() {

		// setup
		SimpleItem itemToDelete = this.repository
				.save(this.factory.create(new SimpleUser(Authentication.BRUCE, Instant.now())));

		// exercise
		long numberOfItemsBefore = this.repository.list(null, null).getTotalElements();
		this.repository.delete(itemToDelete);
		List<SimpleItem> items = this.repository.list(null, null).getElements();

		// verify
		Assertions.assertNotNull(items);
		Assertions.assertEquals(numberOfItemsBefore - 1, items.size());
	}

	@Test
	public void testList_UsingCriteria() throws InterruptedException {

		// setup
		ItemSearchCriteria itemSearchCriteria = new ItemSearchCriteria();
		itemSearchCriteria.setAcquisitionDateLowerBound(LocalDate.now().minusDays(1));
		itemSearchCriteria.setAcquisitionDateUpperBound(LocalDate.now().plusDays(1));
		itemSearchCriteria.setLabels("bar", "foo");
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("foo", "bar");
		itemSearchCriteria.setProperties(properties);

		// exercise
		long numberOfItemsBefore = this.repository.list(itemSearchCriteria, null, null).getTotalElements();

		SimpleItem firstItem = (SimpleItem) this.factory
				.create(new SimpleUser(Authentication.VAN_DAMME, Instant.now()));
		firstItem.setProperty("foo", "bar");
		firstItem.addLabel("foo").addLabel("bar");
		firstItem.setAcquisitionDate(LocalDate.now());
		this.repository.save(firstItem);

		Thread.sleep(1);

		IntStream.rangeClosed(1, 9).forEach((i) -> {
			this.repository.save(this.factory.create(firstItem.owner()));
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		});

		List<SimpleItem> items = this.repository.list(itemSearchCriteria, null, null).getElements();

		// verify
		Assertions.assertNotNull(items);
		Assertions.assertEquals(numberOfItemsBefore + 1, items.size());
	}
}
