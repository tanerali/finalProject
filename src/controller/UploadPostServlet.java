package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.InvalidPostDataExcepetion;
import manager.PostManager;
import model.Post;
import model.User;

@WebServlet("/upload")
public class UploadPostServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User currUser = (User) request.getSession().getAttribute("user");
		// not logged
		if (currUser == null) {
			System.out.println("not logged");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {

			System.out.println("logged");
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			int price = Integer.valueOf(request.getParameter("price"));
			String type = request.getParameter("type");
			int hostID = currUser.getUserID();
			
			// TODO ADD PHOTO
			try {
				Post newPost = new Post(
						title, 
						description, 
						price, 
						LocalDate.now(), 
						Post.Type.getType(type),
						hostID);
				
				PostManager.instance.insertPost(newPost);
			} catch (InvalidPostDataExcepetion | SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
