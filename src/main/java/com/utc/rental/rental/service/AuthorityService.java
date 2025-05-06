package com.utc.rental.rental.service;

import java.util.List;

import com.utc.rental.rental.dto.authority.AuthorityDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.Authority;

public interface AuthorityService {
	AuthorityDTO create(AuthorityDTO AuthorityDTO);

	Boolean delete(Long id);
	
	AuthorityDTO update(AuthorityDTO AuthorityDTO);
	
	Authority get(Long id);
	
	List<AuthorityDTO> getAll();
	
	List<AuthorityDTO> getByIds(List<Long> ids);
	
	ResponseDTO<List<AuthorityDTO>> search(SearchDTO searchDTO);
	
	List<AuthorityDTO> getByRoleId(Long roleId);
	Long count();
}
