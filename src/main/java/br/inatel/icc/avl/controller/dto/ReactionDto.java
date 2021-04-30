package br.inatel.icc.avl.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.icc.avl.model.Reaction;

public class ReactionDto {

	private Long id;
	private UserDto user;
	
	public ReactionDto(Reaction reaction) {
		this.id = reaction.getId();
		this.user = new UserDto(reaction.getUser());
	}

	public Long getId() {
		return id;
	}

	public UserDto getUser() {
		return user;
	}

	public static List<ReactionDto> toDtoList(List<Reaction> reactions) {
		List<ReactionDto> reactionsDto = reactions.stream().map(ReactionDto::new).collect(Collectors.toList());
		return reactionsDto;
	}
}
