package com.wisehub.platform.api.service;

import com.wisehub.platform.api.dto.DTODashoard;
import com.wisehub.platform.api.view.model.CustomerViewModel;
import com.wisehub.platform.api.view.model.DashboardCustomerProfileViewModel;
import com.wisehub.platform.data.model.DashboardUser;

public interface CustomerService {

	CustomerViewModel generate(DTODashoard builder);

	DashboardCustomerProfileViewModel getProfile(DashboardUser user);

}
