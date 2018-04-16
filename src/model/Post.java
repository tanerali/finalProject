package model;

import java.util.Date;

import exceptions.InvalidPostCapacityException;
import exceptions.InvalidPostDataExcepetion;
import exceptions.InvalidPostDateException;
import exceptions.InvalidPostDescriptionException;
import exceptions.InvalidPostPriceException;
import exceptions.InvalidPostTitleException;
import exceptions.InvalidPostTypeException;

public class Post {
	enum Type {
		HOTEL, APARTMENT, HOUSE, COTTAGE
	}

	private String title;
	private String description;
	private int price;
	private int capacity;
	private Date dateOfPosting;
	private Type type;

	public Post(String title, String description, int price, int capacity, Date dateOfPosting, Type type)
			throws InvalidPostDataExcepetion {
		this.setCapacity(capacity);
		this.setDateOfPosting(dateOfPosting);
		this.setDescription(description);
		this.setPrice(price);
		this.setTitle(title);
		this.setType(type);

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws InvalidPostTitleException {
		if (title.isEmpty()) {
			throw new InvalidPostTitleException();
		}
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws InvalidPostDescriptionException {
		if (description.isEmpty()) {
			throw new InvalidPostDescriptionException();
		}
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) throws InvalidPostPriceException {
		if (price <= 0) {
			throw new InvalidPostPriceException();
		}
		this.price = price;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) throws InvalidPostCapacityException {
		if (capacity <= 0) {
			throw new InvalidPostCapacityException();
		}
		this.capacity = capacity;
	}

	public Date getDateOfPosting() {
		return dateOfPosting;
	}

	public void setDateOfPosting(Date dateOfPosting) throws InvalidPostDateException {
		this.dateOfPosting = dateOfPosting;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) throws InvalidPostTypeException {
		this.type = type;
	}

}
