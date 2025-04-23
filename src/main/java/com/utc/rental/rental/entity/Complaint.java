//package com.utc.rental.rental.entity;
//
//
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.experimental.FieldDefaults;
//
//import java.time.LocalDateTime;
//
//
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@Entity
//@Table(name = "complaint")
//@Data
//@EqualsAndHashCode(callSuper = false)
//public class Complaint {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long complaintId;
//
//    @ManyToOne
//    @JoinColumn(name = "renter_id", nullable = false)
//    private Renter renter;
//
//    @ManyToOne
//    @JoinColumn(name = "room_id", nullable = false)
//    private Room room;
//
//    @Column(nullable = false, length = 255)
//    private String title;
//
//    @Column(columnDefinition = "TEXT", nullable = false)
//    private String description;
//
//    @Column(nullable = false)
//    private String type;
//
//    @Column(nullable = false, columnDefinition = "ENUM('Chờ xử lý', 'Đang xử lý', 'Đã giải quyết')")
//    private String status;
//
//    @Column(nullable = false, updatable = false)
//    private LocalDateTime createdAt = LocalDateTime.now();
//
//    @ManyToOne
//    @JoinColumn(name = "handled_by")
//    private User handledBy;
//
//    @Column(columnDefinition = "TEXT")
//    private String response;
//
//    @Column
//    private LocalDateTime updatedAt;
//
//    @Column(length = 255)
//    private String attachment;
//}
//
