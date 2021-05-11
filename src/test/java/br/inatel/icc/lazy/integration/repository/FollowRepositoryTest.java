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

import br.inatel.icc.lazy.model.Follow;
import br.inatel.icc.lazy.repository.FollowRepository;
import br.inatel.icc.lazy.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class FollowRepositoryTest extends TestBase {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FollowRepository followRepository;
	
	@BeforeEach
	public void beforeEach() {
		userRepository.saveAndFlush(this.getUserX());
		userRepository.saveAndFlush(this.getUserY());
		followRepository.saveAndFlush(this.getUserXFollowUserY());
	}
	
	@Test
	public void shouldReturnThatUserXFollowUserY() {
		Optional<Follow> follow = followRepository.findByFollowingAndFollower(this.getUserX(), this.getUserY());
		
		Assert.assertTrue(follow.isPresent());
	}
	
	@Test
	public void shouldReturnThatUserYNotFollowUserX() {
		Optional<Follow> follow = followRepository.findByFollowingAndFollower(this.getUserY(), this.getUserX());
		
		Assert.assertTrue(follow.isEmpty());
	}
}
