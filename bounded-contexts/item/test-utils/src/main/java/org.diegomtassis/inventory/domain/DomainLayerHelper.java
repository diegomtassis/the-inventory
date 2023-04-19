package org.diegomtassis.inventory.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.Random;

import org.diegomtassis.inventory.item.domain.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author diegomtassis
 *
 */
public class DomainLayerHelper<T extends Item, U extends User> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainLayerHelper.class);

	private final UserFactory<U> userFactory;

	private final UserRepository<U> userRepository;

	private final ItemFactory<T> itemFactory;

	private final ItemRepository<T> itemRepository;

	/**
	 * Constructor.
	 * 
	 * @param userFactory
	 * @param userRepository
	 * @param itemFactory
	 * @param itemRepository
	 */
	public DomainLayerHelper(UserFactory<U> userFactory, UserRepository<U> userRepository, ItemFactory<T> itemFactory,
			ItemRepository<T> itemRepository) {
		super();
		this.userFactory = userFactory;
		this.userRepository = userRepository;
		this.itemFactory = itemFactory;
		this.itemRepository = itemRepository;
	}

	public U addUser(String key) {

		Optional<U> optUser = this.userRepository.retrieve(key);
		U user = optUser.orElseGet(() -> {
			LOGGER.debug("Added user {}", key);
			return this.userRepository.save(this.userFactory.create(key, Instant.now()));
		});

		return user;
	}

	public T addVideogame(U owner, String system, String name, String region) {

		T videogame = this.addItem(owner);
		videogame.setProperty("system", system);
		videogame.setProperty("name", name);
		videogame.setProperty("region", region);
		videogame.addLabel("videogame");

		return this.save(videogame);
	}

	public T addF1SlotCar(U owner, String brand, String team, String model, String driver) {

		T slotcar = this.addItem(owner);
		slotcar.setProperty("brand", brand);
		slotcar.setProperty("team", team);
		slotcar.setProperty("model", model);
		slotcar.setProperty("driver", driver);
		slotcar.addLabel("slotcar", "f1");

		return this.save(slotcar);
	}

	public T addBluRay(U owner, String name) {

		T bluray = this.addItem(owner);
		bluray.setProperty("name", name);
		bluray.addLabel("bluray");

		return this.save(bluray);
	}

	public T addBook(U owner, String author, String title, String isbn, String genre) {

		T book = this.addItem(owner);
		book.setProperty("author", author);
		book.setProperty("name", title);
		book.setProperty("isbn", isbn);
		book.setProperty("genre", genre);
		book.addLabel("book");

		return this.save(book);
	}

	public T addMusicAlbum(U owner, String title, String artist, String format) {

		T book = this.addItem(owner);
		book.setProperty("name", title);
		book.setProperty("artist", artist);
		book.setProperty("format", format);
		book.addLabel("music", "album");

		return this.save(book);
	}

	private T addItem(U owner) {

		T item = this.itemFactory.create(owner);
		LocalDate randomAcquisitionDate = LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 70))));
		item.setAcquisitionDate(randomAcquisitionDate);

		item = this.itemRepository.save(item);
		LOGGER.debug("Added item {} by user {}", item.getId(), owner.getKey());
		return item;
	}

	private T save(T item) {
		return this.itemRepository.save(item);
	}
}
