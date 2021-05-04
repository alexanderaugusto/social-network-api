package br.inatel.icc.lazy.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.icc.lazy.controller.dto.PostDto;
import br.inatel.icc.lazy.controller.dto.UserDto;
import br.inatel.icc.lazy.controller.form.UserForm;
import br.inatel.icc.lazy.controller.form.UserFormUpdate;
import br.inatel.icc.lazy.model.Follow;
import br.inatel.icc.lazy.model.Post;
import br.inatel.icc.lazy.model.User;
import br.inatel.icc.lazy.repository.FollowRepository;
import br.inatel.icc.lazy.repository.PostRepository;
import br.inatel.icc.lazy.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserRepository userRepository;
	private FollowRepository followRepository;
	private PostRepository postRepository;
	
	@Autowired
	public UserController(UserRepository userRepository, FollowRepository followRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.followRepository = followRepository;
		this.postRepository = postRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> create(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
		User user = form.toUser();
		userRepository.save(user);

		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

		return ResponseEntity.status(201).location(uri).body(new UserDto(user));
	}

	@GetMapping
	public ResponseEntity<UserDto> list(Authentication authentication) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Optional<User> user = userRepository.findById(authenticatedUser.getId());

		if (user.isPresent()) {
			UserDto userDto = new UserDto(user.get());
			return ResponseEntity.status(200).body(userDto);
		}

		return ResponseEntity.status(404).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> list(@PathVariable("id") Long id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			UserDto userDto = new UserDto(user.get());
			return ResponseEntity.status(200).body(userDto);
		}

		return ResponseEntity.status(404).build();
	}

	@GetMapping("/{id}/posts")
	public ResponseEntity<List<PostDto>> listPosts(@PathVariable("id") Long id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			List<PostDto> posts = PostDto.toDtoList(user.get().getPosts());
			return ResponseEntity.status(200).body(posts);
		}

		return ResponseEntity.status(404).build();
	}

	@GetMapping("/{id}/followers")
	public ResponseEntity<List<UserDto>> listFollowers(@PathVariable("id") Long id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			List<UserDto> followers = UserDto.toDtoList(user.get().getFollowers());
			return ResponseEntity.status(200).body(followers);
		}

		return ResponseEntity.status(404).build();
	}

	@GetMapping("/{id}/followings")
	public ResponseEntity<List<UserDto>> listFollowings(@PathVariable("id") Long id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			List<UserDto> followings = UserDto.toDtoList(user.get().getFollowings());
			return ResponseEntity.status(200).body(followings);
		}

		return ResponseEntity.status(404).build();
	}

	@PutMapping("/follow/{id}")
	@Transactional
	public ResponseEntity<?> followUser(Authentication authentication, @PathVariable("id") Long id) {
		User authenticatedUser = (User) authentication.getPrincipal();
		
		Optional<User> userToFollow = userRepository.findById(id);
		
		if(userToFollow.isPresent()) {
			if(userToFollow.get().isFollowedBy(authenticatedUser)) {
				return ResponseEntity.status(403).build();
			}
			
			Follow follow = new Follow(authenticatedUser, userToFollow.get());
			followRepository.save(follow);
			
			return ResponseEntity.status(204).build();
		}
		
		return ResponseEntity.status(404).build();
	}
	
	@DeleteMapping("/follow/{id}")
	@Transactional
	public ResponseEntity<?> unfollowUser(Authentication authentication, @PathVariable("id") Long id){
		User authenticatedUser = (User) authentication.getPrincipal();
		Optional<User> userToUnfollow = userRepository.findById(id);
		
		if(userToUnfollow.isPresent()) {
//			if(!userToUnfollow.get().isFollowedBy(loggedUser)) {
//				return ResponseEntity.status(403).build();
//			}
			
			Follow follow = followRepository.findByFollowerAndFollowing(userToUnfollow, authenticatedUser);
			try {
				followRepository.deleteById(follow.getId());
				return ResponseEntity.status(204).build();
			} catch (NullPointerException e) {
				return ResponseEntity.status(403).build();
			}
		}
		
		return ResponseEntity.status(404).build();
	}
	
	@GetMapping("{id}/follow/{targetId}")
	@Transactional
	public ResponseEntity<?> followUser(@PathVariable("id") Long id, @PathVariable("targetId") Long targetId) {
		Optional<User> user = userRepository.findById(id);
		Optional<User> targetUser = userRepository.findById(targetId);
		
		if(user.isPresent() && targetUser.isPresent() && targetUser.get().isFollowedBy(user.get())) {
			return ResponseEntity.status(204).build();	
		}
		
		return ResponseEntity.status(404).build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> update(@RequestBody @Valid UserFormUpdate form, @PathVariable("id") Long id){
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			User newUser = form.update(user.get());
			return ResponseEntity.status(200).body(new UserDto(newUser));
		}
		
		return ResponseEntity.status(404).build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			user.get().getFollowers().clear();
			user.get().getFollowings().clear();
			userRepository.deleteById(id);
			return ResponseEntity.status(204).build();
		}
		
		return ResponseEntity.status(404).build();
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<UserDto>> search(@RequestParam(required = true, name = "name") String userName,
			@PageableDefault(sort="name", direction = Direction.ASC, page = 0, size = 10) Pageable pageable){
		Page<User> users = userRepository.findByNameContainingIgnoreCase(userName, pageable);
		Page<UserDto> usersDto = UserDto.toDtoPage(users);
		return ResponseEntity.status(200).body(usersDto);
	}
	
	@GetMapping("/timeline")
	@Cacheable(value = "timeline")
	public ResponseEntity<List<PostDto>> timeline(Authentication authentication, @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
		User authenticatedUser = (User) authentication.getPrincipal();
		
		List<Post> posts = postRepository.findUserTimeline(authenticatedUser.getId());
		List<PostDto> postsDto = PostDto.toDtoList(posts);
		
		return ResponseEntity.status(200).body(postsDto);
	}
}
