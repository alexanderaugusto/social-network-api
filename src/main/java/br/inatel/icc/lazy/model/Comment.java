package br.inatel.icc.lazy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@ManyToOne
	private User user;

	@ManyToOne
	private Post post;

	public Comment() {
	}
	
	public Comment(String description, User user, Post post) {
		this.description = description;
		this.user = user;
		this.post = post;
	}
	
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
