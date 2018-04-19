package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;


@WebServlet("/getPic")
public class PictureServlet extends HttpServlet {
       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User)req.getSession().getAttribute("user");
		if (user != null) {
			String path = user.getPhoto();
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

}
