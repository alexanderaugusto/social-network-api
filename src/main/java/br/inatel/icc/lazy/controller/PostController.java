package br.inatel.icc.lazy.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.icc.lazy.config.cloudinary.CloudinaryService;
import br.inatel.icc.lazy.controller.dto.CommentDto;
import br.inatel.icc.lazy.controller.dto.PostDto;
import br.inatel.icc.lazy.controller.dto.ReactionDto;
import br.inatel.icc.lazy.controller.form.CommentForm;
import br.inatel.icc.lazy.model.Comment;
import br.inatel.icc.lazy.model.Post;
import br.inatel.icc.lazy.model.Reaction;
import br.inatel.icc.lazy.model.User;
import br.inatel.icc.lazy.repository.CommentRepository;
import br.inatel.icc.lazy.repository.PostRepository;
import br.inatel.icc.lazy.repository.ReactionRepository;
import br.inatel.icc.lazy.repository.UserRepository;

@RestController
@RequestMapping("/posts")
public class PostController {

	private PostRepository postRepository;
	private UserRepository userRepository;
	private ReactionRepository reactionRepository;
	private CommentRepository commentRepository;
	private CloudinaryService cloudinaryService;

	@Autowired
	public PostController(PostRepository postRepository, UserRepository userRepository,
			ReactionRepository reactionRepository, CommentRepository commentRepository,
			CloudinaryService cloudinaryService) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
		this.reactionRepository = reactionRepository;
		this.commentRepository = commentRepository;
		this.cloudinaryService = cloudinaryService;
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	@CacheEvict(value = "timeline", allEntries = true)
	public ResponseEntity<PostDto> create(Authentication authentication,
			@RequestParam("description") String description,
			@RequestParam(value = "file", required = false) MultipartFile file, UriComponentsBuilder uriBuilder)
			throws IOException {
		User authenticatedUser = (User) authentication.getPrincipal();
		User user = userRepository.getOne(authenticatedUser.getId());

		String media = "default-post.jpg";

		if (file != null) {
			Map uploadResult = cloudinaryService.upload(file, "post");
			media = uploadResult.get("public_id").toString() + "." + uploadResult.get("format").toString();
		}

		Post post = new Post(description, media, user);
		postRepository.save(post);

		URI uri = uriBuilder.path("/posts/{id}").buildAndExpand(post.getId()).toUri();

		return ResponseEntity.status(201).location(uri).body(new PostDto(post));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> list(@PathVariable("id") Long id) {
		Optional<Post> post = postRepository.findById(id);

		if (post.isPresent()) {
			PostDto postDto = new PostDto(post.get());
			return ResponseEntity.status(200).body(postDto);
		}

		return ResponseEntity.status(404).build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "timeline", allEntries = true)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Optional<Post> post = postRepository.findById(id);

		if (post.isPresent()) {
			postRepository.deleteById(id);
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(404).build();
	}

	@PutMapping("/{id}/reactions")
	@Transactional
	public ResponseEntity<?> addReaction(Authentication authentication, @PathVariable("id") Long id) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Optional<Post> post = postRepository.findById(id);

		if (post.isPresent()) {
			Optional<Reaction> reaction = reactionRepository.findByUserAndPost(authenticatedUser, post.get());

			if (reaction.isPresent()) {
				return ResponseEntity.status(403).build();
			}

			Reaction newReaction = new Reaction(authenticatedUser, post.get());
			reactionRepository.save(newReaction);
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(404).build();
	}

	@DeleteMapping("/{id}/reactions")
	@Transactional
	public ResponseEntity<?> removeReaction(Authentication authentication, @PathVariable("id") Long id) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Optional<Post> post = postRepository.findById(id);
		Optional<Reaction> reaction = reactionRepository.findByUserAndPost(authenticatedUser, post.get());

		if (post.isPresent() && reaction.isPresent()) {
			reactionRepository.deleteById(reaction.get().getId());
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(404).build();
	}

	@GetMapping("/{id}/reactions")
	@Transactional
	public ResponseEntity<List<ReactionDto>> listReactions(@PathVariable("id") Long id) {
		Optional<Post> post = postRepository.findById(id);

		if (post.isPresent()) {
			List<Reaction> reactions = post.get().getReactions();
			List<ReactionDto> reactionsDto = ReactionDto.toDtoList(reactions);
			return ResponseEntity.status(200).body(reactionsDto);
		}

		return ResponseEntity.status(404).build();
	}

	@PostMapping("/{id}/comments")
	@Transactional
	public ResponseEntity<?> addComment(Authentication authentication, @RequestBody @Valid CommentForm form,
			@PathVariable("id") Long id) {
		User authenticatedUser = (User) authentication.getPrincipal();
		User user = userRepository.getOne(authenticatedUser.getId());

		Optional<Post> post = postRepository.findById(id);

		if (post.isPresent()) {
			Comment comment = form.toComment(user, post.get());
			commentRepository.save(comment);
			return ResponseEntity.status(201).body(new CommentDto(comment));
		}

		return ResponseEntity.status(404).build();
	}

	@DeleteMapping("/{id}/comments/{commentId}")
	@Transactional
	public ResponseEntity<?> removeComment(@PathVariable("id") Long id, @PathVariable("commentId") Long commentId) {
		Optional<Post> post = postRepository.findById(id);
		Optional<Comment> comment = commentRepository.findById(commentId);

		if (post.isPresent() && comment.isPresent()) {
			commentRepository.deleteById(commentId);
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(404).build();
	}

	@GetMapping("/{id}/comments")
	@Transactional
	public ResponseEntity<List<CommentDto>> listComments(@PathVariable("id") Long id) {
		Optional<Post> post = postRepository.findById(id);

		if (post.isPresent()) {
			List<Comment> comments = post.get().getComments();
			List<CommentDto> commentsDto = CommentDto.toDtoList(comments);
			return ResponseEntity.status(200).body(commentsDto);
		}

		return ResponseEntity.status(404).build();
	}
}
