package com.kabdi.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kabdi.springjwt.model.Like;


public interface LikeRepository extends JpaRepository<Like, Integer>, JpaSpecificationExecutor<Like> {
	
	@Query("select l from Like l where l.liker.id =:userId and l.likee.id =:recipientId")
	public Optional<Like> findUserLike(@Param("userId") int userId, @Param("recipientId") int recipientId);

}
