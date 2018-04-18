package model;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.UserDataException;

public class User {

	private int userID;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String gender;
	private String city;
	private String country;
	private String photo;
	private String description;
	private LocalDate birthDate;
	private String telNumber;
	
	public User(String first_name, String last_name, String email, String password, String gender, String city,
			String country, String photo, String description, LocalDate birthDate, String telNumber) {
		this.firstName = first_name;
		this.lastName = last_name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.city = city;
		this.country = country;
		this.photo = photo;
		this.description = description;
		this.birthDate = birthDate;
		this.telNumber = telNumber;
	}
	
	public String getFirst_name() {
		return firstName;
	}
	public void setFirst_name(String first_name) throws UserDataException {
		if (first_name.isEmpty() || !first_name.matches("[a-zA-Z]+")) {
			throw new UserDataException("Error setting first name");
		}
		this.firstName = first_name;
	}
	public String getLast_name() {
		return lastName;
	}
	public void setLast_name(String last_name) throws UserDataException {
		if (last_name.isEmpty() || !last_name.matches("[a-zA-Z]+")) {
			throw new UserDataException("Error setting last name");
		}
		this.lastName = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws UserDataException {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	    
		if (email.isEmpty() || !email.matches(regex)) {
			throw new UserDataException("Error setting email");
		}
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws UserDataException {
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$\n";
		
		if (password.isEmpty() || !password.matches(regex)) {
			throw new UserDataException("Error setting password");
		}
		this.password = password;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) throws UserDataException {
		if (gender.isEmpty()) {
			throw new UserDataException("Error setting gender");
		}
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	//drop down menu for the given country
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	//drop down menu
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) throws UserDataException {
		if (birthDate == null) {
			throw new UserDataException("Error setting birth date");
		}
		this.birthDate = birthDate;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(String telNumber) throws UserDataException {
		if (telNumber.isEmpty()) {
			throw new UserDataException("Error setting telephone number");
		}
		this.telNumber = telNumber;
	}
	
	
}
