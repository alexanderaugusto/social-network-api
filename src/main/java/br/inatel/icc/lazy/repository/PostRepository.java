package br.inatel.icc.lazy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.inatel.icc.lazy.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	@Query(value = "SELECT * FROM Post \r\n"
			+ "INNER JOIN User ON Post.owner_id = User.id \r\n"
			+ "INNER JOIN Follow ON Post.owner_id = Follow.follower_id \r\n"
			+ "WHERE Follow.following_id = :id",
			nativeQuery = true)
	List<Post> findUserTimeline(@Param("id") Long id);
}
