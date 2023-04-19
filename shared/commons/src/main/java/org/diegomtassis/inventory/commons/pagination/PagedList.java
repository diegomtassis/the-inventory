package org.diegomtassis.inventory.commons.pagination;

import java.util.List;

/**
 * @author diegomtassis
 *
 */
public class PagedList<T> {

	private long page;

	private long numberOfElements;

	private long size;

	private long totalElements;

	private List<T> elements;

	/**
	 * Constructor.
	 */
	public PagedList() {
		super();
	}

	public long getPage() {
		return this.page;
	}

	public PagedList<T> setPage(long page) {
		this.page = page;
		return this;
	}

	public long getNumberOfElements() {
		return this.numberOfElements;
	}

	public PagedList<T> setNumberOfElements(long numberOfElements) {
		this.numberOfElements = numberOfElements;
		return this;
	}

	public long getSize() {
		return this.size;
	}

	public PagedList<T> setSize(long size) {
		this.size = size;
		return this;
	}

	public long getTotalElements() {
		return this.totalElements;
	}

	public PagedList<T> setTotalElements(long totalElements) {
		this.totalElements = totalElements;
		return this;
	}

	public List<T> getElements() {
		return this.elements;
	}

	public PagedList<T> setElements(List<T> elements) {
		this.elements = elements;
		return this;
	}
}
