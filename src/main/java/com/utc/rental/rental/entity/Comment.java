package com.utc.rental.rental.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.utc.rental.rental.config.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "comment")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"status", "author"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    @Id
    private String id;

    @Column(columnDefinition = "LONGTEXT")
    private String content;
    
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Statuss status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
}

