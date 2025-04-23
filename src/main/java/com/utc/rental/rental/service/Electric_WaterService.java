package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.index.Electric_WaterDTO;


public interface Electric_WaterService {
	Electric_WaterDTO create(Electric_WaterDTO Electric_WaterDTO);

	Boolean delete(String id);
	
	Electric_WaterDTO update(Electric_WaterDTO Electric_WaterDTO);
	
	Electric_WaterDTO get(String id);
	
	List<Electric_WaterDTO> getAll();
}
