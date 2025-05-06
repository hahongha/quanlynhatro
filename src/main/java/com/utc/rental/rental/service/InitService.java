package com.utc.rental.rental.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.utc.rental.rental.dto.authority.AuthorityDTO;
import com.utc.rental.rental.dto.role.RoleDTO;
import com.utc.rental.rental.entity.Role;
import com.utc.rental.rental.entity.User;
import com.utc.rental.rental.repository.RoleRepo;
import com.utc.rental.rental.repository.UserRepo;

import jakarta.persistence.NoResultException;

public interface InitService {
	// thêm authority từ excel mặc định
	void addAuthoritiesDefault();

	// thêm role admin mặc định
	void addRolesDefault();

	// thêm user admin mặc định
	void addUserRootDefault();
	
	void updateAllPass();
	
}

@Service
class InitServiceImpl implements InitService {

	@Autowired
	AuthorityService authorityService;

	@Autowired
	RoleService roleService;

	@Autowired
	UserRepo userRepo;
	
	
	@Autowired
	RoleRepo roleRepo;

	@Override
	public void addAuthoritiesDefault() {
//		// kiểm tra xem có các quyền chưa
		if (authorityService.count() > 0)
			return;
		for (int i = 0; i < 5; i++) {
			authorityService.create(new AuthorityDTO(0L, "A"+i, "A"+i));
		}
	}

	@Override
	public void addRolesDefault() {
		// thêm các role admin và user
		if (roleService.count() > 0)
			return;
		List<AuthorityDTO> aus = authorityService.getAll();
		Set<AuthorityDTO> set = new HashSet<AuthorityDTO>(aus);
		Set<AuthorityDTO> set2 = new HashSet<AuthorityDTO>();
		if (aus.size() == 0)
			addAuthoritiesDefault();
		roleService.create(new RoleDTO(0L, "ROOT","Chủ hệ thống", set));
		roleService.create(new RoleDTO(0L, "OWNER","Chủ trọ", set));
		roleService.create(new RoleDTO(0L, "STAFF","Nhân viên", set2));
		roleService.create(new RoleDTO(0L, "USER","Khách thuê", set2));
	}

	@Override
	public void addUserRootDefault() {
		RoleDTO roleDTO = roleService.findByRoleName("ROOT");
		if (roleDTO == null)
			throw new NoResultException();
		Role role = roleRepo.findByRoleName("ROOT").orElseThrow();
		User admin = new User();
		admin.setUserId("admin");
		admin.setEmail("phamha03122003@gmail.com");
		admin.setRole(role);
		admin.setCreateAt(LocalDate.now());
		admin.setStatus("ACTIVE");
		admin.setUserType("ADMIN");
		admin.setPassword(new BCryptPasswordEncoder().encode("abcd456789"));
		admin.setUserName("admin");
		admin.setUserAvatar("https://res.cloudinary.com/dlyprrqnn/image/upload/v1743004321/TEST/wsyqk6znjakexow6a5pc.jpg");
		userRepo.save(admin);
		
		Role role2 = roleRepo.findByRoleName("USER").orElseThrow();
		User user = new User();
		user.setUserId("user");
		user.setEmail("ptha03120312@gmail.com");
		user.setRole(role2);
		user.setCreateAt(LocalDate.now());
		user.setStatus("ACTIVE");
		user.setUserType("USER");
		user.setPassword(new BCryptPasswordEncoder().encode("abcd456789"));
		user.setUserName("user1");
		user.setUserAvatar("https://res.cloudinary.com/dlyprrqnn/image/upload/v1743004321/TEST/wsyqk6znjakexow6a5pc.jpg");
		userRepo.save(user);
		
		
	}

	@Override
	public void updateAllPass() {
		List<User> users = userRepo.findAll();
		for (User user : users) {
			user.setPassword(new BCryptPasswordEncoder().encode("abcd456789"));
			userRepo.save(user);
		}
		
	}

}
