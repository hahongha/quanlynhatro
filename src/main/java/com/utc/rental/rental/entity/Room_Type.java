package com.utc.rental.rental.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "room_type")
@Data
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room_Type extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
	@NotNull
	String id;
	
	Long room_size;
	
	List<String> furniture;
	
	//khu vực nhà bếp
	Boolean isKitchen;
	//wc
	Boolean isWC;
	
	//giá tiền
	Long cost;
}
