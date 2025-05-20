package com.cg.rewards.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.rewards.model.Transaction;
import com.cg.rewards.repository.TransactionRepository;

@Service
public class RewardsService implements RewardsServiceInte {

	private static final int HttpStatus = 0;
	@Autowired
	private final TransactionRepository repository;

	public RewardsService(TransactionRepository repository) {
		this.repository = repository;
	}

	public int calculatePoints(double amount) {
		int points = 0;
		if (amount > 100) {
			points += (amount - 100) * 2 + 50;
		} else if (amount > 50) {
			points += amount - 50;
		}
		return points;
	}

	public int getMonthlyRewards(Long customerId, YearMonth month) {

		LocalDate startDate = month.atDay(1);
		LocalDate endDate = month.atEndOfMonth();
		List<Transaction> transaction = repository.findByCustomerIdAndTransactionDataBetween(customerId, startDate,
				endDate);
		return transaction.stream().mapToInt(Transaction::getPointEarned).sum();

	}

	@Override
	public Transaction saveTransactionDetails(Transaction transaction) {
		// TODO Auto-generated method stub
		Transaction transaction1 = repository.save(transaction);
		if (transaction1 != null) {
			return transaction1;
		}
		return null;
	}

	@Override
	public Transaction deleteTransactionById(Long transactionId) {
		// TODO Auto-generated method stub
		Transaction transaction = repository.findById(transactionId).orElse(null);

		if (transaction != null) {
			repository.delete(transaction);
			return transaction;
		}
		return null;
	}

	@Override
	public Transaction updateTransactionById(Transaction transaction, Long transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllDetails() {
		// TODO Auto-generated method stub
		List<Transaction> transaction = repository.findAll();
		if (transaction != null) {
			return transaction;
		}
		return null;
	}

	@Override
	public Transaction getTransactionById(Long transactionId) {

		// TODO Auto-generated method stub
		Transaction transaction = repository.findById(transactionId).orElse(null);
		if (transaction != null) {
			return transaction;
		}
		return null;
	}

}
