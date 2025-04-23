package com.utc.rental.rental.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.user.UserLogin;
import com.utc.rental.rental.entity.User;
import com.utc.rental.rental.security.securityv2.JwtTokenProvider;
import com.utc.rental.rental.service.AuthService;
import com.utc.rental.rental.service.UserService;

import jakarta.persistence.NoResultException;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);
	
	
	@Override
	public ResponseDTO<String> signin(UserLogin loginRequest, User user) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String accessToken = tokenProvider.generateAccessToken(authentication);
			String refreshToken = tokenProvider.generateRefreshToken(user.getUserName());
			
			LOG.info("User "+ loginRequest.toString() + " login at "+ Instant.now().toString());
			LOG.info("accessToken: "+ accessToken);
			LOG.info("refreshToken: " + refreshToken);
			
			return ResponseDTO.<String>builder().code(String.valueOf(HttpStatus.OK.value())).accessToken(accessToken)
					.refreshToken(refreshToken).build();
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}


	@Override
	public ResponseDTO<String> handleRefreshToken(String refreshToken_in) {
		try {
//			InvalidToken invalidToken = tokenProvider.getToken(refreshToken_in);
//			if(invalidTokenRepo.existsById(invalidToken.getId())) {
//				logger.error(invalidToken.toString());
//				throw new BadRequestAlertException("Token is expired", ENTITY_NAME, "expired");
//			}
//			Optional<List<InvalidToken>> tokenOps = invalidTokenRepo.getExpireToken(new Date());
//			
//			if(tokenOps.isPresent()) {
//				invalidTokenRepo.deleteAll(tokenOps.get());
//			}
				
			
			String username = tokenProvider.getUserIdFromJWT(refreshToken_in);
			if (username.isEmpty())
				throw new org.springframework.security.access.AccessDeniedException("Access Denied");

			User user = userService.findByUserName(username);

			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), null));

			String accessToken = tokenProvider.generateAccessToken(authentication);
			String refreshToken = tokenProvider.generateRefreshToken(user.getUserName());

			return ResponseDTO.<String>builder().code(String.valueOf(HttpStatus.OK.value())).accessToken(accessToken)
					.refreshToken(refreshToken).build();

		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	public void logout(String accesstoken, String refreshToken) {
//		try {
//			InvalidToken invalidAccessToken = tokenProvider.getToken(accesstoken);
//			invalidTokenRepo.save(invalidAccessToken);
//		}catch (Exception e) {
//			logger.error("access_token is expired"+ accesstoken);
//		}
//		try {
//		InvalidToken invalidRefreshToken = tokenProvider.getToken(refreshToken);
//		invalidTokenRepo.save(invalidRefreshToken);
//		}catch (Exception e) {
//			logger.error("refresh_token is expired"+ refreshToken);
//		}
	}

}
