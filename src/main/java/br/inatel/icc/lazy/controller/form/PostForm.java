package br.inatel.icc.lazy.controller.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.inatel.icc.lazy.model.Post;
import br.inatel.icc.lazy.model.User;

public class PostForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull @NotEmpty
	private String description;
	private String media;
	
	public String getDescription() {
		return description;
	}
	
	public String getMedia() {
		return media;
	}

	public Post toPost(User owner) {
		Post post = new Post(description, media, owner);
		return post;
	}
}

