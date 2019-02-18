package com.wisehub.platform.api.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.WeeklyIncomeByUserService;
import com.wisehub.platform.api.view.model.CashViewModel;
import com.wisehub.platform.api.view.model.InvestmentViewModel;
import com.wisehub.platform.api.view.model.RevenueViewModel;
import com.wisehub.platform.data.model.WeeklyIncomeByUser;
import com.wisehub.platform.data.model.dao.CrudRepository;
import com.wisehub.platform.data.model.dao.WeeklyIncomeByUserRepository;

@Service
public class WeeklyIncomeByUserServiceImpl extends AbstractCrudService<WeeklyIncomeByUser, String> implements WeeklyIncomeByUserService {

	private static Logger logger = LoggerFactory.getLogger(WeeklyIncomeByUserServiceImpl.class.getName());

	@Autowired
	WeeklyIncomeByUserRepository repository;

	@Override
	protected CrudRepository<WeeklyIncomeByUser, String> getRepository() {
		return repository;
	}

	@Override
	public RevenueViewModel getRevenue(UUID id) {
		/** Fake RevenueViewModel **/

		long highbits = id.getMostSignificantBits();
		double cashTotal = 2000d;
		double investmentsTotal = 1000d;
		
		if (highbits % 2 == 0) {
			cashTotal = cashTotal * 5.32;
			investmentsTotal = investmentsTotal * 5.32;	
		}

		if (highbits % 3 == 0) {
			cashTotal = cashTotal * 2.53;
			investmentsTotal = investmentsTotal * 2.53;
		}
		
		if (highbits % 5 == 0) {
			cashTotal = cashTotal * 3.25;
			investmentsTotal = investmentsTotal * 3.25;
		}
		
		if (highbits % 6 == 0) {
			cashTotal = cashTotal *  6.33;
			investmentsTotal = investmentsTotal * 6.33;
		}
		
		if (highbits % 10 == 0) {
			cashTotal = cashTotal *  1.73;
			investmentsTotal = investmentsTotal * 1.73;
		}
		
		RevenueViewModel revenue = new RevenueViewModel();
		revenue.setCurrency("NGN");
		revenue.setInvestmentsTotal(new BigDecimal(investmentsTotal));
		revenue.setCashTotal(new BigDecimal(cashTotal));
		revenue.setCashModels(getCashViewModel(cashTotal));
		revenue.setInvestmentModels(getInvestmentViewModel(investmentsTotal));

		return revenue;
	}
	
	private List<CashViewModel> getCashViewModel(double cashTotal){
		
		List<CashViewModel> cashViewModels = new ArrayList<CashViewModel>();
		CashViewModel savings = new CashViewModel();
		savings.setName("Savings");
		savings.setVal(new BigDecimal(cashTotal * .15));
		
		CashViewModel checking = new CashViewModel();
		checking.setName("Checking");
		checking.setVal(new BigDecimal(cashTotal * .85));
		
		cashViewModels.add(savings);
		cashViewModels.add(checking);
		
		return cashViewModels;
	}
	
	private List<InvestmentViewModel> getInvestmentViewModel(double investmentsTotal){
		
		List<InvestmentViewModel> investmentViewModels = new ArrayList<InvestmentViewModel>();
		InvestmentViewModel highRate = new InvestmentViewModel();
		highRate.setName("High Rate Investment");
		highRate.setVal(new BigDecimal(investmentsTotal * .65));
		
		InvestmentViewModel mediumRate = new InvestmentViewModel();
		mediumRate.setName("Medium Rate Investment");
		mediumRate.setVal(new BigDecimal(investmentsTotal * .35));
		
		investmentViewModels.add(highRate);
		investmentViewModels.add(mediumRate);
		
		return investmentViewModels;
	}

}
