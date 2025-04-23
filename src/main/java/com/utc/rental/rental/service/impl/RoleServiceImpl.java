package com.utc.rental.rental.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.sl.draw.geom.GuideIf.Op;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.SearchDTO;
import com.utc.rental.rental.dto.authority.AuthorityDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.role.RoleDTO;
import com.utc.rental.rental.entity.Authority;
import com.utc.rental.rental.entity.Role;
import com.utc.rental.rental.repository.RoleRepo;
import com.utc.rental.rental.service.AuthorityService;
import com.utc.rental.rental.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepo roleRepo;

	@Autowired
	AuthorityService authorityService;

	private static final Logger LOG = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Override
	public RoleDTO create(RoleDTO roleDTO) {

		LOG.info("Create Role: " + roleDTO.toString());

		ModelMapper mapper = new ModelMapper();

		if (roleRepo.findByRoleName(roleDTO.getRoleName().trim()).isPresent()) {
			LOG.error("Create Role fail, Role exists: " + roleDTO.toString());
			throw new BadRequestAlertException("Role exists", "Role", "exits");
		}

		Set<Authority> authorities = new HashSet<Authority>();
		if (roleDTO.getAuthorityDTOs() == null || roleDTO.getAuthorityDTOs().size() == 0)
			authorities = new HashSet<Authority>();
		else {
			for (AuthorityDTO authorityDTO : roleDTO.getAuthorityDTOs()) {
				authorities.add(authorityService.get(authorityDTO.getId()));
			}
		}

		Role role = mapper.map(roleDTO, Role.class);
		role.setAuthorities(authorities);
		roleRepo.save(role);
		return roleDTO;
	}

	@Override
	public Boolean delete(Long id) {
		LOG.info("Delete Role by id: " + id.toString());
		try {
			roleRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOG.error("FAIL TO delete Role by id: " + id.toString());
			return false;
		}
	}

	@Override
	public RoleDTO update(RoleDTO roleDTO) {
		ModelMapper mapper = new ModelMapper();
		LOG.info("Update Role by id: " + roleDTO.toString());
		Role role = roleRepo.findById(roleDTO.getId())
				.orElseThrow(() -> new BadRequestAlertException("Not Found Role", "Role", "NotFound"));
		Set<Authority> authorities = new HashSet<Authority>();
		if (roleDTO.getAuthorityDTOs() == null || roleDTO.getAuthorityDTOs().size() == 0)
			authorities = new HashSet<Authority>();
		else {
			for (AuthorityDTO authorityDTO : roleDTO.getAuthorityDTOs()) {
				authorities.add(authorityService.get(authorityDTO.getId()));
			}
		}
		
		if (roleDTO.getRoleName().trim().toLowerCase().toString().equals(role.getRoleName().trim().toLowerCase().toString())) {
			if(roleRepo.findByRoleName(roleDTO.getRoleName()).isPresent()) {
				LOG.error("Create Role fail, Role exists: " + roleDTO.toString());
				throw new BadRequestAlertException("Role exists", "Role", "exits");
			}
		}
		
		Role role2 = mapper.map(roleDTO, Role.class);

		role2.setAuthorities(authorities);
		
		roleRepo.save(role2);
		
		return roleDTO;

	}

	@Override
	public Role get(Long id) {
		LOG.info("Get Role by id:" + id.toString());
		Role role = roleRepo.findById(id)
				.orElseThrow(() -> new BadRequestAlertException("Not Found Role", "Role", "NotFound"));
		return role;
	}

	@Override
	public List<RoleDTO> getAll() {
		LOG.info("Get all Role");
		ModelMapper mapper = new ModelMapper();
		List<Role> authorities = roleRepo.findAll();
		if (authorities.size() <= 0) {
			LOG.error("Not found any Role");
			return new ArrayList<RoleDTO>();
		}
		return authorities.stream().map(Role -> mapper.map(Role, RoleDTO.class)).collect(Collectors.toList());
	}
	
	@Override
	public ResponseDTO<List<RoleDTO>> search(SearchDTO searchDTO) {
		try {
			List<Sort.Order> orders = Optional.ofNullable(searchDTO.getOrders()).orElseGet(Collections::emptyList)
					.stream().map(order -> {
						if (order.getOrder().equals(SearchDTO.ASC))
							return Sort.Order.asc(order.getProperty());

						return Sort.Order.desc(order.getProperty());
					}).collect(Collectors.toList());
			Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(orders));

			Page<Role> page = roleRepo.search(searchDTO.getValue(), pageable);
			ModelMapper mapper = new ModelMapper();
			List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
			for (Role role : page.getContent()) {
				RoleDTO roleDTO = new RoleDTO();
				roleDTO.setId(role.getId());
				roleDTO.setRoleName(role.getRoleName());
				if(role.getAuthorities()!=null) {
					roleDTO.getAuthorityDTOs().addAll(authorityService.getByRoleId(role.getId()));
				}
				roleDTOs.add(roleDTO);
			}
			ResponseDTO<List<RoleDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
			responseDTO.setData(roleDTOs);
			return responseDTO;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	public RoleDTO findByRoleName(String name) {
		LOG.info("find Role by roleName: " + name);
		RoleDTO roleDTO = new RoleDTO();
		Optional<Role> role = roleRepo.findByRoleName(name);
		if(role.isEmpty()) return null;
		return new ModelMapper().map(role, RoleDTO.class);
	}

	@Override
	public Long count() {
		LOG.info("Count role");
		return roleRepo.count();
	}

	@Override
	public Role getByRoleNameOrId(Long id, String name) {
		return roleRepo.findByRoleNameOrId(name, id).orElseThrow(()-> new BadRequestAlertException("Not found Role", "Role","Not Found"));
	}
}
