package com.kabdi.springjwt.repository;

import java.util.List;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.stereotype.Repository;

import com.kabdi.springjwt.model.User;
import com.kabdi.springjwt.spec.SearchCriteria;

@Repository
public class UserDAO implements IUserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> searchUser(final List<SearchCriteria> params, int pageNumber, int pageSize) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<User> query = builder.createQuery(User.class);
        final Root r = query.from(User.class);
       
        Predicate predicate = builder.conjunction();
        UserSearchQueryCriteriaConsumer searchConsumer = new UserSearchQueryCriteriaConsumer(predicate, builder, r);
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);
  

        TypedQuery<User> typedQuery = entityManager.createQuery(query);
       /* while (pageNumber < count.intValue()) {
            typedQuery.setFirstResult(pageNumber - 1);
            typedQuery.setMaxResults(pageSize);
            System.out.println("Current page: " + typedQuery.getResultList());
            pageNumber += pageSize;
        }*/
        
        
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void save(final User entity) {
        entityManager.persist(entity);
    }
    
    
    @Override
    public long countUsers() {
    	long count;
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    	 
    	CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
    	countQuery.multiselect(criteriaBuilder.count(countQuery.from(User.class)));
    	return count = entityManager.createQuery(countQuery).getSingleResult();
    }

}
