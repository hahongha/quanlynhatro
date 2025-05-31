package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchStaff;
import com.utc.rental.rental.dto.staff.StaffDTO;

public interface StaffService {
	StaffDTO create(StaffDTO StaffDTO);

	Boolean delete(String id);
	
	StaffDTO update(StaffDTO StaffDTO);
	
	StaffDTO get(String id);
	
	List<StaffDTO> getAll();
	ResponseDTO<List<StaffDTO>> search(SearchStaff searchStaff);
	
	Long count();
	
	StaffDTO getProfile(String userId);
}
