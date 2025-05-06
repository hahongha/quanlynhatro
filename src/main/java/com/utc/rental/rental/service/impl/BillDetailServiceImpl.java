package com.utc.rental.rental.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utc.rental.rental.dto.bill.BillDetailDTO;
import com.utc.rental.rental.entity.BillDetail;
import com.utc.rental.rental.repository.BillDetailRepo;
import com.utc.rental.rental.repository.BillRepo;
import com.utc.rental.rental.service.BillDetailService;
@Service
public class BillDetailServiceImpl implements BillDetailService{
	
	@Autowired
	private BillDetailRepo billDetailRepo;
	
	@Autowired
	private BillRepo billRepo;

	@Override
	public BillDetailDTO create(BillDetailDTO BillDetailDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillDetailDTO update(BillDetailDTO BillDetailDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillDetailDTO get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BillDetailDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BillDetailDTO> getByBillId(String id) {
		List<BillDetail> billDetails = billDetailRepo.findByBillId(id);
		if(billDetails.size()<=0) return new ArrayList<BillDetailDTO>();
		return billDetails.stream().map(b -> new ModelMapper().map(b, BillDetailDTO.class)).collect(Collectors.toList());
	}

}
