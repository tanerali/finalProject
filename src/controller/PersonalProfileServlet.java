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

@WebServlet("/personalProfile")
public class PersonalProfileServlet extends HttpServlet {
	private UserManager userManager = UserManager.instance;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		if (user != null) {
			request.getRequestDispatcher("WEB-INF/jsp/personalProfile.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		try {
			LocalDate birthDate = null;
			try {
				 birthDate = LocalDate.parse(request.getParameter("birthDate"));
			} catch (Exception e) {
				throw new UserDataException("Invalid birth date entered");
			}
			if (user != null) {
				user.setFirstName(request.getParameter("firstName"));
				user.setLastName(request.getParameter("lastName"));
				user.setEmail(request.getParameter("email"));
				user.setGender(request.getParameter("gender"));
				user.setCity(request.getParameter("city"));
				user.setCountry(request.getParameter("country"));
				user.setDescription(request.getParameter("description"));
				user.setBirthDate(birthDate);
				user.setTelNumber(request.getParameter("telNumber"));
							
				userManager.editUser(user);
				request.getRequestDispatcher("WEB-INF/jsp/personalProfile.jsp").forward(request, response);
			}
		} catch (UserDataException e1) {
			request.setAttribute("exception", e1);
			request.getRequestDispatcher("WEB-INF/jsp/personalProfile.jsp").forward(request, response);
			System.out.println("Invalid user data; "+ e1.getMessage());
		} catch (SQLException e) {
			System.out.println("Couldnt update record in db; "+ e.getMessage());
		}
		
	}
}
