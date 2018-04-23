package manager;

import dao.CommentDAO;

public enum CommentManager {

	INSTANCE;
	private CommentDAO commentDAO = CommentDAO.instance;
	
	
}
