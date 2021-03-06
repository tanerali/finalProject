package manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import dao.PostDAO;
import exceptions.InvalidPostDataExcepetion;
import model.Post;

public enum PostManager {
	instance;

	// userID -> list of posts
	private Map<Integer, List<Post>> postsByUsers;

	// postID -> Post
	private Map<Integer, Post> postsByID;

	private PostManager() {

		postsByUsers = new ConcurrentHashMap<>();
		postsByID = new ConcurrentHashMap<>();

		// Load(cache) all posts
		try {
			for (Post p : PostDAO.instance.getAllPosts()) {
				if (!postsByUsers.containsKey(p.getHostID())) {
					postsByUsers.put(p.getHostID(), new ArrayList<>());
				}
				postsByUsers.get(p.getHostID()).add(p);
				postsByID.put(p.getPostID(), p);
			}

			for (Entry<Integer, Post> e : postsByID.entrySet()) {
				System.out.println(e.getKey() + " " + e.getValue());
			}
			// Now lets load all comments for posts
			// for (Comment c : CommentDAO.instance.getAllComments()) {
			// postsByID.get(c.getPostID()).addComment(c);
			// }
		} catch (SQLException | InvalidPostDataExcepetion e) {
			System.out.println("Oops,smth went terribly wrong!");
		}
	}

	public Map<Integer, List<Post>> getPostsByUsers() {
		return Collections.unmodifiableMap(postsByUsers);
	}

	public Map<Integer, Post> getPostsByID() {
		return Collections.unmodifiableMap(postsByID);
	}
	
	public void addPostToCache(Post post) {
		postsByID.put(post.getPostID(), post);
		if (!postsByUsers.containsKey(post.getHostID())) {
			postsByUsers.put(post.getHostID(), new ArrayList<>());
		}
		postsByUsers.get(post.getHostID()).add(post);
	}
	
	public List<Post> searchPost(String search) {
		ArrayList<Post> posts = new ArrayList<Post>();
		for (int id : postsByID.keySet()) {
			if (postsByID.get(id).getTitle().toLowerCase().contains(search.toLowerCase())) {
				posts.add(postsByID.get(id));
			}
		}
		return posts;
	}

	public List<Post> getAllPosts() throws SQLException, InvalidPostDataExcepetion {
		return PostDAO.instance.getAllPosts();
	}

	public List<Post> searchPostByCity(String city) throws SQLException, InvalidPostDataExcepetion {
		return PostDAO.instance.getAllPostsByCity(city);
	}

	public List<Post> searchPostByCountry(String country) throws SQLException, InvalidPostDataExcepetion {
		return PostDAO.instance.getAllPostsByCountry(country);
	}

	public int insertPost(Post newPost) throws InvalidPostDataExcepetion, SQLException {
		int postID = PostDAO.instance.insertPost(newPost);
		newPost.setPostID(postID);
		addPostToCache(newPost);
		return postID;
	}

	public String getThumbnail(int postID) throws SQLException {
		return PostDAO.instance.getThumbnailPath(postID);
	}
}
