package com.utc.rental.rental.entity;


import java.util.ArrayList;
import java.util.List;

import com.utc.rental.rental.config.StringListConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "room_type")
@Data
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomType extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
	Long id;
	
	String name;
	
	Long size;
	
	String description;
	
	//các hình ảnh mẫu
	@Convert(converter = StringListConverter.class)
	List<String> imageList = new ArrayList<String>();
}
