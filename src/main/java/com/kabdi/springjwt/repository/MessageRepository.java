package com.kabdi.springjwt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kabdi.springjwt.model.Message;

import java.util.List;

import javax.transaction.Transactional;

/**
 * Created by Khalid Abdi
 */
@Transactional
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>, JpaSpecificationExecutor<Message> {

	@Query(value="select m from Message m join fetch m.recipient r join fetch r.photos p where p.isMain is TRUE and r.id = :userId and m.recipientDeleted is FALSE order by m.messageSent desc",
			countQuery="select count(m) from Message m join m.recipient r join r.photos p where p.isMain is TRUE and r.id = :userId and m.recipientDeleted is FALSE")
	Page<Message> findMessagesInForUser(@Param("userId") int userId, Pageable pageableRequest); 
	
	@Query(value="select m from Message m join fetch m.sender s join fetch m.recipient where s.id = :userId and m.senderDeleted is FALSE order by m.messageSent desc",
			countQuery="select count(m) from Message m join m.sender s where s.id = :userId and m.senderDeleted is FALSE")
//	@Query("select m from Message m join m.sender s where s.id = :userId and m.senderDeleted is FALSE order by m.messageSent desc")
	Page<Message> findMessagesOutForUser(@Param("userId") int userId, Pageable pageableRequest); 
	
	@Query(value="select m from Message m join fetch m.sender s join fetch m.recipient r where r.id = :userId and m.recipientDeleted is FALSE and m.isRead is FALSE order by m.messageSent desc",
			countQuery="select count(m) from Message m join m.sender s join m.recipient r where r.id = :userId and m.recipientDeleted is FALSE and m.isRead is FALSE")
	Page<Message> findMessagesDefaultForUser(@Param("userId") int userId, Pageable pageableRequest); 
   
	@Query("select m from Message m join fetch m.recipient r join fetch m.sender s where r.id = :userId and m.recipientDeleted is FALSE and s.id = :recipientId " +
			" or r.id = :recipientId and s.id = :userId and m.senderDeleted is FALSE order by m.messageSent desc")
	List<Message> getMessageThread(@Param("userId") int userId, @Param("recipientId") int recipientId); 
}
