package com.utc.rental.rental.api.rest;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utc.rental.rental.dto.renter.UserRentalDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.staff.StaffDTO;
import com.utc.rental.rental.security.securityv2.CurrentUser;
import com.utc.rental.rental.security.securityv2.UserPrincipal;
import com.utc.rental.rental.service.RenterService;
import com.utc.rental.rental.service.StaffService;

@RestController
@RequestMapping("/api/manager")
public class ManageAPI {
	@Autowired
	private StaffService staffService;
	
	@GetMapping("/getProfile")
	public ResponseDTO<StaffDTO> getProfile(@CurrentUser UserPrincipal currentUser) throws URISyntaxException {
		return ResponseDTO.<StaffDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(staffService.getProfile(currentUser.getUser_id())).build();
	}
}
