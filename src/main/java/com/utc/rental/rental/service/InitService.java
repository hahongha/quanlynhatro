package com.utc.rental.rental.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utc.rental.rental.config.DefaultValue.StatusActRef;
import com.utc.rental.rental.config.DefaultValue.TypeGenderRef;
import com.utc.rental.rental.dto.authority.AuthorityDTO;
import com.utc.rental.rental.dto.image.ImageDTO;
import com.utc.rental.rental.dto.role.RoleDTO;
import com.utc.rental.rental.dto.user.UserDTO;
import com.utc.rental.rental.entity.Image;
import com.utc.rental.rental.repository.ImageRepo;

import jakarta.persistence.NoResultException;

public interface InitService {
	// thêm authority từ excel mặc định
	void addAuthoritiesDefault();

	// thêm role admin mặc định
	void addRolesDefault();

	// thêm user admin mặc định
	void addUserRootDefault();
	
	String addImage();
}

@Service
class InitServiceImpl implements InitService {

	@Autowired
	AuthorityService authorityService;

	@Autowired
	RoleService roleService;

	@Autowired
	UserService userService;
	
	@Autowired 
	ImageRepo imageRepo;

	@Override
	public void addAuthoritiesDefault() {
//		// kiểm tra xem có các quyền chưa
		if (authorityService.count() > 0)
			return;
//		String filePath = "/rental/src/main/resources/static/excel/authority.xlsx"; // Thay bằng
//																										// đường dẫn
//																										// thực tế
//		try (FileInputStream fis = new FileInputStream(new File(filePath)); Workbook workbook = new XSSFWorkbook(fis)) {
//
//			Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
//			for (Row row : sheet) {
//				String name = "";
//				String des = "";
//				Cell firstCell = row.getCell(0);
//				Cell secondCell = row.getCell(1);
//				if (firstCell != null) {
//					name = firstCell.toString();
//				}
//				if (secondCell != null) {
//					des = secondCell.toString();
//				}

//				authorityService.create(new AuthorityDTO(0L, name, des));
//			}
		for (int i = 0; i < 5; i++) {
			authorityService.create(new AuthorityDTO(0L, "A"+i, "A"+i));
		}
		
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
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
		if (userService.count() > 0)
			return;
		RoleDTO roleDTO = roleService.findByRoleName("ROOT");
		if (roleDTO == null)
			throw new NoResultException();
		
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId("0L");
		userDTO.setUserName("admin");
		userDTO.setStatus(StatusActRef.ACTIVE.toString());
		userDTO.setEmail("phamha03122003@gmail.com");
		userDTO.setGender(TypeGenderRef.M.toString());
		userDTO.setImageUrl(addImage());
		userDTO.setFullName("Phạm Thị Hà");
		userDTO.setPhone("0388580312");
		userDTO.setDob(LocalDate.now());
		userDTO.setPlaceOfOrigin("Hồng Hà - Đan Phượng - Hà Nội");
		userDTO.setAddress("Hồng Hà - Đan Phượng - Hà Nội");
		userDTO.setFamilyPhone("0388580312");
		userDTO.setNote("Chủ trọ");
		userDTO.setRole(roleDTO);
		userService.create(userDTO);
	}

	@Override
	public String addImage() {
		if(imageRepo.count()>0) {
			List<Image> images = imageRepo.findAll();
			return images.get(0).getImageURL();
		}
		Image image = new Image(0L, "https://res.cloudinary.com/dlyprrqnn/image/upload/v1743004321/TEST/wsyqk6znjakexow6a5pc.jpg", "TEST/wsyqk6znjakexow6a5pc", true);
		imageRepo.save(image);
		return image.getImageURL();
	}

}
