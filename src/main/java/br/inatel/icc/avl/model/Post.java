package br.inatel.icc.avl.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private String media; // video or image (modify later)

	@ManyToOne
	private User owner;

	@OneToMany(mappedBy = "post", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Reaction> reactions = new ArrayList<>();

	public Post() {
	}
	
	public Post(String description, String media, User owner) {
		this.description = description;
		this.media = media;
		this.owner = owner;
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
