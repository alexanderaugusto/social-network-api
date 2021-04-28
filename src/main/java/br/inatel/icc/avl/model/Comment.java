package br.inatel.icc.avl.model;

public class Comment {

	private Long id;
	private String description;
	private User user;
	private Post post;
	
	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public User getUser() {
		return user;
	}
	
	public Post getPost() {
		return post;
	}
}
