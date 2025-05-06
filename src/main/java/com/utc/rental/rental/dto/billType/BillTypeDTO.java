package com.utc.rental.rental.dto.billType;



import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillTypeDTO {
    private String id; // Ví dụ: ROOM_RENT, ELECTRICITY...

    private String name; // Ví dụ: Tiền phòng, Tiền điện...
    
    //là thu hay chi nếu là thu thì trả về true còn đâu trả về false
    private boolean isExpense;
}
