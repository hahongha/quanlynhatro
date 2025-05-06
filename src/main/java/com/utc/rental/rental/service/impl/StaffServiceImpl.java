package com.utc.rental.rental.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.staff.StaffDTO;
import com.utc.rental.rental.entity.Staff;
import com.utc.rental.rental.repository.StaffRepo;
import com.utc.rental.rental.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffRepo staffRepo;

	@Override
	public StaffDTO create(StaffDTO StaffDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StaffDTO update(StaffDTO StaffDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StaffDTO get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StaffDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StaffDTO getProfile(String userId) {
		Staff staff = staffRepo.findByUserId(userId).orElseThrow(()-> new BadRequestAlertException("Not Found User", "STAFF", "NOTFOUND"));
		return new ModelMapper().map(staff, StaffDTO.class);
	}

}
