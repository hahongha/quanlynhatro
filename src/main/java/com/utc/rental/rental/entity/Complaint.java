package com.utc.rental.rental.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.utc.rental.rental.config.StringListConverter;

@Entity
@Table(name = "complaints")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, exclude = {"renter", "room", "resolvedBy"})
public class Complaint extends BaseModel {
	private static final long serialVersionUID = 1L;
    @Id
    String id;

    @Column(nullable = false)
    String title;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(nullable = false)
    String status = "WAITING";

    @Column(name = "submitted_at", nullable = false)
    LocalDateTime submittedAt;

    @Column(name = "resolved_at")
    LocalDateTime resolvedAt;

    String resolutionNote;

    @Convert(converter = StringListConverter.class)
	List<String> imageList = new ArrayList<String>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "renter_id", nullable = false)
    Renter renter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    Room room;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolved_by")
    User resolvedBy;
}
