package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/upload")
public class UploadPostServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// not logged
		// if (request.getSession().getAttribute("user") == null) {
		request.getRequestDispatcher("login.jsp").forward(request, response);
		// }
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		// int price = Integer.valueOf(request.getParameter("price"));
		String 	type = request.getParameter("type");
		System.out.println(title + " " + description + " " + " " + type);

		// logged
		// TODO GET USER ID FROM DB

	}

}
