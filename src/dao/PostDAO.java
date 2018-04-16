package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidPostDataExcepetion;
import manager.DBManager;
import model.Post;

public enum PostDAO {
	instance;
	// Connection variable
	private Connection connection;

	// Const. SQL statements
	private static final String insertPost = "INSERT INTO POSTS(type_id,title,price,host_id,date_of_posting,description) "
			+ "VALUES(?,?,?,?,?,?);";

	private static final String getAllPosts = "SELECT type_id,title,description,price,date_of_posting FROM POSTS;";

	private static final String getAllPostsByType = "SELECT type_id,title,description,price,date_of_posting FROM POSTS "
			+ "WHERE type_id=?;";

	private static final String getAllPostsByUser = "SELECT * FROM POSTS\n" + "JOIN\n" + "USERS on USERS.ID = ?;";

	private static final String getAllPostsByCountry = "SELECT * FROM POSTS\n" + "JOIN\n"
			+ "USERS on USERS.country = ?;";

	private static final String getAllPostsByCity = "SELECT * FROM POSTS\n" + "JOIN\n" + "USERS on USERS.city = ?;";

	private static final String getAllPostTypes = "SELECT ID,type FROM POST_TYPE;";

	private static final String getAllRecentPosts = "SELECT type_id,title,description,price,date_of_posting FROM POSTS ORDER BY date_of_posting DESC;";

	private PostDAO() {
		connection = DBManager.getInstance().getConnection();
	}

	public List<Post> getAllPosts() throws SQLException, InvalidPostDataExcepetion {
		List<Post> posts = new ArrayList<Post>();
		Statement st = connection.createStatement();
		ResultSet result = st.executeQuery(getAllPosts);
		while (result.next()) {
			Post newPost = new Post(result.getString("title"), result.getString("description"), result.getInt("price"),
					result.getDate("date_of_posting").toLocalDate(), Post.Type.getType(result.getInt("type_id")));
			posts.add(newPost);
		}
		return posts;
	}

	public void insertPost(String title, String description, int price, LocalDate dateOfPosting, Post.Type type)
			throws InvalidPostDataExcepetion, SQLException {

		Post newPost = new Post(title, description, price, dateOfPosting, type);
		newPost.setHostID(21); // only available id in the DB atm
		PreparedStatement statement = connection.prepareStatement(insertPost);
		statement.setInt(1, newPost.getTypeLikeID());
		statement.setString(2, newPost.getTitle());
		statement.setInt(3, newPost.getPrice());
		statement.setString(2, newPost.getTitle());
		statement.setInt(4, newPost.getHostID());
		statement.setDate(5, Date.valueOf(newPost.getDateOfPosting()));
		statement.setString(6, newPost.getDescription());
		statement.executeUpdate();
	}
}
