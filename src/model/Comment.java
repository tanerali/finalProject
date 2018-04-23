package model;

import java.time.LocalDate;

import exceptions.InvalidCommentIDException;

public class Comment {

	private int commentID;
	private int userID;
	private int postID;
	private String user;
	private String Content;
	private LocalDate date;

	public Comment(int postID, int userID, String user, LocalDate date, String content)
			throws InvalidCommentIDException {
		this.setPostID(postID);
		this.setUserID(userID);
		this.setUser(user);
		this.setDate(date);
		this.setContent(content);
	}

	public Comment(int commentID, int postID, int userID, String user, LocalDate date, String content)
			throws InvalidCommentIDException {
		this(postID, userID, user, date, content);
		this.commentID = commentID;

	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) throws InvalidCommentIDException {
		if (commentID > 0) {
			this.commentID = commentID;
		} else {
			throw new InvalidCommentIDException();
		}
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) throws InvalidCommentIDException {
		if (postID > 0) {
			this.postID = postID;
		} else {
			throw new InvalidCommentIDException();
		}
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) throws InvalidCommentIDException {
		if (userID > 0) {
			this.userID = userID;
		} else {
			throw new InvalidCommentIDException();
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
