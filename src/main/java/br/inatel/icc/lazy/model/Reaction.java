package br.inatel.icc.lazy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Post post;
	
	public Reaction() {
	}
	
	public Reaction(User user, Post post) {
		this.user = user;
		this.post = post;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Post getPost() {
		return post;
	}
}
