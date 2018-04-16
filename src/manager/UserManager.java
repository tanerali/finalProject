package manager;

import java.sql.SQLException;

import dao.UserDAO;
import exceptions.InvalidDataException;
import model.User;

public class UserManager {
	private UserDAO userDAO = UserDAO.INSTANCE;
	
	public User login(String email, String password) throws SQLException, InvalidDataException {
		User user = null;
		user = userDAO.getUserByEmail(email, password);
		
		return user;
	}

}
