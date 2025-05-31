//package com.utc.rental.rental.service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.utc.rental.rental.api.error.BadRequestAlertException;
//import com.utc.rental.rental.dto.billType.BillTypeDTO;
//import com.utc.rental.rental.entity.BillType;
//import com.utc.rental.rental.repository.BillTypeRepo;
//
//public interface BillTypeService {
//	BillTypeDTO create(BillTypeDTO BillTypeDTO);
//	
//	BillTypeDTO update(BillTypeDTO BillTypeDTO);
//	
//	BillTypeDTO get(String id);
//	
//	List<BillTypeDTO> getAll();
//
//	Boolean delete(String id);	
//	
//	Long count();
//}
//@Service
//class BillTypeServiceImpl implements BillTypeService{
//
//	@Autowired
//	private BillTypeRepo billTypeRepo;
//	
//	@Override
//	public BillTypeDTO create(BillTypeDTO BillTypeDTO) {
//		if(billTypeRepo.existsById(BillTypeDTO.getId().toUpperCase().toString()))
//			throw new BadRequestAlertException("Loại bill tồn tại", "BillTypeDTO", "EXISTS");
//		BillType billType = new ModelMapper().map(BillTypeDTO, BillType.class);
//		billTypeRepo.save(billType);
//		return BillTypeDTO;
//	}
//
//	@Override
//	public BillTypeDTO update(BillTypeDTO BillTypeDTO) {
//		if(!billTypeRepo.existsById(BillTypeDTO.getId().toUpperCase().toString()))
//			throw new BadRequestAlertException("Loại bill không tồn tại", "BillTypeDTO", "NotFound");
//		BillType billType = billTypeRepo.findById(BillTypeDTO.getId()).orElseThrow();
//		billType.setName(BillTypeDTO.getName());
//		billType.setExpense(BillTypeDTO.isExpense());
//		billTypeRepo.save(billType);
//		return BillTypeDTO;
//	}
//
//	@Override
//	public BillTypeDTO get(String id) {
//		return new ModelMapper().map(billTypeRepo.findById(id).orElseThrow(), BillTypeDTO.class);
//	}
//
//	@Override
//	public List<BillTypeDTO> getAll() {
//		ModelMapper mapper = new ModelMapper();
//		return billTypeRepo.findAll().stream().map(b -> mapper.map(b, BillTypeDTO.class)).collect(Collectors.toList());
//	}
//
//	@Override
//	public Boolean delete(String id) {
//		try {
//			get(id);
//			billTypeRepo.deleteById(id);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
//
//	@Override
//	public Long count() {
//		return billTypeRepo.count();
//	}
//	
//}