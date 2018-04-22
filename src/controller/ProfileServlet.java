package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.UserDataException;
import manager.UserManager;
import model.User;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private UserManager userManager = new UserManager();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		if (user != null) {
			request.getRequestDispatcher("WEB-INF/jsp/profile.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		try {
			LocalDate birthDate = null;
			try {
				 birthDate = LocalDate.parse(request.getParameter("birthDate"));
			} catch (Exception e) {
				throw new UserDataException("Invalid birth date entered");
			}
			
			user = new User(
					request.getParameter("firstName"),
					request.getParameter("lastName"),
					request.getParameter("email"),
					request.getParameter("gender"),
					request.getParameter("city"),
					request.getParameter("country"),
					request.getParameter("description"),
					birthDate,
					request.getParameter("telNumber")
					);
			
			if (user != null) {
				userManager.editUser(user);
				request.getRequestDispatcher("profile.jsp").forward(request, response);
			}
		} catch (UserDataException e1) {
			
		} catch (SQLException e) {
			
		}
		
		
	}
}
