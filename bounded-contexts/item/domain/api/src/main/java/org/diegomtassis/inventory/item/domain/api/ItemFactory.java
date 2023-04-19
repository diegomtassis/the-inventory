package org.diegomtassis.inventory.item.domain.api;

/**
 * Factory for items.
 * 
 * @author diegomtassis
 *
 * @param <T>
 */
public interface ItemFactory<T extends Item> {

	/**
	 * Creates a new item.
	 * 
	 * @param owner
	 * @return a new item
	 */
	T create(User owner);
}
