package com.utc.rental.rental.security.securityv2;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.utc.rental.rental.entity.Authority;
import com.utc.rental.rental.entity.User;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L; // Hoặc một số cụ thể

	private String user_id;

	private String username;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(String user_id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrincipal create(User user) {

//		Set<String> permissions = new HashSet<String>();
//
//		for (Authority authority : user.getRole().getAuthorities()) {
//			permissions.add(authority.getName());
//		}
//
//		List<GrantedAuthority> authorities = permissions.stream()
//				.map(permission -> new SimpleGrantedAuthority(permission.toUpperCase())).collect(Collectors.toList());
		return new UserPrincipal(user.getUserId(), user.getUserName(), user.getPassword(), null);
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserPrincipal that = (UserPrincipal) o;
		return Objects.equals(user_id, that.user_id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(user_id);
	}
}
