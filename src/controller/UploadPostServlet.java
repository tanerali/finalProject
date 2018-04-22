package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import exceptions.InvalidPostDataExcepetion;
import manager.PostManager;
import model.Post;
import model.User;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10)
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
			// UPLOAD PICTURE FIRST

//			String path = "/home/dnn/UPLOADAIRBNB";
			String path = "/Users/tanerali/Desktop/ServerUploads";
			Part filePart = request.getPart("file");
			String fileName = RegisterServlet.getFileName(filePart);
			String absoluteFilePath = path + File.separator + fileName;

			try (InputStream filecontent = filePart.getInputStream();
					OutputStream out = new FileOutputStream(absoluteFilePath)) {

				byte[] bytes = new byte[1024];

				while ((filecontent.read(bytes)) != -1) {
					out.write(bytes);
				}
			} catch (FileNotFoundException fne) {
				System.out.println("Oops,smth went wrong with uploading image! ");
			}
			//
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
