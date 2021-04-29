package br.inatel.icc.avl.controller.dto;

import br.inatel.icc.avl.model.Reaction;

public class ReactionDto {

	private UserDto user;
	
	public ReactionDto(Reaction rection) {
		this.user = new UserDto(rection.getUser());
	}

	public UserDto getUser() {
		return user;
	}
}
