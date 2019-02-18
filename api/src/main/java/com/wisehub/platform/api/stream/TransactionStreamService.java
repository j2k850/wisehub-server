package com.wisehub.platform.api.stream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wisehub.platform.api.view.model.TransactionEventContainer;
import com.wisehub.platform.api.view.model.TransactionEventViewModel;
import com.wisehub.platform.data.model.Transaction;
import com.wisehub.platform.data.model.dao.DTOParameter;
import com.wisehub.platform.data.model.dao.TransactionRepository;

@Component
public class TransactionStreamService implements
    ApplicationListener<BrokerAvailabilityEvent> {
	
	@Autowired
	TransactionRepository transactionRepository;

    private final MessageSendingOperations<String> messagingTemplate;

    @Autowired
    public TransactionStreamService(
        final MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(final BrokerAvailabilityEvent event) {
    }

//    @Scheduled(fixedDelay = 10000)
    public void sendDataUpdates() {
    	
    	Iterable<TransactionEventViewModel> transactionEvents = transactionEvents();

    	TransactionEventContainer container = new TransactionEventContainer();
    	container.setTransactions(transactionEvents);
    	
        this.messagingTemplate.convertAndSend(
            "/topic/greetings", container);

    }
    
//    @Scheduled(fixedDelay = 50000)
    public void sendDataUpdate() {
    	List<Transaction> transactions = this.transactionRepository.findAll(new DTOParameter());
    	for (Transaction transaction : transactions) {
    		TransactionEventViewModel converter = converter(transaction);
    		if (converter != null){
    			this.messagingTemplate.convertAndSend(
    					"/topic/greetings", converter);
    		}
		}
    }
    
	public Iterable<TransactionEventViewModel> transactionEvents() {
		List<Transaction> alls = this.transactionRepository.findAll(new DTOParameter());
		List<TransactionEventViewModel> result = new ArrayList<>();
		for (Transaction transaction : alls) {
			TransactionEventViewModel converter = converter(transaction);
			if (transaction != null){
				result.add(converter);
			}
		}
		return result;
	}

	private TransactionEventViewModel converter(Transaction transaction) {
		if (transaction == null){
			return null;
		}
		TransactionEventViewModel tx = new TransactionEventViewModel();
		tx.setId(tx.getId());
		
		if (transaction.getDeposits() == null && transaction.getDeposits().equals(0.00d) && transaction.getWithdrawals() == null && transaction.getWithdrawals().equals(0.00d)){
			tx.setAction("Open");
		} else if (transaction.getWithdrawals() == null && transaction.getWithdrawals().equals(0.00d)){
			tx.setAction("Withdrawals");
		} else if (transaction.getDeposits() == null && transaction.getDeposits().equals(0.00d)){
			tx.setAction("Deposits");
		}
		
		tx.setBalance(transaction.getBalance());
		tx.setDeposits(transaction.getDeposits());
		tx.setWithdrawals(transaction.getWithdrawals());

		tx.setCreated(transaction.getCreatedAt());
		tx.setDatePosted(transaction.getDatePosted());
		tx.setDescription(transaction.getDescription());
		
		return tx;
	}
}