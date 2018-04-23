package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import exceptions.UserDataException;
import manager.PostManager;
import manager.UserManager;
import model.Post;
import model.User;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
	private PostManager postManager = PostManager.instance;
	private UserManager userManager = UserManager.instance;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int postID = Integer.valueOf(request.getParameter("id"));
		User hostUser = null;
		Post currPost = postManager.getPostsByID().get(postID);
		
		if (currPost != null) {
			try {
				hostUser = userManager.getUserByID(currPost.getHostID());
			} catch (SQLException | UserDataException e) {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
			}
			
			request.setAttribute("user", hostUser);
			request.setAttribute("post", currPost);
			request.getRequestDispatcher("WEB-INF/jsp/post.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("explore").forward(request, response);
		}
	}
}
