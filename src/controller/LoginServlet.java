package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.UserDataException;
import manager.UserManager;
import model.Review;
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
			if (user != null) {
				request.getSession().setAttribute("user", user);
				ArrayList<Review> reviewsFromHosts = userManager.getReviewsFromHosts(email);
				ArrayList<Review> reviewsFromGuests = userManager.getReviewsFromGuests(email);
				
				if (reviewsFromHosts != null && !reviewsFromHosts.isEmpty()) {
					request.getSession().setAttribute("reviewsFromHosts", reviewsFromHosts);
					request.getSession().setAttribute("reviewsFromGuests", reviewsFromGuests);
				}
				request.getRequestDispatcher("WEB-INF/jsp/profile.jsp").forward(request, response);
			} else {
				request.setAttribute("wrong_password", new Object());
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			request.setAttribute("wrong_credentials", new Object());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (UserDataException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
		}

	}

}
