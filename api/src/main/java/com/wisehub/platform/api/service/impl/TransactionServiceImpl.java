package com.wisehub.platform.api.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.exception.InvalidTxException;
import com.wisehub.platform.api.service.TransactionService;
import com.wisehub.platform.data.model.Transaction;
import com.wisehub.platform.data.model.dao.AccountRepository;
import com.wisehub.platform.data.model.dao.TransactionRepository;
import com.wisehub.platform.data.model.dao.UserRepository;
import com.wisehub.platform.data.model.dao.VersionCrudRepository;

@Service
public class TransactionServiceImpl extends AbstractVersionCrudService<Transaction> implements TransactionService {

	private static Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class.getName());

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AccountRepository accountRepository;

	@Override
	protected VersionCrudRepository<Transaction, UUID> getRepository() {
		return transactionRepository;
	}

//	@Autowired
//	private WebSocketHandler eventsHandler;
//	
//	@Value("${trouble.timeout:300000}")
//	private Long timeout;
//
//	
//	private void sendEvent(Transaction transaction) {
//		TransactionEventViewModel event = converter(transaction);
//		
//		try {
//			((TransactionEventHandler) eventsHandler).sendSingleTranscationEventViewModel(event);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	

	@Override
	protected void beforePost(Transaction model) {

		if (userRepository.findById(model.getUserId()) == null) {
			throw new InvalidTxException("Identifications do not exist or are badly formed");
		}

		if (accountRepository.findById(model.getAccountId()) == null) {
			throw new InvalidTxException("Identifications do not exist or are badly formed");
		}

//		sendEvent(model);
	}


//	public String timeout() {
//		String msg = timeout == 0 ? "NEVER" : "" + (timeout / 60000) + " minute(s)";
//		return msg;
//	}
	
//	@Override
//	public Iterable<TransactionEventViewModel> transactionEvents() {
//		List<Transaction> alls = this.transactionRepository.findAll(new DTOParameter());
//		List<TransactionEventViewModel> result = new ArrayList<>();
//		for (Transaction transaction : alls) {
//			TransactionEventViewModel converter = converter(transaction);
//			if (transaction != null){
//				result.add(converter);
//			}
//		}
//		return result;
//	}
//
//	private TransactionEventViewModel converter(Transaction transaction) {
//		if (transaction == null){
//			return null;
//		}
//		TransactionEventViewModel tx = new TransactionEventViewModel();
//		tx.setId(tx.getId());
//		
//		if (transaction.getDeposits() == null && transaction.getDeposits().equals(0.00d) && transaction.getWithdrawals() == null && transaction.getWithdrawals().equals(0.00d)){
//			tx.setAction("Open");
//		} else if (transaction.getWithdrawals() == null && transaction.getWithdrawals().equals(0.00d)){
//			tx.setAction("Withdrawals");
//		} else if (transaction.getDeposits() == null && transaction.getDeposits().equals(0.00d)){
//			tx.setAction("Deposits");
//		}
//		
//		tx.setBalance(transaction.getBalance());
//		tx.setDeposits(transaction.getDeposits());
//		tx.setWithdrawals(transaction.getWithdrawals());
//
//		tx.setCreated(transaction.getCreatedAt());
//		tx.setDatePosted(transaction.getDatePosted());
//		tx.setDescription(transaction.getDescription());
//		
//		return tx;
//	}

}
