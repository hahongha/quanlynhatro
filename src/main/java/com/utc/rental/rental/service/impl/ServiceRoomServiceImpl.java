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
import com.utc.rental.rental.dto.room_service.RoomServiceDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.search.SearchRoomService;
import com.utc.rental.rental.entity.Renter;
import com.utc.rental.rental.entity.Room;
import com.utc.rental.rental.entity.RoomType;
import com.utc.rental.rental.entity.Room_Service;
import com.utc.rental.rental.entity.Room_Service_Room;
import com.utc.rental.rental.repository.RenterRepo;
import com.utc.rental.rental.repository.RoomRepo;
import com.utc.rental.rental.repository.RoomServiceRepo;
import com.utc.rental.rental.repository.ServiceRoomRepo;
import com.utc.rental.rental.service.RoomService;
import com.utc.rental.rental.service.RoomTypeService;
import com.utc.rental.rental.service.ServiceRoomService;

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
public class ServiceRoomServiceImpl implements ServiceRoomService {

	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private RoomServiceRepo roomServiceRepo;
	
	@Autowired
	private ServiceRoomRepo serviceRoomRepo;
   
    
    private static final Logger LOG = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Override
    public RoomServiceDTO create(RoomServiceDTO roomServiceDTO) {
        ModelMapper mapper = new ModelMapper();
        Room_Service_Room roomService = mapper.map(roomServiceDTO, Room_Service_Room.class);
        roomService.setId(null);
        Room room = roomRepo.findById(roomServiceDTO.getRoom().getId()).orElseThrow();
        roomService.setRoom(room);
        Room_Service service = roomServiceRepo.findById(roomServiceDTO.getService().getId()).orElseThrow();
        roomService.setService(service);
        serviceRoomRepo.save(roomService);
        return roomServiceDTO;

    }

    @Override
    public RoomServiceDTO update(RoomServiceDTO roomServiceDTO) {
    	System.err.println("update");
        ModelMapper mapper = new ModelMapper();
        if (!roomRepo.existsById(roomServiceDTO.getId())) {
            LOG.error("Not found room type by id" + roomServiceDTO.toString());
            throw new BadRequestAlertException("Not Found Room", "Room", "Not Found");
        }
        Room_Service_Room roomService = mapper.map(roomServiceDTO, Room_Service_Room.class);
        roomService.setId(null);
        Room room = roomRepo.findById(roomServiceDTO.getRoom().getId()).orElseThrow();
        roomService.setRoom(room);
        Room_Service service = roomServiceRepo.findById(roomServiceDTO.getService().getId()).orElseThrow();
        roomService.setService(service);
        serviceRoomRepo.save(roomService);
        return roomServiceDTO;
    }

    @Override
    public Boolean delete(Long id) {
        try {
            if (!roomRepo.existsById(id))
                return false;
            roomRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public RoomServiceDTO get(Long id) {
        Room_Service_Room room= serviceRoomRepo.findById(id)
                .orElseThrow(() -> new BadRequestAlertException("Room not found", "Room", "Not Found"));
        return new ModelMapper().map(room, RoomServiceDTO.class);
    }

    @Override
    public List<RoomServiceDTO> getAll() {
        ModelMapper mapper = new ModelMapper();
        return serviceRoomRepo.findAll().stream().map(Room -> mapper.map(Room, RoomServiceDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseDTO<List<RoomServiceDTO>> search(SearchRoomService searchRoomServiceDTO) {
    	SearchDTO searchDTO= searchRoomServiceDTO.getSearchDTO();
        try {
            List<Sort.Order> orders = Optional.ofNullable(searchDTO.getOrders()).orElseGet(Collections::emptyList)
                    .stream().map(order -> {
                        if (order.getOrder().equals(SearchDTO.ASC))
                            return Sort.Order.asc(order.getProperty());

                        return Sort.Order.desc(order.getProperty());
                    }).collect(Collectors.toList());
            Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(orders));
            Page<Room_Service_Room> page;
            Long roomId = searchRoomServiceDTO.getRoomId();
            Long serviceId = searchRoomServiceDTO.getServiceId();
            String status = searchRoomServiceDTO.getStatus();

            if (roomId == null && serviceId == null && status == null) {
                page= serviceRoomRepo.findAll(pageable);
            }
            else
            if (roomId != null && serviceId != null && status != null) {
            	page= serviceRoomRepo.findByRoomIdAndServiceIdAndStatus(roomId, serviceId, status, pageable);
            }
            else
            if (roomId != null && serviceId == null && status != null) {
            	page= serviceRoomRepo.findByRoomIdAndStatus(roomId, status, pageable);
            }
            else
            if (roomId == null && serviceId != null && status != null) {
            	page= serviceRoomRepo.findByServiceIdAndStatus(serviceId, status, pageable);
            }
            else
            if (roomId != null && serviceId == null && status == null) {
            	page= serviceRoomRepo.findByRoomId(roomId, pageable);
            }
            else
            if (roomId == null && serviceId != null && status == null) {
            	page= serviceRoomRepo.findByServiceId(serviceId, pageable);
            }
            else
            if (roomId == null && serviceId == null && status != null) {
            	page= serviceRoomRepo.findByStatus(status, pageable);
            }
            else page = serviceRoomRepo.findAll(pageable);
            ModelMapper mapper = new ModelMapper();
            List<RoomServiceDTO> RoomServiceDTOs = page.getContent().stream()
                    .map(Room -> mapper.map(Room, RoomServiceDTO.class))
                    .collect(Collectors.toList());
            ResponseDTO<List<RoomServiceDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
            responseDTO.setData(RoomServiceDTOs);
            return responseDTO;
        } catch (ResourceAccessException e) {
            throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
        }
    }

    @Override
    public Long count() {
        LOG.info("Count Room");
        return roomRepo.count();
    }

	@Override
	public RoomServiceDTO getDTO(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


}
