package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.PostDAO;
import exceptions.InvalidPostDataExcepetion;
import exceptions.InvalidPostDateException;
import exceptions.InvalidPostDescriptionException;
import exceptions.InvalidPostPriceException;
import exceptions.InvalidPostTitleException;
import exceptions.InvalidPostTypeException;

public class Post {
	// In DB IDs (HOTEL-1)(APARTMENT-2)(HOUSE-3)(COTTAGE-4)
	public enum Type {
		HOTEL, APARTMENT, HOUSE, COTTAGE;
		public static Type getType(int DB_ID) {
			switch (DB_ID) {
			case 1:
				return Type.HOTEL;
			case 2:
				return Type.APARTMENT;
			case 3:
				return Type.HOUSE;
			case 4:
				return Type.COTTAGE;
			default:
				return null;
			}
		}
	}

	private String title;
	private String description;
	private int price;
	private LocalDate dateOfPosting;
	private Type type;
	private int hostID;
	private List<Comment> comments;
	private int rating; // 0->5

	public Post(String title, String description, int price, LocalDate dateOfPosting, Type type)
			throws InvalidPostDataExcepetion {
		this.setDateOfPosting(dateOfPosting);
		this.setDescription(description);
		this.setPrice(price);
		this.setTitle(title);
		this.setType(type);
		comments = new ArrayList<>();
		this.rating = 0;
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

	public LocalDate getDateOfPosting() {
		return dateOfPosting;
	}

	public void setDateOfPosting(LocalDate dateOfPosting) throws InvalidPostDateException {
		this.dateOfPosting = dateOfPosting;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) throws InvalidPostTypeException {
		this.type = type;
	}

	public int getTypeLikeID() {
		switch (this.getType()) {
		case HOTEL:
			return 1;
		case APARTMENT:
			return 2;
		case HOUSE:
			return 3;
		case COTTAGE:
			return 4;
		default:
			return -1;
		}
	}

	public void setHostID(int hostID) {
		this.hostID = hostID;
	}

	public int getHostID() {
		return hostID;
	}

	@Override
	public String toString() {
		return this.title + " " + this.description + " " + this.price + " " + this.type + " " + this.dateOfPosting;
	}

	public static void main(String[] args) throws SQLException, InvalidPostDataExcepetion {

		List<Post> ps = PostDAO.instance.getAllPostsByUserID(1);
		for (Post p : ps) {
			System.out.println(p);
		}
	}
}
