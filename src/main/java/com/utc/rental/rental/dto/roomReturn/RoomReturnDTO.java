package com.utc.rental.rental.dto.roomReturn;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomReturnDTO {
    private String id;
	private String contractId;
    private Long roomId;
    private LocalDate returnDate;
    private String reason;
    private String note;
    private String status;
}
