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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private UserManager userManager = UserManager.instance;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userID = 0;
		if (req.getParameter("id") != null) {
			userID = Integer.parseInt(req.getParameter("id"));
		}
		
		try {
			User user = userManager.getUserByID(userID);
			ArrayList<Review> reviewsFromHosts = userManager.getReviewsFromHosts(user.getEmail());
			ArrayList<Review> reviewsFromGuests = userManager.getReviewsFromGuests(user.getEmail());
			
			req.setAttribute("user", user);
			req.setAttribute("reviewsFromHosts", reviewsFromHosts);
			req.setAttribute("reviewsFromGuests", reviewsFromGuests);
			
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		} catch (SQLException e) {
			// TODO 
			e.printStackTrace();
		} catch (UserDataException e) {
			// TODO 
			e.printStackTrace();
		}
	}
}
