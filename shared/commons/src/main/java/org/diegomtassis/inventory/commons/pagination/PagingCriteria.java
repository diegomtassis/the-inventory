package org.diegomtassis.inventory.commons.pagination;

/**
 * @author diegomtassis
 *
 */
public class PagingCriteria {

	private Integer page;

	private Integer size;

	/**
	 * Constructor.
	 */
	public PagingCriteria() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param page
	 * @param size
	 */
	private PagingCriteria(Integer page, Integer size) {
		super();
		this.page = page;
		this.size = size;
	}

	public Integer getPage() {
		return this.page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return this.size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * Factory method.
	 * 
	 * @param page
	 * @param size
	 * @return {@link PagingCriteria}
	 */
	public static PagingCriteria of(Integer page, Integer size) {
		return new PagingCriteria(page, size);
	}
}
