package br.inatel.icc.lazy.integration.repository;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.inatel.icc.lazy.model.User;
import br.inatel.icc.lazy.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class UserRepositoryTest extends TestBase{

	@Autowired
	private UserRepository userRepository;
	private Pageable pageable;
	
	@BeforeEach
	public void beforeEach() {
		userRepository.saveAndFlush(this.getUserX());
	}
	
	@Test
	public void shouldFindUserByEmail() {
		User user = userRepository.findByEmail(this.getUserX().getEmail()).get();
		
		Assert.assertNotNull(user);
		Assert.assertEquals(this.getUserX().getEmail(), user.getEmail());
	}
	
	@Test
	public void shouldFindUsersByName() {
		Page<User> users = userRepository.findByNameContainingIgnoreCase(this.getUserX().getName(), pageable);
		
		Assert.assertNotNull(users);
		Assert.assertEquals(1, users.getContent().size());
	}

}
