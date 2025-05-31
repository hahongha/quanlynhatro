package com.utc.rental.rental.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utc.rental.rental.api.error.BadRequestAlertException;
import com.utc.rental.rental.dto.status.CommentDTO;
import com.utc.rental.rental.dto.status.StatusDTO;
import com.utc.rental.rental.entity.Comment;
import com.utc.rental.rental.entity.Statuss;
import com.utc.rental.rental.entity.User;
import com.utc.rental.rental.repository.CommentRepo;
import com.utc.rental.rental.repository.StatusRepo;
import com.utc.rental.rental.repository.UserRepo;

@Service
public class BlogService {
	@Autowired
	private StatusRepo statusRepo;
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private UserRepo userRepo;

	public List<StatusDTO> getAllStatuses() {
		List<Statuss> ss=  statusRepo.findAllByOrderByCreatedAtDesc();
		ModelMapper mapper = new ModelMapper();
		return ss.stream().map(a-> mapper.map(a, StatusDTO.class)).collect(Collectors.toList());
	}

	public StatusDTO addStatus(StatusDTO statuss, String idUser) {
		User user = userRepo.findById(idUser).orElseThrow();
		ModelMapper mapper = new ModelMapper();
		Statuss status = mapper.map(statuss, Statuss.class);
		status.setId(UUID.randomUUID().toString().replace("-", ""));
		status.setCreatedAt(LocalDateTime.now());
		status.setAuthor(user);
		statusRepo.save(status);
		return statuss;
	}
	
	public StatusDTO updateStatus(StatusDTO statuss, String idUser) {
		if(statusRepo.existsById(statuss.getId())) {
			throw new BadRequestAlertException("Không thấy khiếu nại", "Status", "Not Found");
		}
		User user = userRepo.findById(idUser).orElseThrow();
		ModelMapper mapper = new ModelMapper();
		Statuss status = mapper.map(statuss, Statuss.class);
		status.setAuthor(user);
		statusRepo.save(status);
		return statuss;
	}

	public CommentDTO addComment(CommentDTO commentDTO, String idUser) {
		User user = userRepo.findById(idUser).orElseThrow();
		ModelMapper mapper = new ModelMapper();
		Comment comment = mapper.map(commentDTO, Comment.class);
		comment.setId(UUID.randomUUID().toString().replace("-", ""));
		comment.setCreatedAt(LocalDateTime.now());
		comment.setAuthor(user);
		commentRepo.save(comment);
		return commentDTO;
	}
	
	public StatusDTO getStatus(String id) {
		ModelMapper mapper = new ModelMapper();
		Statuss status = statusRepo.findById(id).orElseThrow(()-> new BadRequestAlertException("Không thấy khiếu nại", "Status", "Not Found"));
		return mapper.map(status, StatusDTO.class);
	}
	
	public void deleteComment(String id) {
		try {
			commentRepo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadRequestAlertException("Lỗi xóa comment", "comment", "Not Found");
			// TODO: handle exception
		}
	}
	
	public void deleteStatus(String id) {
		try {
			Statuss status = statusRepo.findById(id).orElseThrow(); 
			commentRepo.deleteAll(status.getComments());
			statusRepo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadRequestAlertException("Lỗi xóa comment", "comment", "Not Found");
			// TODO: handle exception
		}
	}
}
