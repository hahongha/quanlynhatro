package com.utc.rental.rental.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rental.rental.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, String> {
//    Page<Comment> findByStatusIdOrderByCreatedAtAsc(String statusId, Pageable pageable);
	List<Comment> findByStatusIdOrderByCreatedAtAsc(String statusId);
}