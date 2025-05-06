package com.utc.rental.rental.dto.roomType;

import java.util.ArrayList;
import java.util.List;

import com.utc.rental.rental.config.StringListConverter;

import jakarta.persistence.Convert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypeDTO {
	Long id;
	
	String name;
	
	Long size;
	
	String description;
	
	//các hình ảnh mẫu
	@Convert(converter = StringListConverter.class)
	List<String> imageList = new ArrayList<String>();
}
