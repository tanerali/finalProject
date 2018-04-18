package manager;

import java.sql.SQLException;

import dao.UserDAO;
import exceptions.UserDataException;
import model.User;

public class UserManager {
	private UserDAO userDAO = UserDAO.INSTANCE;
	
	public User login(String email, String password) throws SQLException, UserDataException {
		User user = null;
		user = userDAO.getUserByEmail(email, password);
		
		return user;
	}

	public boolean register(User user) throws SQLException {
		return userDAO.addUser(user);
	}

}
