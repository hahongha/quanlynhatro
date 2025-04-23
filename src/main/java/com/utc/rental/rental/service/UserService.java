package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.SearchDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.user.UpdatePasswordDTO;
import com.utc.rental.rental.dto.user.UserDTO;
import com.utc.rental.rental.dto.user.UserUpdateDTO;
import com.utc.rental.rental.entity.User;


public interface UserService {
	UserDTO create (UserDTO userDTO);
	
	UserDTO update(UserDTO userDTO);
	Boolean delete(String id);
	User get(String id);
	UserDTO getDTO(String id);
	List<UserDTO> getAll();
	UserDTO updatePassword(UpdatePasswordDTO updatePasswordDTO);
	UserDTO inActiveAccount(String id);
	UserDTO updateByUser(UserDTO userDTO);
	ResponseDTO<List<UserDTO>> search(SearchDTO searchDTO);
	User findByUserName(String userName);
	UserDTO updateUserProfile(UserUpdateDTO userUpdateDTO);
	Long count();
}
