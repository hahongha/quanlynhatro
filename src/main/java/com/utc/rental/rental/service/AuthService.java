package com.utc.rental.rental.service;

import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.user.UserLogin;
import com.utc.rental.rental.entity.User;

public interface AuthService {
	ResponseDTO<String> signin(UserLogin loginRequest, User user);
	ResponseDTO<String> handleRefreshToken(String refreshToken_in);
	void logout(String accesstoken,String refreshtoken);
}
