package com.utc.rental.rental.dto.status;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.search.SearchRoomReturn;
import com.utc.rental.rental.dto.user.UserResponse;
import com.utc.rental.rental.entity.Comment;
import com.utc.rental.rental.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusDTO {
	private String id;

	String title;

	private String content;

	private LocalDateTime createdAt;

	private UserResponse author;

	private List<CommentDTO> comments;

	private String status;
}
