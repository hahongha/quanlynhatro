package com.utc.rental.rental.service.impl;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.cmlink.CMLinkDTO;
import com.utc.rental.rental.entity.ContractMember;
import com.utc.rental.rental.entity.ContractMemberLink;
import com.utc.rental.rental.entity.Contractt;
import com.utc.rental.rental.repository.ContractMemberLinkRepo;
import com.utc.rental.rental.repository.ContractMemberRepo;
import com.utc.rental.rental.repository.ContractRepo;
import com.utc.rental.rental.service.ContractMemberLinkService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractMemberLinkServiceImpl implements ContractMemberLinkService {

	@Autowired
	private ContractMemberLinkRepo contractMemberLinkRepo;
	
	@Autowired
	private ContractMemberRepo contractMemberRepo;
	
	@Autowired
	private ContractRepo contractRepo;
	
	@Override
	public CMLinkDTO save(CMLinkDTO link) {
		ModelMapper mapper = new ModelMapper();
		ContractMemberLink contractMemberLink = mapper.map(link, ContractMemberLink.class);
		Contractt contractt = contractRepo.findById(link.getContract().getId()).orElseThrow();
		ContractMember contractMember = contractMemberRepo.findById(link.getContractMember().getId()).orElseThrow();
		contractMemberLink.setContract(contractt);
		contractMemberLink.setContractMember(contractMember);
		contractMemberLink.setActive(link.isActive());
		contractMemberLinkRepo.save(contractMemberLink);
		// TODO Auto-generated method stub
		return mapper.map(contractMemberLink, CMLinkDTO.class);
	}

	@Override
	public CMLinkDTO update(CMLinkDTO link) {
		if(!contractMemberLinkRepo.existsById(link.getId()))
		{
			throw new BadRequestAlertException("Không tìm thấy mối liên hệ giữa hợp đồng và người thuê cùng", "CM LINK","NOT FOUND");
		}
		ContractMemberLink contractMemberLink = contractMemberLinkRepo.findById(link.getId()).orElseThrow();
		Contractt contractt = contractRepo.findById(link.getContract().getId()).orElseThrow();
		ContractMember contractMember = contractMemberRepo.findById(link.getContractMember().getId()).orElseThrow();
		contractMemberLink.setContract(contractt);
		contractMemberLink.setContractMember(contractMember);
		contractMemberLink.setActive(link.isActive());
		contractMemberLinkRepo.save(contractMemberLink);
		ModelMapper mapper = new ModelMapper();
		// TODO Auto-generated method stub
		return mapper.map(contractMemberLink, CMLinkDTO.class);
	}

	@Override
	public void delete(Long id) {
		try {
			contractMemberLinkRepo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			throw new BadRequestAlertException("Lỗi xóa link", "CM LINK","NOT FOUND");
		}
	}

	@Override
	public CMLinkDTO findById(Long id) {
		if(!contractMemberLinkRepo.existsById(id))
		{
			throw new BadRequestAlertException("Không tìm thấy mối liên hệ giữa hợp đồng và người thuê cùng", "CM LINK","NOT FOUND");
		}
		ContractMemberLink conLink = contractMemberLinkRepo.findById(id).orElseThrow();
		return new ModelMapper().map(conLink, CMLinkDTO.class);
	}

	@Override
	public List<CMLinkDTO> findAll() {
		ModelMapper mapper = new ModelMapper();
		return contractMemberLinkRepo.findAll().stream().map(a->mapper.map(a, CMLinkDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<CMLinkDTO> getByContract(String id) {
		ModelMapper mapper = new ModelMapper();
		return contractMemberLinkRepo.findByContract_Id(id).stream().map(a->mapper.map(a, CMLinkDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<CMLinkDTO> getByContractMember(Long id) {
		ModelMapper mapper = new ModelMapper();
		return contractMemberLinkRepo.findByContractMember_Id(id).stream().map(a->mapper.map(a, CMLinkDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<CMLinkDTO> getByContractMember_Contract(Long idMember, String idContract) {
		ModelMapper mapper = new ModelMapper();
		return contractMemberLinkRepo.findByContract_IdOrContractMember_Id(idContract, idMember).stream().map(a->mapper.map(a, CMLinkDTO.class)).collect(Collectors.toList());

	}

	@Override
	public void cancel(Long id) {
		try {
			ContractMemberLink contractMemberLink = contractMemberLinkRepo.findById(id).orElseThrow();
			contractMemberLink.setActive(false);
			contractMemberLinkRepo.save(contractMemberLink);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			throw new BadRequestAlertException("Lỗi xóa link", "CM LINK","NOT FOUND");
		}
	}
}
