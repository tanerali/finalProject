package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LocationDao;
import exceptions.InvalidPostDataExcepetion;
import manager.PostManager;
import model.Post;


@WebServlet("/explore")
public class ExploreServlet extends HttpServlet {
	private PostManager postManager = PostManager.instance;
	private LocationDao locationDao = LocationDao.instance;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ArrayList<Post> posts = (ArrayList<Post>) postManager.getAllPosts();
			
			Map<String, TreeSet<String>> locations = locationDao.getLocations();
			
			if (posts != null) {
				req.setAttribute("posts", posts);
				req.getServletContext().setAttribute("locations", locations);
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
