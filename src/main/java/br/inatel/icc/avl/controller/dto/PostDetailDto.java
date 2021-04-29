package br.inatel.icc.avl.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.inatel.icc.avl.model.Post;

public class PostDetailDto {

	private Long id;
	private String description;
	private String media;
	private UserDto owner;
	private int totalReactions;
	private int totalComments;
	private List<CommentDto> comments;
	private List<ReactionDto> reactions;
	
	public PostDetailDto(Post post) {
		this.id = post.getId();
		this.description = post.getDescription();
		this.media = post.getMedia();
		this.owner = new UserDto(post.getOwner());
		this.totalReactions = post.getReactions().size();
		this.totalComments = post.getComments().size();
		
		this.comments = new ArrayList<>();
		this.comments.addAll(post.getComments().stream().map(CommentDto::new).collect(Collectors.toList()));
		
		this.reactions = new ArrayList<>();
		this.reactions.addAll(post.getReactions().stream().map(ReactionDto::new).collect(Collectors.toList()));
		
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

	public UserDto getOwner() {
		return owner;
	}

	public int getTotalReactions() {
		return totalReactions;
	}

	public int getTotalComments() {
		return totalComments;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public List<ReactionDto> getReactions() {
		return reactions;
	}
}
