package br.inatel.icc.avl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.avl.model.Follow;
import br.inatel.icc.avl.model.User;

public interface FollowRepository extends JpaRepository<Follow, Long>{

	Follow findByFollowerAndFollowing(Optional<User> userToUnfollow, User loggedUser);
}
