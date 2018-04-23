package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import manager.PostManager;
import model.Post;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search = request.getParameter("search");
		System.out.println(search);
		if (search != null) {
			List<Post> result = PostManager.instance.searchPost(search);
			if (!result.isEmpty()) {
				System.out.println("not empty");
				request.setAttribute("posts", result);
				request.getRequestDispatcher("explore.jsp").forward(request, response);
			}
			// String resultJSON = new Gson().toJson(result);
			// response.setContentType("text/html");
			// response.getWriter().write(resultJSON);
			// System.out.println(resultJSON);
		}
	}

}
