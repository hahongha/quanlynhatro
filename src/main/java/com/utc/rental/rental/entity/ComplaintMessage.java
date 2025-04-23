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
//@Data
//@Entity
//@Table(name = "complaint_messages")
//@EqualsAndHashCode(callSuper = false)
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class ComplaintMessage {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long messageId;
//
//    @ManyToOne
//    @JoinColumn(name = "complaint_id", nullable = false)
//    private Complaint complaint;
//
//    @ManyToOne
//    @JoinColumn(name = "sender_id", nullable = false)
//    private User sender; // Người gửi (khách thuê hoặc chủ trọ)
//
//    @Column(columnDefinition = "TEXT", nullable = false)
//    private String messageContent;
//
//    @Column(nullable = false)
//    private LocalDateTime sentAt = LocalDateTime.now();
//
//    @Column(length = 255)
//    private String attachment;
//}
