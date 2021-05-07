package br.inatel.icc.lazy.controller.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.inatel.icc.lazy.model.Post;

public class PostDto {

	private Long id;
	private String description;
	private String media;
	private int totalReactions;
	private int totalComments;
	private UserDto owner;
	private List<Long> reactions;
	
	public PostDto(Post post) {
		this.id = post.getId();
		this.description = post.getDescription();
		this.media = post.getMedia();
		this.totalReactions = post.getReactions().size();
		this.totalComments = post.getComments().size();
		this.owner = new UserDto(post.getOwner());
		
		this.reactions = new ArrayList<>();
		this.reactions = post.getReactions().stream().map(e -> e.getUser().getId()).collect(Collectors.toList());
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

	public List<Long> getReactions() {
		return reactions;
	}

	public static List<PostDto> toDtoList(List<Post> posts) {
		Collections.reverse(posts);
		List<PostDto> postsDto = posts.stream().map(PostDto::new).collect(Collectors.toList());
		return postsDto;
	}
}
