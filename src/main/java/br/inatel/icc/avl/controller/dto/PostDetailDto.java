package br.inatel.icc.avl.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.icc.avl.model.Post;

public class PostDetailDto {

	private Long id;
	private String description;
	private String media;
	private int totalReactions;
	private int totalComments;
	private UserDto owner;
	
	public PostDetailDto(Post post) {
		this.id = post.getId();
		this.description = post.getDescription();
		this.media = post.getMedia();
		this.totalReactions = post.getReactions().size();
		this.totalComments = post.getComments().size();
		this.owner = new UserDto(post.getOwner());
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

	public static List<PostDetailDto> toDtoList(List<Post> posts) {
		List<PostDetailDto> postsDto = posts.stream().map(PostDetailDto::new).collect(Collectors.toList());
		return postsDto;
	}
}
