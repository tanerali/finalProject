package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.BookingManager;
import model.Booking;
import model.User;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
	private BookingManager bookingManager = BookingManager.instance;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int postID = 0;
		if (req.getParameter("postID") != null) {
			postID = Integer.parseInt(req.getParameter("postID"));
		}
		
		LocalDate dateFrom = null;
		LocalDate dateTo = null;
		if (req.getParameter("dateFrom") != null && req.getParameter("dateTo") != null) {
			dateFrom = LocalDate.parse(req.getParameter("dateFrom"));
			dateTo = LocalDate.parse(req.getParameter("dateTo"));
		}
		
		User user = (User)req.getSession().getAttribute("user");
		
		Booking booking = new Booking(postID, user.getUserID(), dateFrom, dateTo);
		
		try {
			bookingManager.requestBooking(booking);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
