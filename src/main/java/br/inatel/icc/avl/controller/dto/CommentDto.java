package br.inatel.icc.avl.controller.dto;

import br.inatel.icc.avl.model.Comment;

public class CommentDto {

	private Long id;
	private String description;
	private UserDto user;
	
	public CommentDto(Comment comment) {
		this.id = comment.getId();
		this.description = comment.getDescription();
		this.user = new UserDto(comment.getUser());
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public UserDto getUser() {
		return user;
	}
}
