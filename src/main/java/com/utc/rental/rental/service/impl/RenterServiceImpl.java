package com.utc.rental.rental.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.config.DefaultValue.StatusActRef;
import com.utc.rental.rental.dto.bill.BillDTO;
import com.utc.rental.rental.dto.contract.ContractDTO;
import com.utc.rental.rental.dto.index.Electric_WaterDTO;
import com.utc.rental.rental.dto.renter.RenterDTO;
import com.utc.rental.rental.dto.renter.UserRentalDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.room.RoomDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.search.SearchRenter;
import com.utc.rental.rental.dto.service.ServiceDTO;
import com.utc.rental.rental.dto.user.UserDTO;
import com.utc.rental.rental.entity.Bill;
import com.utc.rental.rental.entity.Contractt;
import com.utc.rental.rental.entity.Electric_Water;
import com.utc.rental.rental.entity.Renter;
import com.utc.rental.rental.entity.Room;
import com.utc.rental.rental.entity.User;
import com.utc.rental.rental.repository.BillRepo;
import com.utc.rental.rental.repository.ContractRepo;
import com.utc.rental.rental.repository.ElectricWaterRepo;
import com.utc.rental.rental.repository.RenterRepo;
import com.utc.rental.rental.service.RenterService;
import com.utc.rental.rental.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class RenterServiceImpl implements RenterService {

	@Autowired
	private RenterRepo renterRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private ContractRepo contractRepo;

	@Autowired
	private ElectricWaterRepo electricWaterRepo;
	
	@Autowired
	private BillRepo billRepo;

	@Transactional
	@Override
	public RenterDTO create(RenterDTO renterDTO) {
		if (renterDTO.getId() == null)
			renterDTO.setId(UUID.randomUUID().toString().replace("_", ""));
		ModelMapper mapper = new ModelMapper();
		try {
			UserDTO userDTO = userService.create(renterDTO.getUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Renter renter = mapper.map(renterDTO, Renter.class);
		User user = userService.findByUserName(renterDTO.getUser().getUserName());
		renter.setUser(user);
		renterDTO.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		renterRepo.save(renter);
		return renterDTO;
	}

	@Override
	public RenterDTO update(RenterDTO renterDTO) {
		if (!renterRepo.existsById(renterDTO.getId()))
			throw new BadRequestAlertException("Không tìm thấy khách thuê", "Renter", "NotFound");
		ModelMapper mapper = new ModelMapper();
		Renter renter = mapper.map(renterDTO, Renter.class);
		UserDTO user = renterDTO.getUser();
		userService.update(user);
		renterRepo.save(renter);
		return renterDTO;
	}

	@Override
	public Renter get(String id) {
		return renterRepo.findById(id)
				.orElseThrow(() -> new BadRequestAlertException("Không tìm thấy khách thuê", "Renter", "NotFound"));
	}

	@Override
	public List<RenterDTO> getAll() {
		return renterRepo.findAll().stream().map(r -> new ModelMapper().map(r, RenterDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Boolean delete(String id) {
		if (renterRepo.existsById(id)) {
			renterRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public ResponseDTO<List<RenterDTO>> search(SearchRenter searchRenter) {
		SearchDTO searchDTO = searchRenter.getSearchDTO();
	    String status = "%%";
	    String roomNumber ="%%";
		try {
			List<Sort.Order> orders = Optional.ofNullable(searchDTO.getOrders()).orElseGet(Collections::emptyList)
					.stream().map(order -> {
						if (order.getOrder().equals(SearchDTO.ASC))
							return Sort.Order.asc(order.getProperty());

						return Sort.Order.desc(order.getProperty());
					}).collect(Collectors.toList());
			Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(orders));
			if(searchRenter.getStatus()!= null) status = searchRenter.getStatus();
			if(searchRenter.getRoomNumber()!= null) status = searchRenter.getRoomNumber();
			Page<Renter> page = renterRepo.search(searchDTO.getValue(),status,pageable);
			ModelMapper mapper = new ModelMapper();
			List<RenterDTO> renterDTOs = new ArrayList<RenterDTO>();
			for (Renter Renter : page.getContent()) {
				RenterDTO r = mapper.map(Renter, RenterDTO.class);
				renterDTOs.add(r);
			}
			ResponseDTO<List<RenterDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
			responseDTO.setData(renterDTOs);
			return responseDTO;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	public Long count() {
		return renterRepo.count();
	}

	@Override
	public Renter findByID(String id, String identification) {
		Optional<Renter> renter = renterRepo.findById(id);
		if (renter.isEmpty()) {
			renter = renterRepo.findByIdentification(identification);
			if (renter.isEmpty()) {
				return null;
			}
		}
		return renter.orElseThrow(() -> new BadRequestAlertException("Không tìm thấy khách thuê", "renter", "Not Found"));
	}

	@Override
	public Renter getIdentification(String id) {
		return renterRepo.findByIdentification(id)
				.orElseThrow(() -> new BadRequestAlertException("Không tìm thấy khách thuê", "renter", "Not Found"));
	}

	@Override
	public RenterDTO findByUserId(String id) {
		Renter renter = renterRepo.findByUserId(id)
				.orElseThrow(() -> new BadRequestAlertException("Không tìm thấy khách thuê", "renter", "Not Found"));
		return new ModelMapper().map(renter, RenterDTO.class);
	}

	@Override
	public UserRentalDTO getUserRental(String userId) {
		Renter renter = renterRepo.findByUserId(userId)
				.orElseThrow(() -> new BadRequestAlertException("Không tìm thấy khách thuê", "renter", "Not Found"));
		UserRentalDTO userRentalDTO = new ModelMapper().map(renter, UserRentalDTO.class);
		return userRentalDTO;
	}

	@Override
	public List<ContractDTO> getContractFromUser(String id) {
		Renter renter = renterRepo.findByUserId(id)
				.orElseThrow(() -> new BadRequestAlertException("Không tìm thấy khách thuê", "renter", "Not Found"));
		List<Contractt> contracts = contractRepo.findByRenterId(renter.getId());
		return contracts.stream().map(c -> new ModelMapper().map(c, ContractDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<ContractDTO> getContractHistory(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomDTO> getRoomFromUser(String id) {
		Renter renter = renterRepo.findByUserId(id)
				.orElseThrow(() -> new BadRequestAlertException("Không tìm thấy khách thuê", "renter", "Not Found"));
		List<Contractt> contracts = new ArrayList<Contractt>();
		List<RoomDTO> rooms = new ArrayList<RoomDTO>();
		contracts.addAll(contractRepo.findByRenterStatus(renter.getId(), StatusActRef.ACTIVE.toString()));
		if (contracts.size() <= 0)
			return new ArrayList<RoomDTO>();
		for (Contractt contractt : contracts) {
			Room room = contractt.getRoom();
			RoomDTO roomDTO = new ModelMapper().map(room, RoomDTO.class);
			roomDTO.setContractId(contractt.getId());
			rooms.add(roomDTO);
		}
		return rooms;
	}

	@Override
	public List<ServiceDTO> getServiceFromUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServiceDTO> getServiceActiveFromUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BillDTO> getBillActiveFromUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BillDTO> getBillCancelFromUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Electric_WaterDTO> getElectric_WaterFromUser(String id, String type) {
		Renter renter = renterRepo.findByUserId(id)
				.orElseThrow(() -> new BadRequestAlertException("Không tìm thấy khách thuê", "renter", "Not Found"));
		List<Contractt> contracts = new ArrayList<Contractt>();
		List<Electric_Water> electric_Waters = new ArrayList<Electric_Water>();
		contracts.addAll(contractRepo.findByRenterStatus(renter.getId(), StatusActRef.ACTIVE.toString()));
		for (Contractt contractt : contracts) {
			Room room = contractt.getRoom();
			LocalDate endDate = contractt.getEndDate();
			if(endDate== null || endDate.isAfter(LocalDate.now()))
			electric_Waters.addAll(electricWaterRepo.findByRoomAndTypeAndDateRange(room.getId(), type, contractt.getStartDate(), endDate));
		}
		return electric_Waters.stream().map(c -> new ModelMapper().map(c, Electric_WaterDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<BillDTO> getBillFromUser(String id) {
		Renter renter = renterRepo.findByUserId(id)
				.orElseThrow(() -> new BadRequestAlertException("Không tìm thấy khách thuê", "renter", "Not Found"));
		List<Bill> bills = billRepo.findByRenterId(renter.getId());
		if (bills.size()<= 0) {
			return new ArrayList<BillDTO>();
		}
		return bills.stream().map(c -> new ModelMapper().map(c, BillDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Renter addContract(RenterDTO renterDTO) {
		Optional<Renter> reOptional;
		if(renterDTO.getId()!=null && !renterDTO.getId().isBlank()) {
			reOptional = renterRepo.findById(renterDTO.getId());
			if(reOptional.isPresent()) return reOptional.orElseThrow();
		}
		if(renterDTO.getIdentification()!=null && !renterDTO.getIdentification().isBlank()) {
			System.err.println(renterDTO.getIdentification());
			reOptional = renterRepo.findByIdentification(renterDTO.getIdentification());
			if(reOptional.isPresent()) return reOptional.orElseThrow();
		}
		throw new BadRequestAlertException("Không tìm thấy khách thuê", "renter", "Not Found");
	}

}
