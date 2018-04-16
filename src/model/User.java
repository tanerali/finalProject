package model;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.InvalidDataException;

public class User {

	private String first_name;
	private String last_name;
	private String email;
	private String user;
	private String password;
	private int age;
	private String gender;
	private String city;
	private String country;
	private String photo;
	private String description;
	private LocalDate birthDate;
	private String telNumber;
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) throws InvalidDataException {
		if (first_name.isEmpty() || !first_name.matches("[a-zA-Z]+")) {
			throw new InvalidDataException();
		}
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) throws InvalidDataException {
		if (last_name.isEmpty() || !last_name.matches("[a-zA-Z]+")) {
			throw new InvalidDataException();
		}
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws InvalidDataException {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	    
		if (email.isEmpty() || !email.matches(regex)) {
			throw new InvalidDataException();
		}
		this.email = email;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) throws InvalidDataException {
		if (user.isEmpty()) {
			throw new InvalidDataException();
		}
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws InvalidDataException {
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$\n";
		
		if (password.isEmpty() || !password.matches(regex)) {
			throw new InvalidDataException();
		}
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		if (age > 0 && age<120) {
			this.age = age;
		}
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) throws InvalidDataException {
		if (gender.isEmpty()) {
			throw new InvalidDataException();
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
	public void setBirthDate(LocalDate birthDate) throws InvalidDataException {
		if (birthDate == null) {
			throw new InvalidDataException();
		}
		this.birthDate = birthDate;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(String telNumber) throws InvalidDataException {
		if (telNumber.isEmpty()) {
			throw new InvalidDataException();
		}
		this.telNumber = telNumber;
	}
	
	
}
