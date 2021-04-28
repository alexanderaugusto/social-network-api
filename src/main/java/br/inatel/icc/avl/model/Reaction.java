package br.inatel.icc.avl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reaction {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Post post;
	
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
