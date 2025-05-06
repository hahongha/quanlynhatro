package com.utc.rental.rental.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.service.ServiceDTO;
import com.utc.rental.rental.entity.Room_Service;
import com.utc.rental.rental.repository.RoomServiceRepo;
import com.utc.rental.rental.service.Room_ServiceService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.zalando.problem.Status;
@Service
public class Room_ServiceServiceImpl implements Room_ServiceService {
	
	@Autowired
	RoomServiceRepo roomServiceRepo;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(Room_ServiceServiceImpl.class);
	

	@Override
	public ServiceDTO create(ServiceDTO serviceDTO) {
		
		LOG.info("Create RoomService: "+serviceDTO.toString());
		
		serviceDTO.setId(null);
		ModelMapper mapper = new ModelMapper();
		
		Room_Service RoomService = mapper.map(serviceDTO, Room_Service.class);
		roomServiceRepo.save(RoomService);
		return serviceDTO;
	}

	@Override
	public Boolean delete(Long id) {
		LOG.info("Delete RoomService by id: "+id.toString());
		try {
			roomServiceRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOG.error("FAIL TO delete RoomService by id: "+id.toString());
			return false;
		}
	}

	@Override
	public ServiceDTO update(ServiceDTO serviceDTO) {
		ModelMapper mapper = new ModelMapper();
		LOG.info("Update RoomService by id: "+serviceDTO.toString());
//		Room_Service service = roomServiceRepo.findById(ServiceDTO.getId())
//				.orElseThrow(()-> new BadRequestAlertException("Not Found RoomService", "RoomService", "NotFound"));
//		
		if(!roomServiceRepo.existsById(serviceDTO.getId())) throw  new BadRequestAlertException("Not Found RoomService", "RoomService", "NotFound");
		

		Room_Service service2 = mapper.map(serviceDTO, Room_Service.class);
		
		roomServiceRepo.save(service2);
		
		return serviceDTO;
		
	}

	@Override
	public Room_Service get(Long id) {
		LOG.info("Get RoomService by id:"+id.toString());
		Room_Service RoomService = roomServiceRepo.findById(id)
				.orElseThrow(()-> new BadRequestAlertException("Not Found RoomService", "RoomService", "NotFound"));
		return RoomService;
	}
	
	@Override
	public ServiceDTO getDTO(Long id) {
		LOG.info("Get RoomService by id:"+id.toString());
		Room_Service service = get(id);
		return new ModelMapper().map(service, ServiceDTO.class);
	}

	@Override
	public List<ServiceDTO> getAll() {
		LOG.info("Get all RoomService");
		ModelMapper mapper = new ModelMapper();
		List<Room_Service> authorities = roomServiceRepo.findAll();
		if(authorities.size()<=0) {
			LOG.error("Not found any RoomService");
			return new ArrayList<ServiceDTO>();
		}
		return authorities.stream().map(RoomService -> mapper.map(RoomService, ServiceDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ResponseDTO<List<ServiceDTO>> search(SearchDTO searchDTO) {
		try {
			List<Sort.Order> orders = Optional.ofNullable(searchDTO.getOrders()).orElseGet(Collections::emptyList)
					.stream().map(order -> {
						if (order.getOrder().equals(SearchDTO.ASC))
							return Sort.Order.asc(order.getProperty());

						return Sort.Order.desc(order.getProperty());
					}).collect(Collectors.toList());
			Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(orders));

			Page<Room_Service> page = roomServiceRepo.findAll(pageable);
			ModelMapper mapper = new ModelMapper();
			List<ServiceDTO> levelDTOs = page.getContent().stream()
					  .map(RoomService -> mapper.map(RoomService, ServiceDTO.class))
					  .collect(Collectors.toList());
			
			
			
			ResponseDTO<List<ServiceDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
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
		return roomServiceRepo.count();
	}

}
