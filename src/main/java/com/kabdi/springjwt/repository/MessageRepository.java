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

	@Query(value="select * from messages m  " +
			" left join users u on m.recipient_id=u.id  " +
			" left join photos pr on m.recipient_id=pr.id " + 
			" left join photos ps on m.sender_id=ps.id  " +
			" where  m.recipient_id=:userId and m.recipient_deleted is FALSE " +
			" and (pr.id is null or pr.is_main is true) " +
			" and (ps.id is null or ps.is_main is true)", nativeQuery = true)

	Page<Message> findMessagesInForUser(@Param("userId") int userId, Pageable pageableRequest); 
	
	@Query(value="select * from messages m " + 
			" left join users u on m.sender_id=u.id " + 
			" left join photos pr on m.recipient_id=pr.id " + 
			" left join photos ps on m.sender_id=ps.id  " +
			" where m.sender_id=:userId and m.sender_deleted is FALSE " +
			" and (pr.id is null or pr.is_main is true) " +
			" and (ps.id is null or ps.is_main is true) " +
			" order by m.message_sent desc", nativeQuery = true)
	
	Page<Message> findMessagesOutForUser(@Param("userId") int userId, Pageable pageableRequest); 
	
	@Query(value="select * from messages m  " +
			" left join users u on m.recipient_id=u.id  " +
			" left join photos pr on m.recipient_id=pr.id " + 
			" left join photos ps on m.sender_id=ps.id  " +
			" where  m.recipient_id=:userId and m.recipient_deleted is FALSE and m.is_read is FALSE " +
			" and (pr.id is null or pr.is_main is true) " +
			" and (ps.id is null or ps.is_main is true) order by m.message_sent desc ", nativeQuery = true)
	
	Page<Message> findMessagesDefaultForUser(@Param("userId") int userId, Pageable pageableRequest); 
   
	@Query("select m from Message m join fetch m.recipient r join fetch m.sender s where r.id = :userId and m.recipientDeleted is FALSE and s.id = :recipientId " +
			" or r.id = :recipientId and s.id = :userId and m.senderDeleted is FALSE order by m.messageSent desc")
	List<Message> getMessageThread(@Param("userId") int userId, @Param("recipientId") int recipientId); 
}
