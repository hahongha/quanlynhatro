package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.RenterDTO;
import com.utc.rental.rental.dto.SearchDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.entity.Renter;

public interface RenterService {
	RenterDTO create(RenterDTO RenterDTO);

	RenterDTO update(RenterDTO RenterDTO);

	Renter get(String id);
	
	Renter getIdentification(String id);
	
	Renter findByID(String id, String identification);

	List<RenterDTO> getAll();

	Boolean delete(String id);

	ResponseDTO<List<RenterDTO>> search(SearchDTO searchDTO);
	Long count();
	
	RenterDTO findByUserId(String id);
}
