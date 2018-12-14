package com.kabdi.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kabdi.springjwt.model.Photo;

import java.util.Optional;

import javax.transaction.Transactional;

/**
 * Created by Khalid Abdi
 */
@Transactional
@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer>, JpaSpecificationExecutor<Photo> {

	@Query("select photo from Photo photo where photo.user.id =:userId and photo.isMain is TRUE")
	public Optional<Photo> findMainPhotoForUser(@Param("userId") int userId);
	
	@Query("select photo.url from Photo photo where photo.user.id =:userId and photo.isMain is TRUE")
	public String findMainPhotoUrlForUser(@Param("userId") int userId);

}
