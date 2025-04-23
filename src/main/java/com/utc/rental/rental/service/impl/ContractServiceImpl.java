//package com.utc.rental.rental.service.impl;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.modelmapper.ModelMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.HttpServerErrorException;
//import org.springframework.web.client.ResourceAccessException;
//import org.zalando.problem.Problem;
//import org.zalando.problem.Status;
//
//import com.utc.rental.rental.api.error.BadRequestAlertException;
//import com.utc.rental.rental.dto.RenterDTO;
//import com.utc.rental.rental.dto.SearchDTO;
//import com.utc.rental.rental.dto.contract.ContractDTO;
//import com.utc.rental.rental.dto.response.ResponseDTO;
//import com.utc.rental.rental.entity.Contract;
//import com.utc.rental.rental.entity.Renter;
//import com.utc.rental.rental.entity.Room;
//import com.utc.rental.rental.repository.ContractRepo;
//import com.utc.rental.rental.service.ContractService;
//import com.utc.rental.rental.service.RenterService;
//import com.utc.rental.rental.service.RoomService;
//@Service
//public class ContractServiceImpl implements ContractService {
//
//	@Autowired
//	ContractRepo contractRepo;
//	
//	@Autowired
//	RenterService renterService;
//	
//	@Autowired
//	RoomService roomService;
//
//	private static final Logger LOG = LoggerFactory.getLogger(ContractServiceImpl.class);
//
//	@Override
//	public ContractDTO create(ContractDTO ContractDTO) {
//		LOG.info("Create Contract: " + ContractDTO.toString());
//
//		ModelMapper mapper = new ModelMapper();
//		Renter renter = new Renter();
//		try {
//			renter = renterService.findByID(ContractDTO.getRenter().getId(), ContractDTO.getRenter().getIdentification());
//		} catch (Exception e) {
//			RenterDTO renterDTO = renterService.create(ContractDTO.getRenter());
//			renter = renterService.getIdentification(renterDTO.getIdentification());
//		}
//		
//		Contract Contract = mapper.map(ContractDTO, Contract.class);
//		Contract.setRenter(renter);
//		
//		Room room = roomService.get(ContractDTO.getRoom().getId());
//		Contract.setRoom(room);
//		contractRepo.save(Contract);
//		return ContractDTO;
//	}
//
//	@Override
//	public Boolean delete(String id) {
//		LOG.info("Delete Contract by id: " + id.toString());
//		try {
//			contractRepo.deleteById(id);
//			return true;
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			LOG.error("FAIL TO delete Contract by id: " + id.toString());
//			return false;
//		}
//	}
//
//	@Override
//	public ContractDTO update(ContractDTO ContractDTO) {
//		ModelMapper mapper = new ModelMapper();
//		LOG.info("Update Contract by id: " + ContractDTO.toString());
////		Contract Contract = contractRepo.findById(ContractDTO.getId())
////				.orElseThrow(() -> new BadRequestAlertException("Not Found Contract", "Contract", "NotFound"));
//		if(!contractRepo.existsById(ContractDTO.getId()))
//			throw new BadRequestAlertException("Not Found Contract", "Contract", "NotFound");
//		Contract Contract2 = mapper.map(ContractDTO, Contract.class);
//
//		contractRepo.save(Contract2);
//		
//		return ContractDTO;
//
//	}
//
//	@Override
//	public Contract get(String id) {
//		LOG.info("Get Contract by id:" + id.toString());
//		Contract Contract = contractRepo.findById(id)
//				.orElseThrow(() -> new BadRequestAlertException("Not Found Contract", "Contract", "NotFound"));
//		return Contract;
//	}
//
//	@Override
//	public List<ContractDTO> getAll() {
//		LOG.info("Get all Contract");
//		ModelMapper mapper = new ModelMapper();
//		List<Contract> authorities = contractRepo.findAll();
//		if (authorities.size() <= 0) {
//			LOG.error("Not found any Contract");
//			return new ArrayList<ContractDTO>();
//		}
//		return authorities.stream().map(Contract -> mapper.map(Contract, ContractDTO.class)).collect(Collectors.toList());
//	}
//	
//	@Override
//	public ResponseDTO<List<ContractDTO>> search(SearchDTO searchDTO) {
//		try {
//			List<Sort.Order> orders = Optional.ofNullable(searchDTO.getOrders()).orElseGet(Collections::emptyList)
//					.stream().map(order -> {
//						if (order.getOrder().equals(SearchDTO.ASC))
//							return Sort.Order.asc(order.getProperty());
//
//						return Sort.Order.desc(order.getProperty());
//					}).collect(Collectors.toList());
//			Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(orders));
//
//			Page<Contract> page = contractRepo.findAll(pageable);
//			ModelMapper mapper = new ModelMapper();
//			List<ContractDTO> ContractDTOs = page.getContent().stream()
//                    .map(Room -> mapper.map(Room, ContractDTO.class))
//                    .collect(Collectors.toList());
//			ResponseDTO<List<ContractDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
//			responseDTO.setData(ContractDTOs);
//			return responseDTO;
//		} catch (ResourceAccessException e) {
//			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
//		} catch (HttpServerErrorException | HttpClientErrorException e) {
//			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
//		}
//	}
//
//	@Override
//	public Long count() {
//		LOG.info("Count Contract");
//		return contractRepo.count();
//	}
//}
