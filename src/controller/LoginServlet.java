package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import exceptions.UserDataException;
import manager.UserManager;
import model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private UserManager userManager = new UserManager();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println(email + password);
		
		try {
			User user = userManager.login(email, password);
			if (user != null) {
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("WEB-INF/jsp/profile.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("login.jsp").forward(request, response);;
			}
		} catch (SQLException e) {
			System.out.println("Couldnt get from db: "+ e.getMessage());
			request.setAttribute("exception", e);
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
		} catch (UserDataException e) {
			System.out.println("Invalid data entered: "+ e.getMessage());
			request.setAttribute("exception", e);
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
		}
		
	}

}
