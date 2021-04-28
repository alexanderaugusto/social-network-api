package br.inatel.icc.avl.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String media; // video or image (modify later)
	@ManyToOne
	private User owner;
	@OneToMany(mappedBy = "post")
	private List<Comment> comments;
	@OneToMany(mappedBy = "post")
	private List<Reaction> reactions;
	
	public Post() {
		reactions = new ArrayList<>();
		comments = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public String getMedia() {
		return media;
	}

	public User getOwner() {
		return owner;
	}

	public List<Reaction> getReactions() {
		return reactions;
	}

	public List<Comment> getComments() {
		return comments;
	}
}
