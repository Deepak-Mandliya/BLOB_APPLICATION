package com.cg.rewards.controller;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.rewards.model.Transaction;
import com.cg.rewards.service.RewardsService;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

	private final RewardsService service;

	public RewardsController(RewardsService service) {
		this.service = service;
	}

	@GetMapping("/{customerId}/{year}/{month}")
	public Map<String, Number> getMonthRewards(@PathVariable Long customerId, @PathVariable int year,
			@PathVariable int month) {

		YearMonth yearMonth = YearMonth.of(year, month);
		int points = service.getMonthlyRewards(customerId, yearMonth);
		return Map.of("customerId", customerId, "month", month, "points", points);

	}

	@PostMapping(value = "/customer", consumes = "text/plain")
	public ResponseEntity<Transaction> saveDetailsByTransaction(@RequestBody Transaction transaction) {
		Transaction transaction1 = service.saveTransactionDetails(transaction);
		if (transaction1 != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(transaction1);
		}
		return null;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Transaction>> getAllDetailsById() {
		List<Transaction> transaction = service.getAllDetails();
		return ResponseEntity.status(HttpStatus.CREATED).body(transaction);

	}

	@DeleteMapping("delete/{transactionId}")
	public String deleteById(@PathVariable Long transactionId) {
		Transaction transaction = service.deleteTransactionById(transactionId);
		if (transaction == null) {
			return "Id is not deleted from database";
		}
		return "Id is deleted Suuccessfully";

	}
}
