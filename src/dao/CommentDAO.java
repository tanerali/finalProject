package dao;

import java.sql.Connection;

import manager.DBManager;

public enum CommentDAO {
	instance;

	private Connection connection;
	private static final String DELETE_COMMENT = "DELETE FROM POST_COMMENTS WHERE ID = ?;";
	

	private CommentDAO() {
		connection = DBManager.INSTANCE.getConnection();
	}
}
