package com.utc.rental.rental.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import com.utc.rental.rental.dto.bill.BillDTO;
import com.utc.rental.rental.dto.payment.PaymentDTO;
import com.utc.rental.rental.dto.response.ResponseDTO;
import com.utc.rental.rental.dto.search.SearchDTO;
import com.utc.rental.rental.dto.search.SearchPayment;
import com.utc.rental.rental.entity.Bill;
import com.utc.rental.rental.entity.Payment;
import com.utc.rental.rental.repository.BillRepo;
import com.utc.rental.rental.repository.PaymentRepo;
import com.utc.rental.rental.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepo paymentRepo;

	@Autowired
	private BillRepo billRepo;

	@Override
	public PaymentDTO create(PaymentDTO paymentDTO) {
		ModelMapper mapper = new ModelMapper();
		Payment payment = mapper.map(paymentDTO, Payment.class);
		payment.setId(UUID.randomUUID().toString().replace("-", ""));
		Bill bill = billRepo.findById(paymentDTO.getBill().getId()).orElseThrow();
		payment.setBill(bill);
		paymentRepo.save(payment);
		return mapper.map(payment, PaymentDTO.class);
	}

	@Override
	public Boolean delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentDTO update(PaymentDTO paymentDTO) {
		if (paymentRepo.existsById(paymentDTO.getId())) {
			throw new BadRequestAlertException("Không tìm thấy lịch sử thanh toán", "payment", "Not Found");
		}
		ModelMapper mapper = new ModelMapper();
		Payment payment = mapper.map(paymentDTO, Payment.class);
		payment.setId(UUID.randomUUID().toString().replace("-", ""));
		Bill bill = billRepo.findById(paymentDTO.getBill().getId()).orElseThrow();
		payment.setBill(bill);
		paymentRepo.save(payment);
		return mapper.map(payment, PaymentDTO.class);
	}

	@Override
	public PaymentDTO get(String id) {
		ModelMapper mapper = new ModelMapper();
		Payment payment = paymentRepo.findById(id).orElseThrow(
				() -> new BadRequestAlertException("Không tìm thấy lịch sử thanh toán", "payment", "Not Found"));
		return mapper.map(payment, PaymentDTO.class);
	}

	@Override
	public List<PaymentDTO> getAll() {
		ModelMapper mapper = new ModelMapper();
		List<Payment> payments = paymentRepo.findAll();
		return payments.stream().map(a -> mapper.map(a, PaymentDTO.class)).collect(Collectors.toList());
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return paymentRepo.count();
	}

	@Override
	public ResponseDTO<List<PaymentDTO>> search(SearchPayment searchPayment) {
		SearchDTO searchDTO = searchPayment.getSearchDTO();
		try {
			List<Sort.Order> orders = Optional.ofNullable(searchDTO.getOrders()).orElseGet(Collections::emptyList)
					.stream().map(order -> {
						if (order.getOrder().equals(SearchDTO.ASC))
							return Sort.Order.asc(order.getProperty());

						return Sort.Order.desc(order.getProperty());
					}).collect(Collectors.toList());
			Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(orders));
			Page<Payment> page = paymentRepo.searchPayments(searchPayment.getPaymentMethod(),
					searchPayment.getStartDate(), searchPayment.getEndDate(), searchPayment.getStatus(),
					searchPayment.getMinAmount(), searchPayment.getMaxAmount(), pageable);

			ModelMapper mapper = new ModelMapper();
			List<PaymentDTO> RoomServiceDTOs = page.getContent().stream().map(Room -> mapper.map(Room, PaymentDTO.class))
					.collect(Collectors.toList());
			ResponseDTO<List<PaymentDTO>> responseDTO = mapper.map(page, ResponseDTO.class);
			responseDTO.setData(RoomServiceDTOs);
			return responseDTO;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

}
