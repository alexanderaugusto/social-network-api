package br.inatel.icc.lazy.integration.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.inatel.icc.lazy.model.Post;
import br.inatel.icc.lazy.repository.FollowRepository;
import br.inatel.icc.lazy.repository.PostRepository;
import br.inatel.icc.lazy.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class PostRepositoryTest extends TestBase{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private FollowRepository followRepository;
	
	@BeforeEach
	public void beforeEach() {
		userRepository.saveAndFlush(this.getUserX());
		userRepository.saveAndFlush(this.getUserY());
		postRepository.saveAndFlush(this.getPostUserY());
		followRepository.saveAndFlush(this.getUserXFollowUserY());
	}
	
	@Test
	public void shouldReturnUserTimeline() {
		List<Post> timeline = postRepository.findUserTimeline(this.getUserX().getId());
		
		Assert.assertEquals(1, timeline.size());
	}
}
