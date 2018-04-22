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


import exceptions.UserDataException;
import manager.UserManager;
import model.User;

@MultipartConfig(fileSizeThreshold=1024*1024*10
//				 location="/Users/tanerali/Desktop/ServerUploads"
)
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private UserManager userManager = new UserManager();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		User user = null;
		try {
			//if date is empty, or not exactly as it has to be it 
			//throws a parsing exception
			LocalDate birthDate = null;
			try {
				 birthDate = LocalDate.parse(request.getParameter("birthDate"));
			} catch (Exception e) {
				throw new UserDataException("Invalid birth date entered");
			}
			
			//if password and confirm password dont match
			if (!request.getParameter("pass1").equals(request.getParameter("pass2"))) {
				throw new UserDataException("Password mismatch");
			}
			
			
			//photo upload
		    String path = "/Users/tanerali/Desktop/ServerUploads";
		    Part filePart = request.getPart("photo");
		    String fileName = getFileName(filePart);
		    String absoluteFilePath = path+ File.separator+ fileName;
		    
		    try (InputStream filecontent = filePart.getInputStream();
	    		 OutputStream out = new FileOutputStream(absoluteFilePath)) {
		    		        
		        byte[] bytes = new byte[1024];

		        while ((filecontent.read(bytes)) != -1) {
		            out.write(bytes);
		        }
		    } catch (FileNotFoundException fne) {
		        throw new UserDataException("You did not specify a photo to upload");
		    }

			user = new User(
					request.getParameter("firstName"),
					request.getParameter("lastName"),
					request.getParameter("email"),
					request.getParameter("pass1"),
					request.getParameter("gender"),
					request.getParameter("city"),
					request.getParameter("country"),
					absoluteFilePath,
					request.getParameter("description"),
					birthDate,
					request.getParameter("telNumber")
					);
			if (userManager.register(user)) {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		} catch (UserDataException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		} catch (SQLException e) {
			System.out.println("Couldnt add to database; "+ e.getMessage());
			request.setAttribute("exception", e);
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}
	
	/**
     * Utility method to get file name from HTTP header content-disposition
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
