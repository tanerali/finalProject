package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import exceptions.InvalidDataException;
import manager.DBManager;
import model.User;

public enum UserDAO {
	INSTANCE;
	private Connection connection;
	
	private UserDAO() {
		connection = DBManager.INSTANCE.getConnection();
	}

	public User getUserByEmail(String email, String password) throws SQLException, InvalidDataException {
		String sqlQuery = "SELECT "
				+ "first_name, last_name, email, user, password, gender, city, "
				+ "country, photo, description, birth_date, telephone_number "
				+ "FROM USERS "
				+ "WHERE email = ?";
		
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)){
			ps.setString(1, email);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			
			User user = null;
			if (!resultSet.getString(3).isEmpty() && resultSet.getString(5).equals(password)) {
				
				user = new User(resultSet.getString(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getString(5),
						resultSet.getString(6),
						resultSet.getString(7),
						resultSet.getString(8),
						resultSet.getString(9),
						resultSet.getString(10),
						LocalDate.parse(resultSet.getString(11)),
						resultSet.getString(12));
				

			}
			return user;
		}
	}
	
	
}
