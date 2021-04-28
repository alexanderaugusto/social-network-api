package br.inatel.icc.avl.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.icc.avl.controller.form.UserForm;
import br.inatel.icc.avl.model.User;
import br.inatel.icc.avl.repository.UserRepository;
import br.inatel.icc.dto.UserDto;

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
	public ResponseEntity<UserDto> create(@RequestBody UserForm form, UriComponentsBuilder uriBuilder){
		User user = form.toUser();
		userRepository.save(user);
		
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.status(201).location(uri).body(new UserDto(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> list(@PathVariable("id") Long id){
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(optionalUser.isPresent()) {
			UserDto user = new UserDto(optionalUser.get());
			return ResponseEntity.status(200).body(user);
		}
		
		return ResponseEntity.status(404).build();
	}
}
