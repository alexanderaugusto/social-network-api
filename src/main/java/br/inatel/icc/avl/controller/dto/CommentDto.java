package br.inatel.icc.avl.controller.dto;

import br.inatel.icc.avl.model.Comment;

public class CommentDto {

	private String description;
	private UserDto user;
	
	public CommentDto(Comment comment) {
		this.description = comment.getDescription();
		this.user = new UserDto(comment.getUser());
	}

	public String getDescription() {
		return description;
	}

	public UserDto getUser() {
		return user;
	}
}
