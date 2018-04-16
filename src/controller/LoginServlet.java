package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.InvalidDataException;
import manager.DBManager;
import manager.UserManager;
import model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private UserManager userManager = new UserManager();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			User user = userManager.login(email, password);
			request.getSession().setAttribute("user", user);
			
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		} catch (SQLException e) {
			System.out.println("Error getting user from database; "+ e.getMessage());
		} catch (InvalidDataException e) {
			System.out.println("Invalid data entered; "+ e.getMessage());
		}
		
		
	}

}
