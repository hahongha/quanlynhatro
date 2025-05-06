package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.role.RoleDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.Role;

public interface RoleService {
	RoleDTO create(RoleDTO roleDTO);
	
	RoleDTO update(RoleDTO roleDTO);
	
	Role get(Long id);
	
	List<RoleDTO> getAll();

	Boolean delete(Long id);
	
	ResponseDTO<List<RoleDTO>> search(SearchDTO searchDTO);
	
	RoleDTO findByRoleName(String name);
	
	Long count();
	
	Role getByRoleNameOrId(Long id, String name);
	
}
