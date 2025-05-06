package com.utc.rental.rental.entity;

import java.util.List;

import com.utc.rental.rental.config.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "room")
@Data
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Room extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
	private Long id;

	String roomNumber;

	//trạng thái hiện tại( hoạt động, đang sửa, ...)
	String status;
	
	Long cost;
	
	//có đang thuê không
	Boolean isActive;
	
	@Convert(converter = StringListConverter.class)
	List<String> funiture;
	
	// mô tả về phòng trọ
	String description;
	
	//số người thuê hiện tại
	int number;
	
	//electric
	double electricIndex;
	//water
	double waterIndex;
		
	@ManyToOne(fetch = FetchType.LAZY)
	RoomType room_Type;
	
	//các hình ảnh thực tế
	@Convert(converter = StringListConverter.class)
	@Column(columnDefinition = "TEXT")
	List<String> images;
}
