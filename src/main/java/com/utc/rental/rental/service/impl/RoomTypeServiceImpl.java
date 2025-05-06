package com.utc.rental.rental.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.roomType.RoomTypeDTO;
import com.utc.rental.rental.dto.roomType.SearchRoomTypeDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.RoomType;
import com.utc.rental.rental.service.RoomTypeService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
@Service
public class RoomTypeServiceImpl implements RoomTypeService {
	
	@Autowired
	com.utc.rental.rental.repository.RoomTypeRepo roomTypeRepo;
	private static final Logger LOG = LoggerFactory.getLogger(RoomTypeServiceImpl.class);
	
	@Override
	public RoomTypeDTO create(RoomTypeDTO roomTypeDTO) {
		ModelMapper mapper = new ModelMapper();
		RoomType roomType = mapper.map(roomTypeDTO, RoomType.class);
		return roomTypeDTO;
		
	}

	@Override
	public RoomTypeDTO update(RoomTypeDTO roomTypeDTO) {
		ModelMapper mapper = new ModelMapper();
		if(!roomTypeRepo.existsById(roomTypeDTO.getId())) {
			LOG.error("Not found room type by id"+ roomTypeDTO.toString());
			throw new BadRequestAlertException("Not Found RoomType", "RoomType", "Not Found");
		}
		RoomType r = mapper.map(roomTypeDTO, RoomType.class);
		roomTypeRepo.save(r);
		return roomTypeDTO;
	}

	@Override
	public Boolean delete(Long id) {
		try {
			if(!roomTypeRepo.existsById(id)) return false;
			roomTypeRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public RoomType get(Long id) {
		return roomTypeRepo.findById(id).orElseThrow(()-> new BadRequestAlertException("RoomType not found", "RoomType", "Not Found"));
	}

	@Override
	public List<RoomTypeDTO> getAll() {
		ModelMapper mapper = new ModelMapper();
		return roomTypeRepo.findAll().stream().map(RoomType-> mapper.map(RoomType, RoomTypeDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ResponseDTO<List<RoomTypeDTO>> search(SearchRoomTypeDTO searchRoomTypeDTO) {
		try {
		List<Sort.Order> orders = Optional.ofNullable(searchRoomTypeDTO.getOrders()).orElseGet(Collections::emptyList)
				.stream().map(order -> {
					if (order.getOrder().equals(searchRoomTypeDTO.ASC))
						return Sort.Order.asc(order.getProperty());

					return Sort.Order.desc(order.getProperty());
				}).collect(Collectors.toList());
		Pageable pageable = PageRequest.of(searchRoomTypeDTO.getPage(), searchRoomTypeDTO.getSize(), Sort.by(orders));
		
		Page<RoomType> 
			page= roomTypeRepo.search(searchRoomTypeDTO.getValue(),
					searchRoomTypeDTO.getMinSize(), searchRoomTypeDTO.getMaxSize()
					, pageable);
		ModelMapper mapper = new ModelMapper();
		List<RoomTypeDTO> roomTypeDTOs = page.getContent().stream()
				  .map(RoomType -> mapper.map(RoomType, RoomTypeDTO.class))
				  .collect(Collectors.toList());
		ResponseDTO<List<RoomTypeDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
		responseDTO.setData(roomTypeDTOs);
		return responseDTO;
	} catch (ResourceAccessException e) {
		throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
	} catch (HttpServerErrorException | HttpClientErrorException e) {
		throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
	}
	}

	@Override
	public RoomType findByName(String RoomTypeName) {
		LOG.info("Found RoomType by RoomTypeName:" + RoomTypeName);
		RoomType roomType = roomTypeRepo.findByName(RoomTypeName).orElseThrow(()-> new BadRequestAlertException("Not Found RoomType", RoomTypeName, RoomTypeName));
		return roomType;
	}

	@Override
	public Long count() {
		LOG.info("Count RoomType");
		return roomTypeRepo.count();
	}

}
