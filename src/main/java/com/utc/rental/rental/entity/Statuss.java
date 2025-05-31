package com.utc.rental.rental.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.utc.rental.rental.config.StringListConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "status")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"author"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Statuss {
    @Id
    private String id;
    
	@Column(nullable = false)
	String title;

	@Column(columnDefinition = "LONGTEXT")
    private String content;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    private String status;
    
    private boolean isPublic;
    
//    @Convert(converter = StringListConverter.class)
//    @Column(columnDefinition = "TEXT")
//    private List<String> imageList;
}

