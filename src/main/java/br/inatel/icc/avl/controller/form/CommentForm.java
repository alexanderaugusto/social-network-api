package br.inatel.icc.avl.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.inatel.icc.avl.model.Comment;
import br.inatel.icc.avl.model.Post;
import br.inatel.icc.avl.model.User;

public class CommentForm {

	@NotNull @NotEmpty @Length(min = 5, max = 250)
	private String description;

	public String getDescription() {
		return description;
	}

	public Comment toComment(User user, Post post) {
		Comment comment = new Comment(description, user, post);
		return comment;
	}
}
