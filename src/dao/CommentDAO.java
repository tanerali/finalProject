package dao;

import java.sql.Connection;
import java.util.List;

import manager.DBManager;
import model.Comment;

public enum CommentDAO {
	instance;

	private Connection connection;
	private static final String DELETE_COMMENT = "DELETE FROM POST_COMMENTS WHERE ID = ?;";

	private CommentDAO() {
		connection = DBManager.INSTANCE.getConnection();
	}

	public List<Comment> getAllComments() {
		// TODO Auto-generated method stub
		return null;
	}
}
