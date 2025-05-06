package com.utc.rental.rental.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.zalando.problem.Problem;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.authority.AuthorityDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.Authority;
import com.utc.rental.rental.repository.AuthorityRepo;
import com.utc.rental.rental.service.AuthorityService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;
@Service
public class AuthorityServiceImpl implements AuthorityService {
	
	@Autowired
	AuthorityRepo authorityRepo;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthorityServiceImpl.class);
	

	@Override
	public AuthorityDTO create(AuthorityDTO authorityDTO) {
		
		LOG.info("Create authority: "+authorityDTO.toString());
		
		authorityDTO.setId(null);
		ModelMapper mapper = new ModelMapper();
		
		if(authorityRepo.findByName(authorityDTO.getName().trim()).isPresent()) {
			LOG.error("Create authority fail, Authority exists: "+authorityDTO.toString());
			throw new BadRequestAlertException("Authority exists", "authority", "exits");
		}
		
		Authority authority = mapper.map(authorityDTO, Authority.class);
		authorityRepo.save(authority);
		return authorityDTO;
	}

	@Override
	public Boolean delete(Long id) {
		LOG.info("Delete authority by id: "+id.toString());
		try {
			authorityRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOG.error("FAIL TO delete authority by id: "+id.toString());
			return false;
		}
	}

	@Override
	public AuthorityDTO update(AuthorityDTO authorityDTO) {
		ModelMapper mapper = new ModelMapper();
		LOG.info("Update authority by id: "+authorityDTO.toString());
		Authority authority = authorityRepo.findById(authorityDTO.getId())
				.orElseThrow(()-> new BadRequestAlertException("Not Found Authority", "authority", "NotFound"));
		
		if(!authorityDTO.getName().trim().toLowerCase().toString().equals(authority.getName().trim().toLowerCase().toString())) {
			if(authorityRepo.findByName(authorityDTO.getName()).isPresent()) {
				LOG.error("Create authority fail, Authority exists: "+authorityDTO.toString());
				throw new BadRequestAlertException("Authority exists", "authority", "exits");
			}
		}

		Authority authority2 = mapper.map(authorityDTO, Authority.class);
		authority2.setRoles(authority.getRoles());
		
		authorityRepo.save(authority2);
		
		return authorityDTO;
		
	}

	@Override
	public Authority get(Long id) {
		LOG.info("Get authority by id:"+id.toString());
		Authority authority = authorityRepo.findById(id)
				.orElseThrow(()-> new BadRequestAlertException("Not Found Authority", "authority", "NotFound"));
		return authority;
	}

	@Override
	public List<AuthorityDTO> getAll() {
		LOG.info("Get all authority");
		ModelMapper mapper = new ModelMapper();
		List<Authority> authorities = authorityRepo.findAll();
		if(authorities.size()<=0) {
			LOG.error("Not found any authority");
			return new ArrayList<AuthorityDTO>();
		}
		return authorities.stream().map(authority -> mapper.map(authority, AuthorityDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<AuthorityDTO> getByIds(List<Long> ids) {
		LOG.info("search authority by list ids:"+ ids.toString());
		List<Authority> authorities = authorityRepo.findAllById(ids);
		if(authorities.size()<=0) {
			LOG.error("Not found any authority");
			return new ArrayList<AuthorityDTO>();
		}
		ModelMapper mapper = new ModelMapper();
		return authorities.stream().map(authority -> mapper.map(authority, AuthorityDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ResponseDTO<List<AuthorityDTO>> search(SearchDTO searchDTO) {
		try {
			List<Sort.Order> orders = Optional.ofNullable(searchDTO.getOrders()).orElseGet(Collections::emptyList)
					.stream().map(order -> {
						if (order.getOrder().equals(SearchDTO.ASC))
							return Sort.Order.asc(order.getProperty());

						return Sort.Order.desc(order.getProperty());
					}).collect(Collectors.toList());
			Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(orders));

			Page<Authority> page = authorityRepo.search(searchDTO.getValue(), pageable);
			ModelMapper mapper = new ModelMapper();
			List<AuthorityDTO> levelDTOs = page.getContent().stream()
					  .map(Authority -> mapper.map(Authority, AuthorityDTO.class))
					  .collect(Collectors.toList());
			
			
			ResponseDTO<List<AuthorityDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
			responseDTO.setData(levelDTOs);
			return responseDTO;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	public List<AuthorityDTO> getByRoleId(Long roleId) {
		ModelMapper mapper = new ModelMapper();
		List<Authority> authorities = authorityRepo.findByRolesId(roleId);
		if(authorities==null) return new ArrayList<AuthorityDTO>();
		if(authorities.size()==0 ) return new ArrayList<AuthorityDTO>();
		return authorities.stream().map(a -> mapper.map(a, AuthorityDTO.class)).collect(Collectors.toList());
	}

	@Override
	public Long count() {
		LOG.info("Count authority");
		return authorityRepo.count();
	}

}
