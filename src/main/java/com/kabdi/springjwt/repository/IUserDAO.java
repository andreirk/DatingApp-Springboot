package com.kabdi.springjwt.repository;

import java.util.List;

import com.kabdi.springjwt.model.User;
import com.kabdi.springjwt.spec.SearchCriteria;



public interface IUserDAO {
    List<User> searchUser(List<SearchCriteria> params, int pageNumber, int pageSize);

    void save(User entity);

	long countUsers();
}
