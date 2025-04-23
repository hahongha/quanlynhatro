package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.StaffDTO;

public interface StaffService {
	StaffDTO create(StaffDTO StaffDTO);

	Boolean delete(String id);
	
	StaffDTO update(StaffDTO StaffDTO);
	
	StaffDTO get(String id);
	
	List<StaffDTO> getAll();
	Long count();
}
