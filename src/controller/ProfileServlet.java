package controller;

import java.awt.List;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
import model.Review;
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
	

}
