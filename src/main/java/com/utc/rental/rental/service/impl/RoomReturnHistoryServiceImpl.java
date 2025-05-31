package com.utc.rental.rental.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.zalando.problem.Problem;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.roomReturn.RoomReturnDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.search.SearchRoomReturn;
import com.utc.rental.rental.entity.Bill;
import com.utc.rental.rental.entity.Contractt;
import com.utc.rental.rental.entity.Room;
import com.utc.rental.rental.entity.RoomReturnHistory;
import com.utc.rental.rental.repository.BillRepo;
import com.utc.rental.rental.repository.ContractRepo;
import com.utc.rental.rental.repository.RoomRepo;
import com.utc.rental.rental.repository.RoomReturnHistoryRepo;
import com.utc.rental.rental.repository.RoomServiceRepo;
import com.utc.rental.rental.service.RoomReturnHistoryService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.zalando.problem.Status;
@Service
public class RoomReturnHistoryServiceImpl implements RoomReturnHistoryService {
	
	@Autowired
	private RoomReturnHistoryRepo roomReturnHistoryRepo;
	
	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private ContractRepo contractRepo;
	
	@Autowired
	private BillRepo billRepo;
	
	private static final Logger LOG = LoggerFactory.getLogger(RoomReturnHistoryServiceImpl.class);
	

	@Override
	public RoomReturnDTO create(RoomReturnDTO roomReturnDTO) {
		
		LOG.info("Create RoomService: "+roomReturnDTO.toString());
		
		roomReturnDTO.setId(null);
		ModelMapper mapper = new ModelMapper();
		
		RoomReturnHistory roomReturnHistory = new RoomReturnHistory();
		roomReturnHistory.setId(UUID.randomUUID().toString().replace("-", ""));
		roomReturnHistory.setUpdateAt(LocalDate.now());
		roomReturnHistory.setReturnDate(roomReturnDTO.getReturnDate());
		roomReturnHistory.setNote(roomReturnDTO.getNote());
		roomReturnHistory.setReason(roomReturnDTO.getReason());
		roomReturnHistory.setStatus(roomReturnDTO.getStatus());
		Contractt contractt = contractRepo.findById(roomReturnDTO.getContractId()).orElseThrow();
		Room room = contractt.getRoom();
		roomReturnHistory.setRoom(room);
		roomReturnHistory.setContract(contractt);
		
		roomReturnHistoryRepo.save(roomReturnHistory);
		
		return roomReturnDTO;
	}

	@Override
	public Boolean delete(String id) {
		LOG.info("Delete RoomService by id: "+id.toString());
		try {
			roomReturnHistoryRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOG.error("FAIL TO delete RoomService by id: "+id.toString());
			return false;
		}
	}

	@Override
	public RoomReturnDTO update(RoomReturnDTO roomReturnDTO) {
		ModelMapper mapper = new ModelMapper();
		LOG.info("Update RoomService by id: "+roomReturnDTO.toString());
//		RoomReturnHistory service = roomServiceRepo.findById(RoomReturnDTO.getId())
//				.orElseThrow(()-> new BadRequestAlertException("Not Found RoomService", "RoomService", "NotFound"));
//		
		if(!roomReturnHistoryRepo.existsById(roomReturnDTO.getId())) throw  new BadRequestAlertException("Not Found RoomService", "RoomService", "NotFound");
//		
//
		RoomReturnHistory roomReturnHistory = new RoomReturnHistory();
		roomReturnHistory.setId(roomReturnDTO.getId());
		roomReturnHistory.setUpdateAt(LocalDate.now());
		roomReturnHistory.setReturnDate(roomReturnDTO.getReturnDate());
		roomReturnHistory.setNote(roomReturnDTO.getNote());
		roomReturnHistory.setReason(roomReturnDTO.getReason());
		roomReturnHistory.setStatus(roomReturnDTO.getStatus());
//		
//		
		Contractt contractt = contractRepo.findById(roomReturnDTO.getContractId()).orElseThrow();
//		
		Room room = contractt.getRoom();
		roomReturnHistory.setRoom(room);
		roomReturnHistory.setContract(contractt);
//		
		roomReturnHistoryRepo.save(roomReturnHistory);
//		
		return roomReturnDTO;
		
	}

	@Override
	public RoomReturnDTO get(String id) {
		LOG.info("Get RoomService by id:"+id.toString());
		RoomReturnHistory RoomService = roomReturnHistoryRepo.findById(id)
				.orElseThrow(()-> new BadRequestAlertException("Not Found RoomService", "RoomService", "NotFound"));
		return new ModelMapper().map(RoomService, RoomReturnDTO.class);
	}

	@Override
	public List<RoomReturnDTO> getAll() {
		LOG.info("Get all RoomService");
		ModelMapper mapper = new ModelMapper();
		List<RoomReturnHistory> authorities = roomReturnHistoryRepo.findAll();
		if(authorities.size()<=0) {
			LOG.error("Not found any RoomService");
			return new ArrayList<RoomReturnDTO>();
		}
		return authorities.stream().map(RoomService -> mapper.map(RoomService, RoomReturnDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ResponseDTO<List<RoomReturnDTO>> search(SearchRoomReturn searchRoomReturn) {
		SearchDTO searchDTO = searchRoomReturn.getSearchDTO();
		try {
			List<Sort.Order> orders = Optional.ofNullable(searchDTO.getOrders()).orElseGet(Collections::emptyList)
					.stream().map(order -> {
						if (order.getOrder().equals(SearchDTO.ASC))
							return Sort.Order.asc(order.getProperty());

						return Sort.Order.desc(order.getProperty());
					}).collect(Collectors.toList());
			Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(orders));
			Page<RoomReturnHistory> page;
			if (searchRoomReturn.getRoomId() != null && searchRoomReturn.getContractId() != null && searchRoomReturn.getStatus() != null) {
				   page= roomReturnHistoryRepo.findByRoomIdAndcontractIdAndStatus(searchRoomReturn.getRoomId(), searchRoomReturn.getContractId(), searchRoomReturn.getStatus(), pageable);
				} else if (searchRoomReturn.getRoomId() != null && searchRoomReturn.getStatus() != null) {
				   page= roomReturnHistoryRepo.findByRoomIdAndStatus(searchRoomReturn.getRoomId(), searchRoomReturn.getStatus(), pageable);
				} else if (searchRoomReturn.getContractId() != null && searchRoomReturn.getStatus() != null) {
				   page= roomReturnHistoryRepo.findBycontractIdAndStatus(searchRoomReturn.getContractId(), searchRoomReturn.getStatus(), pageable);
				} else if (searchRoomReturn.getRoomId() != null) {
				   page= roomReturnHistoryRepo.findByRoomId(searchRoomReturn.getRoomId(), pageable);
				} else if (searchRoomReturn.getContractId() != null) {
				   page= roomReturnHistoryRepo.findBycontractId(searchRoomReturn.getContractId(), pageable);
				} else if (searchRoomReturn.getStatus() != null) {
				   page= roomReturnHistoryRepo.findByStatus(searchRoomReturn.getStatus(), pageable);
				} else {
				   page= roomReturnHistoryRepo.findAll(pageable); // không có điều kiện gì
				}
			ModelMapper mapper = new ModelMapper();
			List<RoomReturnDTO> levelDTOs = page.getContent().stream()
					  .map(RoomService -> mapper.map(RoomService, RoomReturnDTO.class))
					  .collect(Collectors.toList());
			
			
			
			ResponseDTO<List<RoomReturnDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
			responseDTO.setData(levelDTOs);
			return responseDTO;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}


	@Override
	public Long count() {
		LOG.info("Count RoomService");
		return roomReturnHistoryRepo.count();
	}

}
