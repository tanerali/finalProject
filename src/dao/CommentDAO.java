package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import manager.DBManager;

public enum CommentDAO {
	instance;

	private Connection connection;
	private static final String DELETE_COMMENT = "DELETE FROM POST_COMMENTS WHERE ID = ?;";
	private static final String INSERT_POST = "INSERT INTO POST_COMMENTS(user_id,post_id,content,date) VALUES(?,?,?,?);";
	private static final String ALL_COMMENTS_FOR_POST = "SELECT ID,user_ID,post_ID,content,date FROM POST_COMMENTS WHERE post_ID =?;";
	private static final String ALL_COMMENTS_BY_USER = "SELECT ID,user_ID,post_ID,content,date FROM POST_COMMENTS WHERE user_ID =?;";

	private CommentDAO() {
		connection = DBManager.INSTANCE.getConnection();
	}

	public void deleteComment(int commentID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_COMMENT);
		
	}
}
