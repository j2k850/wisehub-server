package com.wisehub.platform.api.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.WeeklyExpensesByUserService;
import com.wisehub.platform.api.view.model.ExpensesViewModel;
import com.wisehub.platform.api.view.model.LoanViewModel;
import com.wisehub.platform.data.model.WeeklyExpensesByUser;
import com.wisehub.platform.data.model.dao.CrudRepository;
import com.wisehub.platform.data.model.dao.WeeklyExpensesByUserRepository;

@Service
public class WeeklyExpensesByUserServiceImpl extends AbstractCrudService<WeeklyExpensesByUser, String> implements WeeklyExpensesByUserService {

	private static Logger logger = LoggerFactory.getLogger(WeeklyExpensesByUserServiceImpl.class.getName());

	@Autowired
	WeeklyExpensesByUserRepository repository;

	@Override
	protected CrudRepository<WeeklyExpensesByUser, String> getRepository() {
		return repository;
	}
	
	public ExpensesViewModel getExpenses(UUID id) {
		/** Fake ExpensesViewModel **/

		long highbits = id.getMostSignificantBits();
		double loansTotal = 2000d;
		double spent = 1000d;
		
		if (highbits % 2 == 0) {
			loansTotal = loansTotal * 5.32;
			spent = spent * 5.32;	
		}

		if (highbits % 3 == 0) {
			loansTotal = loansTotal * 2.53;
			spent = spent * 2.53;
		}
		
		if (highbits % 5 == 0) {
			loansTotal = loansTotal * 3.25;
			spent = spent * 3.25;
		}
		
		if (highbits % 6 == 0) {
			loansTotal = loansTotal *  6.33;
			spent = spent * 6.33;
		}
		
		if (highbits % 10 == 0) {
			loansTotal = loansTotal *  1.73;
			spent = 	spent * 1.73;
		}
		
		ExpensesViewModel expenses = new ExpensesViewModel();
		expenses.setCurrency("NGN");
		expenses.setLoansTotal(new BigDecimal(loansTotal));
		expenses.setTotalSpent(new BigDecimal(spent));
		expenses.setLoanViewModels(getLoanViewModel(loansTotal));
		
		return expenses;
	}
	

	private List<LoanViewModel> getLoanViewModel(double loansTotal){
		
		List<LoanViewModel> loanViewModels = new ArrayList<LoanViewModel>();
		LoanViewModel loan1 = new LoanViewModel();
		loan1.setName("Loan 1");
		loan1.setVal(new BigDecimal(loansTotal * .45));
		
		LoanViewModel loan2 = new LoanViewModel();
		loan2.setName("Loan 2");
		loan2.setVal(new BigDecimal(loansTotal * .55));
		
		loanViewModels.add(loan1);
		loanViewModels.add(loan2);
		
		return loanViewModels;
	}
}
