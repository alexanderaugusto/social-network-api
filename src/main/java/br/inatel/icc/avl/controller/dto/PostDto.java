package br.inatel.icc.avl.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.icc.avl.model.Post;

public class PostDto {

	private Long id;
	private String description;
	private String media;
	private int totalReactions;
	private int totalComments;
	
	public PostDto(Post post) {
		this.id = post.getId();
		this.description = post.getDescription();
		this.media = post.getMedia();
		this.totalReactions = post.getReactions().size();
		this.totalComments = post.getComments().size();
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
	
	public int getTotalReactions() {
		return totalReactions;
	}

	public int getTotalComments() {
		return totalComments;
	}

	public static List<PostDto> toDtoList(List<Post> posts) {
		List<PostDto> postsDto = posts.stream().map(PostDto::new).collect(Collectors.toList());
		return postsDto;
	}

}
