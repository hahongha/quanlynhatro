package com.utc.rental.rental.dto.role;

import java.util.HashSet;
import java.util.Set;

import com.utc.rental.rental.dto.authority.AuthorityDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDTO {
	Long id;
	String roleName;
	String roleDes;
	Set<AuthorityDTO> authorityDTOs = new HashSet<AuthorityDTO>();
}
