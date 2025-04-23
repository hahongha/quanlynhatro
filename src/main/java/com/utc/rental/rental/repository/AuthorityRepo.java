package com.utc.rental.rental.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.utc.rental.rental.entity.Authority;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long> {
	Optional<Authority> findByName(String name);
	
	List<Authority> findByRolesId(Long roleId);
	
	@Query("Select e from Authority e where e.name like :x or e.description like :x ")
	Page<Authority> search(@Param("x") String value, Pageable pageable);
}
