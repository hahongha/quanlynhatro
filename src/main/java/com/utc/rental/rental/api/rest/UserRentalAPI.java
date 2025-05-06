package com.utc.rental.rental.api.rest;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utc.rental.rental.dto.bill.BillDTO;
import com.utc.rental.rental.dto.contract.ContractDTO;
import com.utc.rental.rental.dto.index.Electric_WaterDTO;
import com.utc.rental.rental.dto.renter.UserRentalDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.room.RoomDTO;
import com.utc.rental.rental.security.securityv2.CurrentUser;
import com.utc.rental.rental.security.securityv2.UserPrincipal;
import com.utc.rental.rental.service.RenterService;


@RestController
@RequestMapping("/api/userRental")
public class UserRentalAPI {
	@Autowired
	private RenterService renterService;
	
	@GetMapping("/getProfile")
	public ResponseDTO<UserRentalDTO> getProfile(@CurrentUser UserPrincipal currentUser) throws URISyntaxException {
		return ResponseDTO.<UserRentalDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(renterService.getUserRental(currentUser.getUser_id())).build();
	}
	
	@GetMapping("/getContract")
	public ResponseDTO<List<ContractDTO>> getContract(@CurrentUser UserPrincipal currentUser) throws URISyntaxException {
		return ResponseDTO.<List<ContractDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(renterService.getContractFromUser(currentUser.getUser_id())).build();
	}
	
	@GetMapping("/getRoom")
	public ResponseDTO<List<RoomDTO>> getRoom(@CurrentUser UserPrincipal currentUser) throws URISyntaxException {
		return ResponseDTO.<List<RoomDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(renterService.getRoomFromUser(currentUser.getUser_id())).build();
	}
	
	@GetMapping("/getEW")
	public ResponseDTO<List<Electric_WaterDTO>> getEW(@CurrentUser UserPrincipal currentUser, @RequestParam("type") String type) throws URISyntaxException {
		return ResponseDTO.<List<Electric_WaterDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(renterService.getElectric_WaterFromUser
				(currentUser.getUser_id(), type)).build();
	}
	
	@GetMapping("/getBill")
	public ResponseDTO<List<BillDTO>> getBill(@CurrentUser UserPrincipal currentUser) throws URISyntaxException {
		return ResponseDTO.<List<BillDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(renterService.getBillFromUser
				(currentUser.getUser_id())).build();
	}
}
