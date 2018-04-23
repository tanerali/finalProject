package manager;

import java.sql.SQLException;
import java.time.LocalDate;

import dao.BookingDAO;
import model.Booking;

public enum BookingManager {
	instance;
	private BookingDAO bookingDao = BookingDAO.instance;

	public boolean requestBooking(Booking booking) throws SQLException {
		return bookingDao.createBooking(booking);
	}
}
