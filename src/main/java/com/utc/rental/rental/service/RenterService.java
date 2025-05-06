package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.bill.BillDTO;
import com.utc.rental.rental.dto.contract.ContractDTO;
import com.utc.rental.rental.dto.index.Electric_WaterDTO;
import com.utc.rental.rental.dto.renter.RenterDTO;
import com.utc.rental.rental.dto.renter.UserRentalDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.room.RoomDTO;
import com.utc.rental.rental.dto.search.SearchRenter;
import com.utc.rental.rental.dto.service.ServiceDTO;
import com.utc.rental.rental.entity.Renter;

public interface RenterService {
	RenterDTO create(RenterDTO RenterDTO);

	RenterDTO update(RenterDTO RenterDTO);

	Renter get(String id);

	Renter getIdentification(String id);

	Renter findByID(String id, String identification);

	List<RenterDTO> getAll();

	Boolean delete(String id);

	ResponseDTO<List<RenterDTO>> search(SearchRenter searchDTO);

	Long count();

	RenterDTO findByUserId(String id);

	// xem thông tin cá nhân
	UserRentalDTO getUserRental(String id);

	// xem hợp đồng
	List<ContractDTO> getContractFromUser(String id);

	// xem lịch sử hợp đồng
	List<ContractDTO> getContractHistory(String id);

	// xem thông tin phòng
	List<RoomDTO> getRoomFromUser(String id);

	// xem thông tin các dịch vụ hiện tại
	List<ServiceDTO> getServiceFromUser(String id);

	// xem các dịch vụ đã đăng kí với phòng và đang hoạt động
	List<ServiceDTO> getServiceActiveFromUser(String id);

	// xem lịch sử điện nước của phòng

	// xem thông tin lịch sử thông tin dịch vụ

	// xem hóa đơn
	List<BillDTO> getBillActiveFromUser(String id);
	
	List<BillDTO> getBillFromUser(String id);

	// xem lịch sử hóa đơn
	List<BillDTO> getBillCancelFromUser(String id);

	// thanh toán hóa đơn
	List<Electric_WaterDTO> getElectric_WaterFromUser(String id, String type);
	
	Renter addContract(RenterDTO renterDTO);
}
