package com.utc.rental.rental.service.impl;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.SearchDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.user.UpdatePasswordDTO;
import com.utc.rental.rental.dto.user.UserDTO;
import com.utc.rental.rental.dto.user.UserUpdateDTO;
import com.utc.rental.rental.entity.Role;
import com.utc.rental.rental.entity.User;
import com.utc.rental.rental.repository.UserRepo;
import com.utc.rental.rental.service.RoleService;
import com.utc.rental.rental.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RoleService roleService;
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public UserDTO create(UserDTO userDTO) {
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(userDTO, User.class);
		Role role = new Role();
		try {
			role = roleService.getByRoleNameOrId(userDTO.getRole().getId(), userDTO.getRole().getRoleName());
		}catch (Exception e) {
			e.printStackTrace();
		}
		user.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setPassword(new BCryptPasswordEncoder().encode("abcd456789"));
		user.setRole(role);
		user.setStatus(user.getStatus().toUpperCase().toString());
		userRepo.save(user);
		return userDTO;
		
	}

	@Override
	public UserDTO update(UserDTO userDTO) {
		ModelMapper mapper = new ModelMapper();
		User user = userRepo.findById(userDTO.getUserId().toString()).orElseThrow(
				()-> new BadRequestAlertException("Not Found User", "User", "Not Found"));
		
		Role role = roleService.get(userDTO.getRole().getId());
		User user2 = mapper.map(userDTO, User.class);
		user2.setRole(role);
		user2.setPassword(user.getPassword());
		userRepo.save(user2);
		return userDTO;
	}

	@Override
	public Boolean delete(String id) {
		try {
			userRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User get(String id) {
		return userRepo.findById(id).orElseThrow(()-> new BadRequestAlertException("User not found", "user", "Not Found"));
	}

	@Override
	public List<UserDTO> getAll() {
		ModelMapper mapper = new ModelMapper();
		return userRepo.findAll().stream().map(user-> mapper.map(user, UserDTO.class)).collect(Collectors.toList());
	}

	@Override
	public UserDTO updatePassword(UpdatePasswordDTO updatePasswordDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO inActiveAccount(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO updateByUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO<List<UserDTO>> search(SearchDTO searchDTO) {
		try {
		List<Sort.Order> orders = Optional.ofNullable(searchDTO.getOrders()).orElseGet(Collections::emptyList)
				.stream().map(order -> {
					if (order.getOrder().equals(SearchDTO.ASC))
						return Sort.Order.asc(order.getProperty());

					return Sort.Order.desc(order.getProperty());
				}).collect(Collectors.toList());
		Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(orders));

		Page<User> page = userRepo.search(searchDTO.getValue(), pageable);
		ModelMapper mapper = new ModelMapper();
		List<UserDTO> levelDTOs = page.getContent().stream()
				  .map(user -> mapper.map(user, UserDTO.class))
				  .collect(Collectors.toList());
		
		
		ResponseDTO<List<UserDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
		responseDTO.setData(levelDTOs);
		return responseDTO;
	} catch (ResourceAccessException e) {
		throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
	} catch (HttpServerErrorException | HttpClientErrorException e) {
		throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
	}
	}

	@Override
	public User findByUserName(String userName) {
		LOG.info("Found user by userName:" + userName);
		User user = userRepo.findByUserName(userName).orElseThrow(()-> new BadRequestAlertException("Not Found User", userName, userName));
		return user;
	}

	@Override
	public Long count() {
		LOG.info("Count user");
		return userRepo.count();
	}

	@Override
	public UserDTO getDTO(String id) {
		User user = get(id);
		return new ModelMapper().map(user, UserDTO.class);
	}

	@Override
	public UserDTO updateUserProfile(UserUpdateDTO updatedUser) {
		// Giả sử bạn đã có userId trong updatedUser
        User existingUser = userRepo.findById(updatedUser.getUserId())
                                          .orElseThrow(() -> new RuntimeException("User not found"));

        // Cập nhật các trường mà người dùng có thể thay đổi
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setFamilyPhone(updatedUser.getFamilyPhone());
        existingUser.setUserName(updatedUser.getUserName());
        existingUser.setImageUrl(updatedUser.getImageUrl());

        // Lưu lại thông tin cập nhật
        userRepo.save(existingUser);
        
        return new ModelMapper().map(existingUser, UserDTO.class);
	}

}
