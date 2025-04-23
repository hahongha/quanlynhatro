package com.utc.rental.rental.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rental.rental.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Optional<Role> findByRoleName(String roleName);
	
	Optional<Role> findByRoleNameOrId(String roleName, Long id);

	@Query("SELECT r FROM Role r WHERE r.roleName LIKE %:keyword%")
	Page<Role> search(@Param("keyword") String keyword, Pageable pageable);

}
