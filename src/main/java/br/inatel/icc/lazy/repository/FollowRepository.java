package br.inatel.icc.lazy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.lazy.model.Follow;
import br.inatel.icc.lazy.model.User;

public interface FollowRepository extends JpaRepository<Follow, Long>{

	Optional<Follow> findByFollowingAndFollower(User following, User follower);
}
