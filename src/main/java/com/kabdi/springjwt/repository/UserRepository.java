package com.kabdi.springjwt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kabdi.springjwt.model.User;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

/**
 * Created by Khalid Abdi
 */
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> { 

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
    
    @Query(value="select u from User u left join fetch u.photos p where u.gender = :gender and u.dateOfBirth >= :minDob and u.dateOfBirth <= :maxDob order by u.lastActive desc",
    		countQuery="select count(u) from User u where u.gender = :gender and u.dateOfBirth >= :minDob and u.dateOfBirth <= :maxDob")
    Page<User> findUsersLastActive(@Param("gender") String gender, @Param("minDob") Date minDob, @Param("maxDob") Date maxDob, Pageable pageable);
    
    @Query(value="select u from User u left join fetch u.photos p where u.gender = :gender and u.dateOfBirth >= :minDob and u.dateOfBirth <= :maxDob order by u.created desc",
    		countQuery="select count(u) from User u left join u.photos p where u.gender = :gender and u.dateOfBirth >= :minDob and u.dateOfBirth <= :maxDob")
    Page<User> findUsersCreated(@Param("gender") String gender, @Param("minDob") Date minDob, 
    		@Param("maxDob") Date maxDob, Pageable pageable);
        
    @Query(value ="select * from users u left join likes l on u.id = l.liker_id left join photos p on p.user_id = u.id where (p.id is null or p.is_main is true) and l.likee_id =:id order by u.created desc", 
      countQuery = "select count(u) from users u left join likes l on u.id = l.liker_id left join photos p on p.user_id = u.id where where (p.id is null or p.is_main is true) and l.likee_id =:id", nativeQuery = true)
    Page<User> findUsersLikers(@Param("id") int id, Pageable pageable);
    
    @Query(value="select * from users u left join likes l on u.id = l.likee_id left join photos p on p.user_id = u.id where (p.id is null or p.is_main is true) and l.liker_id=:id order by u.created desc", 
    countQuery = "select count(u) from users u left join likes l on u.id = l.likee_id left join photos p on p.user_id = u.id where (p.id is null or p.is_main is true) and l.liker_id=:id", nativeQuery = true)
    Page<User> findUsersLikees(@Param("id") int id, Pageable pageable);
    
    @Modifying
    @Query("update User u set u.lastActive = ?1 where u.id = ?2")
    void updateUserLastActive(Date lastActive, int id);
    
    @Query("select user from User user left join fetch user.photos where user.id =:id")
    Optional<User> findOneWithPhotos(@Param("id") int id);
    
    @Query("select user from User user left join fetch user.photos where user.username =:username")
    Optional<User> findOneWithPhotos(@Param("username") String username);
    
    @Query(value="select * from users u  " +
			" left join photos p on u.id=p.user_id " + 
			" where u.id=:userId and (p.id is null or p.is_main is true)", nativeQuery = true)
    
    User findOneWithMainPhoto(@Param("userId") int userId);
    
    @Query("select user.knownAs from User user where user.id =:userId")
	String findKnownAsForUser(@Param("userId") int userId);
    
    

}
