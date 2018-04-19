package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import exceptions.UserDataException;
import manager.DBManager;
import model.User;

public enum UserDAO {
	INSTANCE;
	private Connection connection;
	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	private UserDAO() {
		connection = DBManager.INSTANCE.getConnection();
	}

	public User getUserByEmail(String email, String password) throws SQLException, UserDataException {
		String sqlQuery = "SELECT "
				+ "first_name, last_name, email, password, gender, city, "
				+ "country, photo, description, birth_date, telephone_number "
				+ "FROM USERS "
				+ "WHERE email = ?";
		
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)){
			ps.setString(1, email);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			
			User user = null;
			//if the user exists
			if (!resultSet.getString("email").isEmpty() &&
					//and his password corresponds to the encoded password in the DB
					bcrypt.matches(password, resultSet.getString("password"))) {

				user = new User(
						resultSet.getString("first_name"),
						resultSet.getString("last_name"),
						resultSet.getString("email"),
						resultSet.getString("password"),
						resultSet.getString("gender"),
						resultSet.getString("city"),
						resultSet.getString("country"),
						resultSet.getString("photo"),
						resultSet.getString("description"),
						LocalDate.parse(resultSet.getString("birth_date")),
						resultSet.getString("telephone_number"));
			}
			return user;
		}
	}

	public boolean addUser(User user) throws SQLException {
		String sql = "INSERT INTO USERS "
				+ "(first_name, last_name, email, password, gender, city, "
				+ "country, photo, description, birth_date, telephone_number) " + 
				"VALUES (?,?,?,?,?,?,?,?,?,?,?);";
		
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, user.getFirst_name());
			ps.setString(2, user.getLast_name());
			ps.setString(3, user.getEmail());
			ps.setString(4, bcrypt.encode(user.getPassword()));
			ps.setString(5, user.getGender());
			ps.setString(6, user.getCity());
			ps.setString(7, user.getCountry());
			ps.setString(8, user.getPhoto());
			ps.setString(9, user.getDescription());
			ps.setObject(10, user.getBirthDate());
			ps.setString(11, user.getTelNumber());
			return ps.executeUpdate() > 0 ? true : false;
		}
		
	}
	
	
}
