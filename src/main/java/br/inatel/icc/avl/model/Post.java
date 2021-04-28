package br.inatel.icc.avl.model;

import java.util.ArrayList;
import java.util.List;

public class Post {

	private Long id;
	private String description;
	private String media; // video or image (modify later)
	private List<Comment> comments;
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

	public List<Reaction> getReactions() {
		return reactions;
	}

	public List<Comment> getComments() {
		return comments;
	}
}
