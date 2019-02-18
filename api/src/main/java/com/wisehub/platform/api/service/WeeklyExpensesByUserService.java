package com.wisehub.platform.api.service;

import java.util.UUID;

import com.wisehub.platform.api.view.model.ExpensesViewModel;
import com.wisehub.platform.data.model.WeeklyExpensesByUser;

public interface WeeklyExpensesByUserService extends CrudService<WeeklyExpensesByUser,String> {
	
	ExpensesViewModel getExpenses(UUID id);

}

