package br.inatel.icc.lazy.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.inatel.icc.lazy.model.Comment;
import br.inatel.icc.lazy.model.Post;
import br.inatel.icc.lazy.model.User;

public class CommentForm {

	@NotNull @NotEmpty
	private String description;

	public String getDescription() {
		return description;
	}

	public Comment toComment(User user, Post post) {
		Comment comment = new Comment(description, user, post);
		return comment;
	}
}
