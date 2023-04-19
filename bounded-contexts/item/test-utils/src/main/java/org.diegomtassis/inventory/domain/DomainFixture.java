/*****************************************************************************
 * Copyright (C) Blue Chips, 2018-2018 - All rights reserved
 *
 *****************************************************************************/
package org.diegomtassis.inventory.domain;

import org.diegomtassis.inventory.item.domain.api.Item;
import org.diegomtassis.inventory.item.domain.api.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.diegomtassis.inventory.Authentication;

/**
 * @author diegomtassis
 *
 */
public class DomainFixture<T extends Item, U extends User> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainFixture.class);

	private final DomainLayerHelper<T, U> helper;

	private long totalNumberOfItems;
	private int totalNumberOfVideogames;

	/**
	 * Constructor.
	 * 
	 * @param domainLayerHelper
	 */
	public DomainFixture(DomainLayerHelper<T, U> domainLayerHelper) {
		super();
		this.helper = domainLayerHelper;
	}

	public void load() {

		LOGGER.debug("Fixture loading...");

		// sly
		U sly = this.helper.addUser(Authentication.SLY);
		this.addBluRay(sly, "Tron Legacy");
		this.addF1SlotCar(sly, "Exin", "Ferrari", "B3", "Niki Lauda");
		this.addVideogame(sly, "Sega MegaDrive", "MUSHA", "JP");
		this.addVideogame(sly, "Sega Mega-CD", "Final Fight CD", "EU");
		this.addMusicAlbum(sly, "The best of Rocky", "Various artists", "Vinyl");

		// arnie
		U arnie = this.helper.addUser(Authentication.ARNIE);
		this.addBluRay(arnie, "Blade Runner");
		this.addVideogame(arnie, "Nintendo Entertainent System", "Super Mario Bros 3", "USA");
		this.addVideogame(arnie, "Nintendo Super Famicom", "Super Mario Kart", "JP");
		this.addBook(arnie, "Tsun Zu", "The art of war", "23423-23234-234-2678", "manual");

		// jeff
		U jeff = this.helper.addUser(Authentication.JEFF);
		this.addBook(jeff, "Jules Verne", "The mysterious island", "1223-666-7265-61524", "adventure");
		this.addBook(jeff, "Orson Scott Card", "Ender's game", "234-2345-4567-1234", "sci-fi");
		this.addBook(jeff, "Alexander Dumas", "The count of Monte Cristo", "345-74-1234-6879", "adventure");

		// bruce
		U bruce = this.helper.addUser(Authentication.BRUCE);
		this.addMusicAlbum(bruce, "(What's the story) Morning glory", "Oasis", "CD");
		this.addMusicAlbum(bruce, "Gladiator", "Hans Zimmer", "CD");
		this.addMusicAlbum(bruce, "Tron Legacy Soundtrack", "Daft Punk", "CD");
		this.addMusicAlbum(bruce, "Use your illusion II", "Guns n' Roses", "Cassette");
		this.addMusicAlbum(bruce, "Let it Be", "The Beatles", "Vinyl");

		// users without items
		this.helper.addUser(Authentication.DOLPH);
		this.helper.addUser(Authentication.VAN_DAMME);
		this.helper.addUser(Authentication.WESLEY);

		LOGGER.debug("Fixture loading ended");
	}

	private T addVideogame(U owner, String system, String name, String region) {

		this.totalNumberOfVideogames++;
		this.totalNumberOfItems++;
		return this.helper.addVideogame(owner, system, name, region);
	}

	private T addF1SlotCar(U owner, String brand, String team, String model, String driver) {

		this.totalNumberOfItems++;
		return this.helper.addF1SlotCar(owner, brand, team, model, driver);
	}

	private T addBluRay(U owner, String name) {

		this.totalNumberOfItems++;
		return this.helper.addBluRay(owner, name);
	}

	private T addBook(U owner, String author, String title, String isbn, String genre) {

		this.totalNumberOfItems++;
		return this.helper.addBook(owner, author, title, isbn, genre);
	}

	private T addMusicAlbum(U owner, String title, String artist, String format) {

		this.totalNumberOfItems++;
		return this.helper.addMusicAlbum(owner, title, artist, format);
	}

	public long getTotalNumberOfItems() {
		return totalNumberOfItems;
	}

	public int getTotalNumberOfVideogames() {
		return totalNumberOfVideogames;
	}
}
