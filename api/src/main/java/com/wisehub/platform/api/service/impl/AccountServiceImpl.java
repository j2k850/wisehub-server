package com.wisehub.platform.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.AccountService;
import com.wisehub.platform.api.view.model.AccountByProductViewModel;
import com.wisehub.platform.api.view.model.AccountInfoViewModel;
import com.wisehub.platform.data.model.Account;
import com.wisehub.platform.data.model.AccountStatus;
import com.wisehub.platform.data.model.DashboardUser;
import com.wisehub.platform.data.model.ProductStatus;
import com.wisehub.platform.data.model.dao.AccountByProductStatusRepository;
import com.wisehub.platform.data.model.dao.AccountRepository;
import com.wisehub.platform.data.model.dao.DTOParameter;
import com.wisehub.platform.data.model.dao.VersionCrudRepository;

@Service
public class AccountServiceImpl extends AbstractVersionCrudService<Account> implements AccountService {

	private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class.getName());

	@Autowired
	AccountRepository accountRepository;
	
	
	@Autowired
	AccountByProductStatusRepository accountByProductStatusRepository;

	@Override
	protected VersionCrudRepository<Account, UUID> getRepository() {
		return accountRepository;
	}

	@Override
	public List<AccountInfoViewModel> getInfoBy(UUID id) {
		/**Fake Product Recommeded"**/

		long highbits = id.getMostSignificantBits();
		
		List<AccountInfoViewModel> list = new ArrayList<>();
		
		if(highbits%2==0){
			AccountInfoViewModel accountInfoViewModel = new AccountInfoViewModel();
			accountInfoViewModel.setBankName("Big Bank");
			accountInfoViewModel.setProductName("Product Big Bank 1");
			accountInfoViewModel.setStatus("Pending");
			list.add(accountInfoViewModel);
		}
		
		if(highbits%3==0){
			AccountInfoViewModel accountInfoViewModel = new AccountInfoViewModel();
			accountInfoViewModel.setBankName("Small Bank");
			accountInfoViewModel.setProductName("Product Small Bank 2");
			accountInfoViewModel.setStatus("In Progress");
			list.add(accountInfoViewModel);
		}
		if(highbits%5==0){
			AccountInfoViewModel accountInfoViewModel = new AccountInfoViewModel();
			accountInfoViewModel.setBankName("Medium Bank");
			accountInfoViewModel.setProductName("Product Medium Bank 3");
			accountInfoViewModel.setStatus("Opened");
			list.add(accountInfoViewModel);
		}
		
		return list;
	}
	
	
	public AccountByProductViewModel getProfile(DashboardUser user) {
		AccountByProductViewModel vm = new AccountByProductViewModel();
		
		accountByProductStatusRepository.acountByStatus(new DTOParameter(), ProductStatus.RECOMMENDED.getDescription());
		
		
		return vm;
	}

	@Override
	public Long countByStatus(AccountStatus accountStatus) {
		return accountRepository.acountByStatus(new DTOParameter(), accountStatus);
	}

	@Override
	public Date findFirstSale() {
		return accountRepository.findFirstSale();
	}

	@Override
	public Date findLastSale() {
		return accountRepository.findLastSale();

	}

}
