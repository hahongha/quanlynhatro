package com.utc.rental.rental.dto.status;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.utc.rental.rental.dto.user.UserResponse;
import com.utc.rental.rental.entity.Statuss;
import com.utc.rental.rental.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDTO {
    private String id;

    private String content;
    
    private LocalDateTime createdAt;

    private String statusId;

    private UserResponse author;
}
