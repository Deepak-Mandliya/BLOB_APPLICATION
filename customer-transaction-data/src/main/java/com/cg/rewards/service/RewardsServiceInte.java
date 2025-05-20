package com.cg.rewards.service;

import java.util.List;

import com.cg.rewards.model.Transaction;

public interface RewardsServiceInte {

	Transaction saveTransactionDetails(Transaction transaction);

	Transaction deleteTransactionById(Long transactionId);

	Transaction updateTransactionById(Transaction transaction, Long transactionId);

	List<Transaction> getAllDetails();

	Transaction getTransactionById(Long transactionId);

}
