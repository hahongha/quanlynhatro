package com.utc.rental.rental.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "bill_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillType {

    @Id
    private String id; // Ví dụ: ROOM_RENT, ELECTRICITY...

    @Column(nullable = false, unique = true)
    private String name; // Ví dụ: Tiền phòng, Tiền điện...
    
    //là thu hay chi nếu là thu thì trả về true còn đâu trả về false
    @Column(name = "is_expense", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isExpense;
}

