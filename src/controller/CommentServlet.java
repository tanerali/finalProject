package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.CommentManager;
import model.Comment;
import model.User;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	private CommentManager commentManager = CommentManager.instance;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String commentText = req.getParameter("comment");
		
		int postID = 0;
		if (req.getParameter("postID") != null) {
			postID = Integer.parseInt(req.getParameter("postID"));
		}
		
		User user = (User) req.getSession().getAttribute("user");
		
		if (commentText != null && !commentText.isEmpty() && user != null) {
			Comment comment = new Comment(
					user.getUserID(), 
					(user.getFirstName()+ " "+ user.getLastName()), 
					postID, 
					commentText, 
					LocalDate.now());
			try {
				commentManager.addCommentToPost(comment);
				resp.sendRedirect("post?id="+postID);
			} catch (SQLException e) {
				req.getRequestDispatcher("error.jsp").forward(req, resp);
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int commentID = 0;
		int postID = 0;
		if (req.getParameter("commentID") != null) {
			commentID = Integer.parseInt(req.getParameter("commentID"));
		}
		if (req.getParameter("postID") != null) {
			postID = Integer.parseInt(req.getParameter("postID"));
		}
		
		System.out.println(commentID);
		System.out.println(postID);
		
		try {
			if (commentManager.deleteComment(commentID)) {
				req.getRequestDispatcher("post?id="+ postID).forward(req, resp);
			}
		} catch (SQLException e) {
			req.getRequestDispatcher("error.jsp").forward(req, resp);
			e.printStackTrace();
		}
	}
}
