package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
import model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private UserManager userManager = new UserManager();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		User user = new User(
				request.getParameter("firstName"),
				request.getParameter("lastName"),
				request.getParameter("email"),
				request.getParameter("pass1"),
				request.getParameter("gender"),
				request.getParameter("city"),
				request.getParameter("country"),
				request.getParameter("photo"),
				request.getParameter("description"),
				LocalDate.parse(request.getParameter("birthDate")),
				request.getParameter("telNumber"));
		
		
		try {
			userManager.register(user);
		} catch (SQLException e) {
			System.out.println("Couldnt add to database; "+ e.getMessage());
			request.setAttribute("exception", e);
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

}
