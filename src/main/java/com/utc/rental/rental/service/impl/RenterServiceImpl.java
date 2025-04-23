package com.utc.rental.rental.service.impl;

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
import com.utc.rental.rental.dto.RenterDTO;
import com.utc.rental.rental.dto.SearchDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.user.UserDTO;
import com.utc.rental.rental.entity.Renter;
import com.utc.rental.rental.entity.User;
import com.utc.rental.rental.repository.RenterRepo;
import com.utc.rental.rental.service.RenterService;
import com.utc.rental.rental.service.UserService;

import ch.qos.logback.core.model.Model;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@Service
public class RenterServiceImpl implements RenterService {

	@Autowired
	RenterRepo renterRepo;
	
	@Autowired
	UserService userService;
	@Transactional
	@Override
	public RenterDTO create(RenterDTO renterDTO) {
		if(renterDTO.getId()==null) renterDTO.setId(UUID.randomUUID().toString().replace("_", ""));
		ModelMapper mapper = new ModelMapper();
		try {
			UserDTO userDTO = userService.create(renterDTO.getUser());
		}catch (Exception e) {
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
		if(!renterRepo.existsById(renterDTO.getId())) throw new BadRequestAlertException("Không tìm thấy khách thuê", "Renter","NotFound");
		ModelMapper mapper = new ModelMapper();
		Renter renter = mapper.map(renterDTO, Renter.class);
		UserDTO user = renterDTO.getUser();
		userService.update(user);
		renterRepo.save(renter);
		return renterDTO;
	}

	@Override
	public Renter get(String id) {
		return renterRepo.findById(id).orElseThrow(()-> new BadRequestAlertException("Không tìm thấy khách thuê", "Renter","NotFound"));
	}

	@Override
	public List<RenterDTO> getAll() {
		return renterRepo.findAll().stream().map(r-> new ModelMapper().map(r, RenterDTO.class)).collect(Collectors.toList());
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
	public ResponseDTO<List<RenterDTO>> search(SearchDTO searchDTO) {
		try {
			List<Sort.Order> orders = Optional.ofNullable(searchDTO.getOrders()).orElseGet(Collections::emptyList)
					.stream().map(order -> {
						if (order.getOrder().equals(SearchDTO.ASC))
							return Sort.Order.asc(order.getProperty());

						return Sort.Order.desc(order.getProperty());
					}).collect(Collectors.toList());
			Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(orders));

			Page<Renter> page = renterRepo.search(searchDTO.getValue(), pageable);
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
		return renterRepo.findByIdentificationOrId(id, identification).orElseThrow(()-> new BadRequestAlertException("Không tìm thấy khách thuê2", "renter", "Not Found"));
	}

	@Override
	public Renter getIdentification(String id) {
		return renterRepo.findByIdentification(id).orElseThrow(()-> new BadRequestAlertException("Không tìm thấy khách thuê", "renter", "Not Found"));
	}

	@Override
	public RenterDTO findByUserId(String id) {
		Renter renter = renterRepo.findByUserId(id).orElseThrow(()-> new BadRequestAlertException("Không tìm thấy khách thuê", "renter", "Not Found"));
		return new ModelMapper().map(renter, RenterDTO.class);
	}
}
