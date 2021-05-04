package br.inatel.icc.lazy.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.icc.lazy.model.Comment;

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

	public static List<CommentDto> toDtoList(List<Comment> comments) {
		List<CommentDto> commentsDto = comments.stream().map(CommentDto::new).collect(Collectors.toList());
		return commentsDto;
	}
}
