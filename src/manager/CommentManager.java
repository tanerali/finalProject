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

	public boolean addCommentToPost(Comment comment) throws SQLException {
		return commentDAO.addCommentToPost(comment);
	}

	public boolean deleteComment(int commentID) throws SQLException {
		return commentDAO.deleteComment(commentID);
	}
	
	
}
