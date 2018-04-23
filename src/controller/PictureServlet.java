package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;
import model.User;


@WebServlet("/getPic")
public class PictureServlet extends HttpServlet {
	private UserManager userManager = UserManager.instance; 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userID = 0;
		if (req.getParameter("id") != null) {
			userID = Integer.parseInt(req.getParameter("id"));
		}

		String path = null;
		try {
			path = userManager.getPhoto(userID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File file = new File(path);
		try (InputStream filecontent = new FileInputStream(file);
				OutputStream out = resp.getOutputStream()) {

			byte[] bytes = new byte[1024];

			while ((filecontent.read(bytes)) != -1) {
				out.write(bytes);
			}
		} catch (FileNotFoundException fne) {
			req.setAttribute("exception", fne);
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}
	}

}
