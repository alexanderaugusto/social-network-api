package br.inatel.icc.lazy.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.inatel.icc.lazy.model.Post;
import br.inatel.icc.lazy.model.User;

public class PostForm {

	@NotNull @NotEmpty @Length(min = 10, max = 250)
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

