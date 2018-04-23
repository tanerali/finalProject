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
import manager.CommentManager;
import manager.PostManager;
import manager.UserManager;
import model.Post;
import model.User;
import model.Comment;;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
	private PostManager postManager = PostManager.instance;
	private UserManager userManager = UserManager.instance;
	private CommentManager commentManager = CommentManager.instance;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int postID = 0;
		if (request.getParameter("id") != null) {
			postID = Integer.valueOf(request.getParameter("id"));
		}
		System.out.println(postID);
		
		User hostUser = null;
		Post currPost = postManager.getPostsByID().get(postID);
		ArrayList<Comment> comments = new ArrayList<>();
		
		if (currPost != null) {
			try {
				hostUser = userManager.getUserByID(currPost.getHostID());
				comments = commentManager.getCommentsForPost(postID);
			} catch (SQLException | UserDataException e) {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
			}
			
			request.setAttribute("user", hostUser);
			request.setAttribute("post", currPost);
			request.setAttribute("comments", comments);
			request.getRequestDispatcher("WEB-INF/jsp/post.jsp").forward(request, response);
		}
	}
}
