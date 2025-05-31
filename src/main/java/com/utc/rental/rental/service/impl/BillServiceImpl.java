package com.utc.rental.rental.service.impl;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.bill.BillDTO;
import com.utc.rental.rental.dto.contract.ContractDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.roomReturn.RoomReturnDTO;
import com.utc.rental.rental.dto.room_service.RoomServiceDTO;
import com.utc.rental.rental.dto.search.SearchBill;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.Bill;
import com.utc.rental.rental.entity.BillDetail;
import com.utc.rental.rental.entity.Contractt;
import com.utc.rental.rental.entity.Renter;
import com.utc.rental.rental.entity.Room;
import com.utc.rental.rental.entity.RoomReturnHistory;
import com.utc.rental.rental.entity.Room_Service_Room;
import com.utc.rental.rental.repository.BillDetailRepo;
import com.utc.rental.rental.repository.BillRepo;
import com.utc.rental.rental.repository.ContractRepo;
import com.utc.rental.rental.repository.RenterRepo;
import com.utc.rental.rental.repository.RoomRepo;
import com.utc.rental.rental.repository.RoomReturnHistoryRepo;
import com.utc.rental.rental.service.BillService;
@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepo billRepo;
	
	@Autowired
	private RenterRepo renterRepo;
	
	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private ContractRepo contractRepo;
	
	@Autowired
	private BillDetailRepo billDetailRepo;
	
	@Autowired
	private RoomReturnHistoryRepo roomReturnHistoryRepo;
	
	@Override
	public BillDTO create(BillDTO billDTO) {
		System.err.println("create");
		System.err.println(billDTO.toString());
//		Bill bill = new ModelMapper().map(billDTO, Bill.class);
//		bill.setId(UUID.randomUUID().toString().replace("-", ""));
//		Renter renter = renterRepo.findById(billDTO.getRenter().getId()).orElseThrow();
//		bill.setRenter(renter);
//		Room room = roomRepo.findById(billDTO.getRoom().getId()).orElseThrow();
//		bill.setRoom(room);
//		billRepo.save(bill);
		return billDTO;
	}

	@Override
	public Boolean delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillDTO update(BillDTO billDTO) {
		if (!billRepo.existsById(billDTO.getId())) {
			throw new BadRequestAlertException("Not Found Bill", "Bill", "NotFound");
		}
		Bill bill = new ModelMapper().map(billDTO, Bill.class);
		if(billDTO.getRenter().getId()!=null) {
			Renter renter = renterRepo.findById(billDTO.getRenter().getId()).orElseThrow();
			bill.setRenter(renter);
		}
		if(billDTO.getRoom().getId()!=null) {
			Room room = roomRepo.findById(billDTO.getRoom().getId()).orElseThrow();
			bill.setRoom(room);
		}
		
		List<BillDetail> billDetails = bill.getBillDetails();
		for (BillDetail billDetail : billDetails) {
			billDetail.setBill(bill);
			billDetailRepo.save(billDetail);
		}
		
		billRepo.save(bill);
		return billDTO;
	}

	@Override
	public BillDTO get(String id) {
		Bill bill = billRepo.findById(id).orElseThrow();
		return new ModelMapper().map(bill, BillDTO.class);
	}

	@Override
	public List<BillDTO> getAll() {
		ModelMapper mapper = new ModelMapper();
		List<Bill> bills = billRepo.findAll();
		return bills.stream().map(b-> mapper.map(b, BillDTO.class)).collect(Collectors.toList());
	}

	@Override
	public Long count() {
		return billRepo.count();
	}

	@Override
	public ResponseDTO<List<BillDTO>> search(SearchBill searchBill) {
		SearchDTO searchDTO= searchBill.getSearchDTO();
        try {
            List<Sort.Order> orders = Optional.ofNullable(searchDTO.getOrders()).orElseGet(Collections::emptyList)
                    .stream().map(order -> {
                        if (order.getOrder().equals(SearchDTO.ASC))
                            return Sort.Order.asc(order.getProperty());

                        return Sort.Order.desc(order.getProperty());
                    }).collect(Collectors.toList());
            Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(orders));
            Page<Bill> page = billRepo.findAll(pageable);
            
            ModelMapper mapper = new ModelMapper();
            List<BillDTO> RoomServiceDTOs = page.getContent().stream()
                    .map(Room -> mapper.map(Room, BillDTO.class))
                    .collect(Collectors.toList());
            ResponseDTO<List<BillDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
            responseDTO.setData(RoomServiceDTOs);
            return responseDTO;
        } catch (ResourceAccessException e) {
            throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
        }
	}

	@Override
	public BillDTO createREFUND(RoomReturnDTO roomReturnDTO) {
		
		RoomReturnHistory roomReturn = roomReturnHistoryRepo.findById(roomReturnDTO.getId()).orElseThrow();
		
		Bill bill = new Bill();
		
		bill.setId(UUID.randomUUID().toString().replace("-", ""));
		Contractt contractt = contractRepo.findById(roomReturnDTO.getContractId()).orElseThrow();
		contractt.setStatus("WAITING_CHECKOUT");
		bill.setRenter(contractt.getRenter());
		bill.setRoom(contractt.getRoom());
		
		BillDetail billDetail = new BillDetail();
		billDetail.setId(null);
		billDetail.setBill(bill);
		billDetail.setName("Tiền cọc");
		billDetail.setQuantity(1);
		billDetail.setUnitPrice(contractt.getDeposit());
		billDetail.setTotal(billDetail.getUnitPrice()* billDetail.getQuantity());
		
		bill.getBillDetails().add(billDetail);
		
		billRepo.save(bill);
		
		return new ModelMapper().map(bill, BillDTO.class);
	}

}
