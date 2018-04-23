package manager;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.CommentDAO;
import model.Comment;

public enum CommentManager {

	instance;
	private CommentDAO commentDAO = CommentDAO.instance;

	public ArrayList<Comment> getCommentsForPost(int postID) throws SQLException {
		return commentDAO.getCommentsByPostId(postID);
	}
	
	
}
