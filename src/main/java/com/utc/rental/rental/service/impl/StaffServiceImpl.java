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
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.room.RoomDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.search.SearchStaff;
import com.utc.rental.rental.dto.service.ServiceDTO;
import com.utc.rental.rental.dto.staff.StaffDTO;
import com.utc.rental.rental.dto.user.UserDTO;
import com.utc.rental.rental.entity.Bill;
import com.utc.rental.rental.entity.Contractt;
import com.utc.rental.rental.entity.Electric_Water;
import com.utc.rental.rental.entity.Staff;
import com.utc.rental.rental.entity.Room;
import com.utc.rental.rental.entity.User;
import com.utc.rental.rental.repository.BillRepo;
import com.utc.rental.rental.repository.ContractRepo;
import com.utc.rental.rental.repository.ElectricWaterRepo;
import com.utc.rental.rental.repository.StaffRepo;
import com.utc.rental.rental.service.StaffService;
import com.utc.rental.rental.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepo StaffRepo;

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
	public StaffDTO create(StaffDTO StaffDTO) {
		if (StaffDTO.getId() == null)
			StaffDTO.setId(UUID.randomUUID().toString().replace("_", ""));
		ModelMapper mapper = new ModelMapper();
		try {
			UserDTO userDTO = userService.create(StaffDTO.getUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Staff Staff = mapper.map(StaffDTO, Staff.class);
		User user = userService.findByUserName(StaffDTO.getUser().getUserName());
		Staff.setUser(user);
		StaffDTO.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		StaffRepo.save(Staff);
		return StaffDTO;
	}

	@Override
	public StaffDTO update(StaffDTO StaffDTO) {
		if (!StaffRepo.existsById(StaffDTO.getId()))
			throw new BadRequestAlertException("Không tìm thấy khách thuê", "Staff", "NotFound");
		ModelMapper mapper = new ModelMapper();
		Staff Staff = mapper.map(StaffDTO, Staff.class);
		UserDTO user = StaffDTO.getUser();
		userService.update(user);
		StaffRepo.save(Staff);
		return StaffDTO;
	}

	@Override
	public StaffDTO get(String id) {
		Staff staff = StaffRepo.findById(id)
				.orElseThrow(() -> new BadRequestAlertException("Không tìm thấy khách thuê", "Staff", "NotFound"));
		return new ModelMapper().map(staff, StaffDTO.class);
	}

	@Override
	public List<StaffDTO> getAll() {
		return StaffRepo.findAll().stream().map(r -> new ModelMapper().map(r, StaffDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Boolean delete(String id) {
		if (StaffRepo.existsById(id)) {
			StaffRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public ResponseDTO<List<StaffDTO>> search(SearchStaff searchStaff) {
		SearchDTO searchDTO = searchStaff.getSearchDTO();
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
			Page<Staff> page = StaffRepo.findAll(pageable);
			ModelMapper mapper = new ModelMapper();
			List<StaffDTO> StaffDTOs = new ArrayList<StaffDTO>();
			for (Staff Staff : page.getContent()) {
				StaffDTO r = mapper.map(Staff, StaffDTO.class);
				StaffDTOs.add(r);
			}
			ResponseDTO<List<StaffDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
			responseDTO.setData(StaffDTOs);
			return responseDTO;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	public Long count() {
		return StaffRepo.count();
	}
	
	@Override
	public StaffDTO getProfile(String userId) {
		Staff staff = StaffRepo.findByUserId(userId).orElseThrow(()-> new BadRequestAlertException("Not Found User", "STAFF", "NOTFOUND"));
		return new ModelMapper().map(staff, StaffDTO.class);
	}

}
