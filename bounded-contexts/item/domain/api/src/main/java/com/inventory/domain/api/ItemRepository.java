package com.inventory.domain.api;

import com.inventory.commons.pagination.PagedList;
import com.inventory.commons.pagination.PagingCriteria;
import com.inventory.commons.pagination.SortingCriteria;

import java.util.Optional;

/**
 * Repository for items.
 * 
 * @author diegomtassis
 *
 * @param <T>
 */
public interface ItemRepository<T extends Item> {

	/**
	 * Saves an item.
	 * 
	 * @param item
	 * @return the saved item
	 */
	T save(T item);

	/**
	 * Retrieves an item.
	 * 
	 * @param id
	 * @return the retrieved item
	 */
	Optional<T> retrieve(String id);

	/**
	 * Deletes an item.
	 * 
	 * @param item
	 */
	void delete(T item);

	/**
	 * List.
	 * 
	 * @param pagingCriteria
	 * @param sortingCriteria
	 * @return paged list
	 */
	PagedList<T> list(PagingCriteria pagingCriteria, SortingCriteria sortingCriteria);

	/**
	 * List all the items matching a given search criteria.
	 * 
	 * @param searchCriteria
	 * @param pagingCriteria
	 * @param sortingCriteria
	 * @return the items matching the search criteria
	 */
	PagedList<T> list(ItemSearchCriteria searchCriteria, PagingCriteria pagingCriteria, SortingCriteria sortingCriteria);
}
