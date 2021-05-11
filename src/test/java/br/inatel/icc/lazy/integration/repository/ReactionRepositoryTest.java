package br.inatel.icc.lazy.integration.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.inatel.icc.lazy.model.Reaction;
import br.inatel.icc.lazy.repository.PostRepository;
import br.inatel.icc.lazy.repository.ReactionRepository;
import br.inatel.icc.lazy.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class ReactionRepositoryTest extends TestBase{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ReactionRepository reactionRepository;
	
	@BeforeEach
	public void beforeEach() {
		userRepository.saveAndFlush(this.getUserX());
		userRepository.saveAndFlush(this.getUserY());
		postRepository.saveAndFlush(this.getPostUserX());
		reactionRepository.saveAndFlush(this.getReactionUserYPostX());
	}
	
	@Test
	public void shoudReturnThatUserYReactToPostX() {
		Optional<Reaction> reaction = reactionRepository.findByUserAndPost(this.getUserY(), this.getPostUserX());
	
		Assert.assertTrue(reaction.isPresent());
		Assert.assertEquals(this.getUserY().getId(), reaction.get().getUser().getId());
		Assert.assertEquals(this.getPostUserX().getId(), reaction.get().getPost().getId());
	}
	
	@Test
	public void shoudReturnThatUserXNotReactToPostX() {
		Optional<Reaction> reaction = reactionRepository.findByUserAndPost(this.getUserX(), this.getPostUserX());
	
		Assert.assertTrue(reaction.isEmpty());
	}
}
