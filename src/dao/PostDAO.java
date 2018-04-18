package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static final String deletePost = "DELETE FROM POSTS where ID=?;";

	private static final String getAllPosts = "SELECT ID,type_id,title,description,host_id,price,date_of_posting FROM POSTS;";

	private static final String getAllPostsByType = "SELECT ID,type_id,title,description,host_id,price,date_of_posting FROM POSTS WHERE type_id=?;";

	private static final String getAllPostsByUser = "SELECT * FROM POSTS\n" + "JOIN\n" + "USERS on USERS.ID = ?;";

	private static final String getAllPostsByCountry = "SELECT * FROM POSTS\n" + "JOIN\n"
			+ "USERS on USERS.country = ?;";

	private static final String getAllPostsByCity = "SELECT POSTS.ID,POSTS.title,POSTS.description,POSTS.host_id,POSTS.price,POSTS.date_of_posting,POSTS.type_id FROM POSTS JOIN USERS on USERS.city = ?;";

	private static final String getAllPostTypes = "SELECT ID,type FROM POST_TYPE;";

	private static final String getAllRecentPosts = "SELECT POSTS.ID,POSTS.type_id,POSTS.title,POSTS.description,POSTS.host_id,POSTS.price,POSTS.date_of_posting FROM POSTS ORDER BY date_of_posting DESC;";

	private PostDAO() {
		connection = DBManager.INSTANCE.getConnection();
	}

	public List<Post> getAllPosts() throws SQLException, InvalidPostDataExcepetion {
		List<Post> posts = new ArrayList<Post>();
		Statement st = connection.createStatement();
		try {
			ResultSet result = st.executeQuery(getAllPosts);
			posts = this.getResult(result);
		} finally {
			st.close();
		}
		return posts;
	}

	public void insertPost(String title, String description, int price, LocalDate dateOfPosting, Post.Type type)
			throws InvalidPostDataExcepetion, SQLException {
		PreparedStatement statement = connection.prepareStatement(insertPost);
		try {
			Post newPost = new Post(title, description, price, dateOfPosting, type);
			newPost.setHostID(21); // only available id in the DB atm
			statement.setInt(1, newPost.getTypeLikeID());
			statement.setString(2, newPost.getTitle());
			statement.setInt(3, newPost.getPrice());
			statement.setString(2, newPost.getTitle());
			statement.setInt(4, newPost.getHostID());
			statement.setDate(5, Date.valueOf(newPost.getDateOfPosting()));
			statement.setString(6, newPost.getDescription());
			statement.executeUpdate();
		} finally {
			statement.close();
		}
	}

	public List<Post> getAllPostsByUserID(int id) throws SQLException, InvalidPostDataExcepetion {
		List<Post> posts = new ArrayList<>();
		PreparedStatement st = connection.prepareStatement(getAllPostsByUser);
		try {
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			posts = this.getResult(result);
		} finally {
			st.close();
		}
		return posts;
	}

	public List<Post> getAllPostsByCountry(String country) throws InvalidPostDataExcepetion, SQLException {
		List<Post> posts = new ArrayList<>();
		PreparedStatement st = connection.prepareStatement(getAllPostsByCountry);
		try {
			st.setString(1, country);
			ResultSet result = st.executeQuery();
			posts = this.getResult(result);
		} finally {
			st.close();
		}
		return posts;
	}

	public List<Post> getAllPostsByCity(String city) throws SQLException, InvalidPostDataExcepetion {
		List<Post> posts = new ArrayList<>();
		PreparedStatement st = connection.prepareStatement(getAllPostsByCity);
		try {
			st.setString(1, city);
			ResultSet result = st.executeQuery();
			posts = this.getResult(result);
		} finally {
			st.close();
		}
		return posts;
	}

	public List<Post> getAllPostsByType(int typeID) throws InvalidPostDataExcepetion, SQLException {
		List<Post> posts = new ArrayList<>();
		PreparedStatement st = connection.prepareStatement(getAllPostsByType);
		try {
			st.setInt(1, typeID);
			ResultSet result = st.executeQuery();
			posts = this.getResult(result);
		} finally {
			st.close();
		}
		return posts;
	}

	public Map<Integer, String> getAllTypes() throws SQLException {
		Map<Integer, String> types = new HashMap<>();
		Statement st = connection.createStatement();
		try {
			ResultSet rs = st.executeQuery(getAllPostTypes);
			while (rs.next()) {
				types.put(rs.getInt("ID"), rs.getString("type"));
			}
		} finally {
			st.close();
		}
		return types;
	}

	public List<Post> getAllRecentPosts() throws SQLException, InvalidPostDataExcepetion {
		List<Post> posts = new ArrayList<Post>();
		try {
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery(getAllRecentPosts);
			posts = this.getResult(result);
		} finally {
			connection.close();
		}
		return posts;
	}

	private List<Post> getResult(ResultSet result) throws InvalidPostDataExcepetion, SQLException {
		List<Post> posts = new ArrayList<Post>();
		while (result.next()) {
			Post newPost = new Post(result.getInt("ID"), result.getString("title"), result.getString("description"),
					result.getInt("price"), result.getDate("date_of_posting").toLocalDate(),
					Post.Type.getType(result.getInt("type_id")), result.getInt("host_id"));
			posts.add(newPost);
		}
		return posts;
	}

	public void removePost(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(deletePost);
		try {
			ps.setInt(1, id);
			ps.executeUpdate();
		} finally {
			ps.close();
		}
	}
}
