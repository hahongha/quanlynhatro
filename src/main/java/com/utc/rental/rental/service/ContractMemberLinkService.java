package com.utc.rental.rental.service;


import java.util.List;

import com.utc.rental.rental.dto.cmlink.CMLinkDTO;

public interface ContractMemberLinkService {
    CMLinkDTO save(CMLinkDTO link);
    CMLinkDTO update(CMLinkDTO link);
    void delete(Long id);
    void cancel(Long id);
    CMLinkDTO findById(Long id);
    List<CMLinkDTO> findAll();
    List<CMLinkDTO> getByContract(String id);
    List<CMLinkDTO> getByContractMember(Long id);
    List<CMLinkDTO> getByContractMember_Contract(Long idMember, String idContract);
}
