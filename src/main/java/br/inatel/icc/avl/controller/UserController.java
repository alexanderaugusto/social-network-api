package br.inatel.icc.avl.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.icc.avl.controller.dto.PostDto;
import br.inatel.icc.avl.controller.dto.UserDto;
import br.inatel.icc.avl.controller.form.UserForm;
import br.inatel.icc.avl.model.User;
import br.inatel.icc.avl.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> create(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
		User user = form.toUser();
		userRepository.save(user);

		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

		return ResponseEntity.status(201).location(uri).body(new UserDto(user));
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
	public ResponseEntity<?> followUser(@PathVariable("id") Long id) {
		long myId = 1;
		User loggedUser = userRepository.getOne(myId);
		Optional<User> userToFollow = userRepository.findById(id);
		
		if(userToFollow.isPresent()) {
			loggedUser.addFollowing(userToFollow.get());
			userToFollow.get().addFollower(loggedUser);
			return ResponseEntity.status(204).build();
		}
		
		return ResponseEntity.status(404).build();
	}
	
	@DeleteMapping("/follow/{id}")
	@Transactional
	public ResponseEntity<?> unfollowUser(@PathVariable("id") Long id){
		long myId = 1;
		User loggedUser = userRepository.getOne(myId);
		Optional<User> userToUnfollow = userRepository.findById(id);
		
		if(userToUnfollow.isPresent()) {
			loggedUser.removeFollowing(userToUnfollow.get());
			userToUnfollow.get().removeFollower(loggedUser);
			return ResponseEntity.status(204).build();
		}
		
		return ResponseEntity.status(404).build();
	}
}
