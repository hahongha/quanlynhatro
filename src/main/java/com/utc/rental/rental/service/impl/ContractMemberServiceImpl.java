package com.utc.rental.rental.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.http.annotation.Contract;
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
import com.utc.rental.rental.config.DefaultValue.StatusActRef;
import com.utc.rental.rental.dto.contract.ContractDTO;
import com.utc.rental.rental.dto.contract.ContractResponseDTO;
import com.utc.rental.rental.dto.contractMember.ContractMemberDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.entity.ContractMember;
import com.utc.rental.rental.entity.Contractt;
import com.utc.rental.rental.repository.ContractMemberRepo;
import com.utc.rental.rental.repository.ContractRepo;
import com.utc.rental.rental.service.ContractMemberService;

@Service
public class ContractMemberServiceImpl implements ContractMemberService {

    @Autowired
    ContractRepo contractRepo;
    
    @Autowired
    ContractMemberRepo contractMemberRepo;
    
    
    
    private static final Logger LOG = LoggerFactory.getLogger(ContractMemberServiceImpl.class);

    @Override
    public ContractMemberDTO create(ContractMemberDTO contractMemberDTO) {
        LOG.info("Create ContractMember: " + contractMemberDTO.toString());

        ModelMapper mapper = new ModelMapper();

        ContractMember contractMember = mapper.map(contractMemberDTO, ContractMember.class);
        if(contractMemberDTO.getContractResponseDTO()!=null) {
        Contractt contract = contractRepo.findById(contractMemberDTO.getContractResponseDTO().getId())
        		.orElseThrow(()-> new BadRequestAlertException("Not Found Contract", "Contract", "Not Found"));     
        contractMember.setContract(contract);
        }
        contractMemberRepo.save(contractMember);
        return contractMemberDTO;
    }

    @Override
    public Boolean delete(Long id) {
        LOG.info("Delete ContractMember by id: " + id.toString());
        try {
            contractMemberRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            LOG.error("FAIL TO delete ContractMember by id: " + id.toString());
            return false;
        }
    }

    @Override
    public ContractMemberDTO update(ContractMemberDTO contractMemberDTO) {
        ModelMapper mapper = new ModelMapper();
        LOG.info("Update ContractMember by id: " + contractMemberDTO.toString());
        ContractMember contractMember = contractMemberRepo.findById(contractMemberDTO.getId())
                .orElseThrow(
                        () -> new BadRequestAlertException("Not Found ContractMember", "ContractMember", "NotFound"));
        if(!contractMemberRepo.existsById(contractMemberDTO.getId()))
        	throw new BadRequestAlertException("Not Found ContractMember", "ContractMember", "NotFound");
        ContractMember contractMember2 = mapper.map(contractMemberDTO, ContractMember.class);
        Contractt contract = contractRepo.findById(contractMemberDTO.getContractResponseDTO().getId())
        		.orElseThrow(()-> new BadRequestAlertException("Not Found Contract", "Contract", "Not Found"));   
        
        contractMember2.setContract(contract);
        
        contractMemberRepo.save(contractMember2);

        return contractMemberDTO;

    }

    @Override
    public ContractMember get(Long id) {
        LOG.info("Get ContractMember by id:" + id.toString());
        ContractMember ContractMember = contractMemberRepo.findById(id)
                .orElseThrow(
                        () -> new BadRequestAlertException("Not Found ContractMember", "ContractMember", "NotFound"));
        return ContractMember;
    }

    @Override
    public List<ContractMemberDTO> getAll() {
        LOG.info("Get all ContractMember");
        List<ContractMemberDTO> contractDTOs = new ArrayList<ContractMemberDTO>();
        List<ContractMember> authorities = contractMemberRepo.findAll();
        if (authorities.size() <= 0) {
            LOG.error("Not found any ContractMember");
            return contractDTOs;
        }
        ModelMapper mapper = new ModelMapper();
        ModelMapper modelMapper = new ModelMapper();
        for (ContractMember contractMember : authorities) {
			ContractMemberDTO contractMemberDTO = mapper.map(contractMember, ContractMemberDTO.class);
			ContractResponseDTO contractResponseDTO = modelMapper.map(contractMember.getContract(), ContractResponseDTO.class);
			contractMemberDTO.setContractResponseDTO(contractResponseDTO);
			contractDTOs.add(contractMemberDTO);
		}
        return contractDTOs;
    }

    @Override
    public ResponseDTO<List<ContractMemberDTO>> search(SearchDTO searchDTO) {
        try {
            List<Sort.Order> orders = Optional.ofNullable(searchDTO.getOrders()).orElseGet(Collections::emptyList)
                    .stream().map(order -> {
                        if (order.getOrder().equals(SearchDTO.ASC))
                            return Sort.Order.asc(order.getProperty());

                        return Sort.Order.desc(order.getProperty());
                    }).collect(Collectors.toList());
            Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(orders));

            Page<ContractMember> page = contractMemberRepo.findAll(pageable);
            ModelMapper mapper = new ModelMapper();
            ModelMapper modelMapper = new ModelMapper();
            List<ContractMemberDTO> contractDTOs = new ArrayList<ContractMemberDTO>();
            for (ContractMember contractMember : page.getContent()) {
    			ContractMemberDTO contractMemberDTO = mapper.map(contractMember, ContractMemberDTO.class);
    			ContractResponseDTO contractResponseDTO = modelMapper.map(contractMember.getContract(), ContractResponseDTO.class);
    			contractMemberDTO.setContractResponseDTO(contractResponseDTO);
    			contractDTOs.add(contractMemberDTO);
    		}
            ResponseDTO<List<ContractMemberDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
            responseDTO.setData(contractDTOs);
            return responseDTO;
        } catch (ResourceAccessException e) {
            throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
        }
    }

    @Override
    public Long count() {
        LOG.info("Count ContractMember");
        return contractMemberRepo.count();
    }

	@Override
	public List<ContractMemberDTO> getContractMemberFromContract(String id) {
		ModelMapper mapper = new ModelMapper();
        ModelMapper modelMapper = new ModelMapper();
        List<ContractMemberDTO> contractDTOs = new ArrayList<ContractMemberDTO>();
		Contractt contractt = contractRepo.findById(id).orElseThrow(()-> new BadRequestAlertException("Not Found Contract", "CONTRACTMEMBER", "NotFound"));
		List<ContractMember> contractMembers = contractMemberRepo.findByContractStatus(id, StatusActRef.ACTIVE.toString());
		 for (ContractMember contractMember : contractMembers) {
 			ContractMemberDTO contractMemberDTO = mapper.map(contractMember, ContractMemberDTO.class);
 			ContractResponseDTO contractResponseDTO = modelMapper.map(contractMember.getContract(), ContractResponseDTO.class);
 			contractMemberDTO.setContractResponseDTO(contractResponseDTO);
// 			System.err.println(contractResponseDTO.toString());
 			contractDTOs.add(contractMemberDTO);
 		}
		return contractDTOs;
	}
}
