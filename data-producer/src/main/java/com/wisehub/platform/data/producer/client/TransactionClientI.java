package com.wisehub.platform.data.producer.client;

import com.wisehub.platform.data.model.Account;
import com.wisehub.platform.data.model.AccountByProductStatus;
import com.wisehub.platform.data.model.ProductByUser;
import com.wisehub.platform.data.model.User;

public interface TransactionClientI {

	void postSaleAccount(String token, Account account);

	void postCustomer(String token, User user);

	void postTxFromCSV(String token, String content);

	void postRegister(DashboardRegisterViewModel viewModel);

	String postAuth(DashboardRegisterViewModel viewModel);

	User[] getByBnv(String token, String bvn);

	void postProductByUser(String token, ProductByUser productByUser);

	void postAccountByProductStatus(String token, AccountByProductStatus accountByProductStatus);

	Account[] getAccountByUserId(String token, String userId, Long accountNumber);

	void init(String token);


}