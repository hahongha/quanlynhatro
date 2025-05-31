package com.utc.rental.rental.api.rest;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.status.CommentDTO;
import com.utc.rental.rental.dto.status.StatusDTO;
import com.utc.rental.rental.security.securityv2.CurrentUser;
import com.utc.rental.rental.security.securityv2.UserPrincipal;
import com.utc.rental.rental.service.BlogService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/blog")
public class BlogAPI {

	@Autowired
	private BlogService blogService;

	@GetMapping("/statuses")
	public ResponseDTO<List<StatusDTO>> getAllStatuses() {
		return ResponseDTO.<List<StatusDTO>>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(blogService.getAllStatuses()).build();
	}

	@PostMapping("/statuses")
	public ResponseDTO<StatusDTO> postStatus(@RequestBody StatusDTO status, @CurrentUser UserPrincipal userPrincipal) {
		return ResponseDTO.<StatusDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(blogService.addStatus(status, userPrincipal.getUser_id())).build();

	}

	@PostMapping("/comments")
	public ResponseDTO<CommentDTO> postComment(@RequestBody CommentDTO comment,
			@CurrentUser UserPrincipal userPrincipal) {
		return ResponseDTO.<CommentDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(blogService.addComment(comment, userPrincipal.getUser_id())).build();
	}

	@GetMapping("/status/{statusId}")
	public ResponseDTO<StatusDTO> getStatus(@PathVariable String statusId) {
		return ResponseDTO.<StatusDTO>builder().code(String.valueOf(HttpStatus.OK.value()))
				.data(blogService.getStatus(statusId)).build();

	}

	@DeleteMapping("/status/{id}")
	public ResponseDTO<Void> deleteStatus(@PathVariable(value = "id") String id,
			@CurrentUser UserPrincipal userPrincipal) throws URISyntaxException {
		blogService.deleteStatus(id);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}

	@DeleteMapping("/comment/{id}")
	public ResponseDTO<Void> deleteComment(@PathVariable(value = "id") String id,
			@CurrentUser UserPrincipal userPrincipal) throws URISyntaxException {
		blogService.deleteComment(id);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
}
