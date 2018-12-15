package com.kabdi.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kabdi.springjwt.model.Role;
import com.kabdi.springjwt.model.RoleName;

import java.util.Optional;

/**
 * Created by jndanial.com (danial jalal nouri).
 * http://http://www.jndanial.com/54/
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
}
