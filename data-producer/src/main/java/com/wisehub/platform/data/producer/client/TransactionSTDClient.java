package com.wisehub.platform.data.producer.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.wisehub.platform.data.model.Account;
import com.wisehub.platform.data.model.AccountByProductStatus;
import com.wisehub.platform.data.model.ProductByUser;
import com.wisehub.platform.data.model.User;

@Component
@Profile(value = { "test" })
public class TransactionSTDClient implements TransactionClientI {

	private static final Logger log = LoggerFactory.getLogger(TransactionSTDClient.class);

	@Override
	public void postSaleAccount(String token, Account account) {
		log.info("received <" + account + ">");
	}

	@Override
	public void postCustomer(String token, User user) {
		log.info("received <" + user + ">");
	}

	@Override
	public void postTxFromCSV(String token, String content) {
		log.info("received <" + content + ">");
	}

	@Override
	public void postRegister(DashboardRegisterViewModel viewModel) {
		log.info("received <" + viewModel + ">");
		
	}

	@Override
	public String postAuth(DashboardRegisterViewModel viewModel) {
		log.info("received <" + viewModel + ">");
		return null;
	}

	@Override
	public User[] getByBnv(String token, String bvn) {
		return null;
	}

	@Override
	public void postProductByUser(String token, ProductByUser productByUser) {
		log.info("received <" + productByUser + ">");
		
	}

	@Override
	public void postAccountByProductStatus(String token, AccountByProductStatus accountByProductStatus) {
		log.info("received <" + accountByProductStatus + ">");
		
	}

	@Override
	public Account[] getAccountByUserId(String token, String userId, Long accountNumber) {
		return null;
	}

	@Override
	public void init(String token) {
		
	}


}