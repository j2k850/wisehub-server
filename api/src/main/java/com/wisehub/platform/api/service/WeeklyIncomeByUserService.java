package com.wisehub.platform.api.service;

import java.util.UUID;

import com.wisehub.platform.api.view.model.RevenueViewModel;
import com.wisehub.platform.data.model.WeeklyIncomeByUser;

public interface WeeklyIncomeByUserService extends CrudService<WeeklyIncomeByUser,String> {

	RevenueViewModel getRevenue(UUID id);

}

