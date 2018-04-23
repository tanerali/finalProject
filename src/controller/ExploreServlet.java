package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.InvalidPostDataExcepetion;
import manager.PostManager;
import model.Post;


@WebServlet("/explore")
public class ExploreServlet extends HttpServlet {
	private PostManager postManager = PostManager.instance;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ArrayList<Post> posts = (ArrayList<Post>) postManager.getAllPosts();
			
			if (posts != null) {
				req.setAttribute("posts", posts);
				req.getRequestDispatcher("explore.jsp").forward(req, resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPostDataExcepetion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
