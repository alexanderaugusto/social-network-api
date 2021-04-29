package br.inatel.icc.avl.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.icc.avl.controller.dto.PostDetailDto;
import br.inatel.icc.avl.controller.dto.PostDto;
import br.inatel.icc.avl.controller.form.PostForm;
import br.inatel.icc.avl.model.Post;
import br.inatel.icc.avl.model.User;
import br.inatel.icc.avl.repository.PostRepository;
import br.inatel.icc.avl.repository.UserRepository;

@RestController
@RequestMapping("/posts")
public class PostController {

	private PostRepository postRepository;
	private UserRepository userRepository;
	private final long myId = 1;
	
	@Autowired
	public PostController(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PostDto> create(@RequestBody @Valid PostForm postForm, UriComponentsBuilder uriBuilder){
		User user = userRepository.getOne(myId);
		
		Post post = postForm.toPost(user);
		postRepository.save(post);
		
		URI uri = uriBuilder.path("/posts/{id}").buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.status(201).location(uri).body(new PostDto(post));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDetailDto> list(@PathVariable("id") Long id){
		Optional<Post> post = postRepository.findById(id);
		
		if(post.isPresent()) {
			PostDetailDto postDto = new PostDetailDto(post.get());
			return ResponseEntity.status(200).body(postDto);
		}
		
		return ResponseEntity.status(404).build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		Optional<Post> post = postRepository.findById(id);
		
		if(post.isPresent()) {
			postRepository.deleteById(id);
			return ResponseEntity.status(204).build();
		}
		
		return ResponseEntity.status(404).build();
	}
}
