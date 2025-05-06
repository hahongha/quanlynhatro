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
import com.utc.rental.rental.dto.room.RoomDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.Renter;
import com.utc.rental.rental.entity.Room;
import com.utc.rental.rental.entity.RoomType;
import com.utc.rental.rental.repository.RenterRepo;
import com.utc.rental.rental.repository.RoomRepo;
import com.utc.rental.rental.service.RoomService;
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
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepo roomRepo;
    
    @Autowired
    RoomTypeService roomTypeService;
    
    @Autowired
    RenterRepo renterRepo;
    
    private static final Logger LOG = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Override
    public RoomDTO create(RoomDTO roomDTO) {
        ModelMapper mapper = new ModelMapper();
        Room room = mapper.map(roomDTO, Room.class);
        RoomType roomType = roomTypeService.get(roomDTO.getRoom_Type().getId());
        room.setRoom_Type(roomType);
        roomRepo.save(room);
        return roomDTO;

    }

    @Override
    public RoomDTO update(RoomDTO roomDTO) {
    	System.err.println("update");
        ModelMapper mapper = new ModelMapper();
        if (!roomRepo.existsById(roomDTO.getId())) {
            LOG.error("Not found room type by id" + roomDTO.toString());
            throw new BadRequestAlertException("Not Found Room", "Room", "Not Found");
        }
        System.err.println(roomDTO.toString());
        Room r = mapper.map(roomDTO, Room.class);
        RoomType roomType = roomTypeService.get(roomDTO.getRoom_Type().getId());
        r.setRoom_Type(roomType);
        System.err.println(r.toString());
        roomRepo.save(r);
        return roomDTO;
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
    public Room get(Long id) {
        return roomRepo.findById(id)
                .orElseThrow(() -> new BadRequestAlertException("Room not found", "Room", "Not Found"));
    }

    @Override
    public List<RoomDTO> getAll() {
        ModelMapper mapper = new ModelMapper();
        return roomRepo.findAll().stream().map(Room -> mapper.map(Room, RoomDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseDTO<List<RoomDTO>> search(SearchDTO searchRoomDTO) {
        try {
            List<Sort.Order> orders = Optional.ofNullable(searchRoomDTO.getOrders()).orElseGet(Collections::emptyList)
                    .stream().map(order -> {
                        if (order.getOrder().equals(SearchDTO.ASC))
                            return Sort.Order.asc(order.getProperty());

                        return Sort.Order.desc(order.getProperty());
                    }).collect(Collectors.toList());
            Pageable pageable = PageRequest.of(searchRoomDTO.getPage(), searchRoomDTO.getSize(), Sort.by(orders));

            Page<Room> page = roomRepo.findAll(pageable);
            ModelMapper mapper = new ModelMapper();
            List<RoomDTO> roomDTOs = page.getContent().stream()
                    .map(Room -> mapper.map(Room, RoomDTO.class))
                    .collect(Collectors.toList());
            ResponseDTO<List<RoomDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
            responseDTO.setData(roomDTOs);
            return responseDTO;
        } catch (ResourceAccessException e) {
            throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
        }
    }

    @Override
    public Room findByName(String RoomName) {
//        LOG.info("Found Room by RoomName:" + RoomName);
//        Room room = roomRepo.findByName(RoomName)
//                .orElseThrow(() -> new BadRequestAlertException("Not Found Room", RoomName, RoomName));
//        return room;
    	return null;
    }

    @Override
    public Long count() {
        LOG.info("Count Room");
        return roomRepo.count();
    }


}
