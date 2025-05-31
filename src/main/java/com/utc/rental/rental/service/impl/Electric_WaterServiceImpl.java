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
import com.utc.rental.rental.dto.index.Electric_WaterDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.search.SearchEW;
import com.utc.rental.rental.entity.Electric_Water;
import com.utc.rental.rental.entity.Room;
import com.utc.rental.rental.repository.ElectricWaterRepo;
import com.utc.rental.rental.repository.RoomRepo;
import com.utc.rental.rental.service.Electric_WaterService;

@Service
public class Electric_WaterServiceImpl implements Electric_WaterService {

	@Autowired
	ElectricWaterRepo Electric_WaterRepo;

	@Autowired
	private RoomRepo roomRepo;

	private static final Logger LOG = LoggerFactory.getLogger(Electric_WaterServiceImpl.class);

	@Override
	public Electric_WaterDTO create(Electric_WaterDTO Electric_WaterDTO) {
		LOG.info("Create Electric_Water: " + Electric_WaterDTO.toString());

		ModelMapper mapper = new ModelMapper();

		Electric_Water Electric_Water = mapper.map(Electric_WaterDTO, Electric_Water.class);
		Long count = Electric_WaterRepo.count() + 100000;
		Electric_Water.setId("EW" + count.toString());
		Room room = roomRepo.findById(Electric_WaterDTO.getRoom().getId()).orElseThrow();
		Electric_Water.setRoom(room);
		Electric_WaterRepo.save(Electric_Water);
		return Electric_WaterDTO;
	}

	@Override
	public Boolean delete(String id) {
		LOG.info("Delete Electric_Water by id: " + id.toString());
		try {
			Electric_WaterRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOG.error("FAIL TO delete Electric_Water by id: " + id.toString());
			return false;
		}
	}

	@Override
	public Electric_WaterDTO update(Electric_WaterDTO Electric_WaterDTO) {
		ModelMapper mapper = new ModelMapper();
		LOG.info("Update Electric_Water by id: " + Electric_WaterDTO.toString());
		Electric_Water Electric_Water = Electric_WaterRepo.findById(Electric_WaterDTO.getId()).orElseThrow(
				() -> new BadRequestAlertException("Not Found Electric_Water", "Electric_Water", "NotFound"));
		if (!Electric_WaterRepo.existsById(Electric_WaterDTO.getId()))
			throw new BadRequestAlertException("Not Found Electric_Water", "Electric_Water", "NotFound");
		Room room = roomRepo.findById(Electric_WaterDTO.getRoom().getId()).orElseThrow();
		Electric_Water.setRoom(room);
		Electric_Water Electric_Water2 = mapper.map(Electric_WaterDTO, Electric_Water.class);

		Electric_WaterRepo.save(Electric_Water2);

		return Electric_WaterDTO;

	}

	@Override
	public Electric_WaterDTO get(String id) {
		LOG.info("Get Electric_Water by id:" + id.toString());
		Electric_Water Electric_Water = Electric_WaterRepo.findById(id).orElseThrow(
				() -> new BadRequestAlertException("Not Found Electric_Water", "Electric_Water", "NotFound"));
		return new ModelMapper().map(Electric_Water, Electric_WaterDTO.class);
	}

	@Override
	public List<Electric_WaterDTO> getAll() {
		LOG.info("Get all Electric_Water");
		List<Electric_WaterDTO> contractDTOs = new ArrayList<Electric_WaterDTO>();
		List<Electric_Water> authorities = Electric_WaterRepo.findAll();
		if (authorities.size() <= 0) {
			LOG.error("Not found any Electric_Water");
			return contractDTOs;
		}
		ModelMapper mapper = new ModelMapper();
		ModelMapper modelMapper = new ModelMapper();
		for (Electric_Water Electric_Water : authorities) {
			Electric_WaterDTO Electric_WaterDTO = mapper.map(Electric_Water, Electric_WaterDTO.class);

			contractDTOs.add(Electric_WaterDTO);
		}
		return contractDTOs;
	}

	@Override
	public ResponseDTO<List<Electric_WaterDTO>> search(SearchEW searchEW) {
		SearchDTO searchDTO = searchEW.getSearchDTO();
		String type = searchEW.getType();
		Long roomId = searchEW.getRoomId();
		LocalDate startDate = searchEW.getStartDate();
		LocalDate endDate = searchEW.getEndDate();
		if(startDate==null) startDate = LocalDate.of(2000, 1, 1);
		if(endDate==null) endDate= LocalDate.now();
		try {
			List<Sort.Order> orders = Optional.ofNullable(searchDTO.getOrders()).orElseGet(Collections::emptyList)
					.stream().map(order -> {
						if (order.getOrder().equals(SearchDTO.ASC))
							return Sort.Order.asc(order.getProperty());

						return Sort.Order.desc(order.getProperty());
					}).collect(Collectors.toList());
			Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(orders));
			Page<Electric_Water> page = Electric_WaterRepo.search(type, roomId, startDate.getYear()*100+ endDate.getMonthValue(), endDate.getYear()*100 + endDate.getMonthValue(), pageable);
//            try {
//				Long value = Long.valueOf(roomId);
//				page = Electric_WaterRepo.findByRoom(value, pageable);
//			} catch (Exception e) {
//				page = Electric_WaterRepo.findAll(pageable);
//			}
			ModelMapper mapper = new ModelMapper();
			ModelMapper modelMapper = new ModelMapper();
			List<Electric_WaterDTO> contractDTOs = page.getContent().stream()
					.map(a -> modelMapper.map(a, Electric_WaterDTO.class)).collect(Collectors.toList());
			ResponseDTO<List<Electric_WaterDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
			responseDTO.setData(contractDTOs);
			return responseDTO;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}
}
