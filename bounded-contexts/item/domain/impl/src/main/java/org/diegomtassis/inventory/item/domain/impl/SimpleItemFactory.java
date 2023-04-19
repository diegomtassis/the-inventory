package org.diegomtassis.inventory.item.domain.impl;


import org.diegomtassis.inventory.item.domain.api.ItemFactory;
import org.diegomtassis.inventory.item.domain.api.User;

/**
 * Very simple {@link ItemFactory} implementation.
 * 
 * @author diegomtassis
 *
 */
public class SimpleItemFactory implements ItemFactory<SimpleItem> {

	@Override
	public SimpleItem create(User owner) {
		return new SimpleItem(owner);
	}
}
