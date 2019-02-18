package com.wisehub.platform.api.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.wisehub.platform.api.view.model.DashboardRegisterViewModel;
import com.wisehub.platform.data.model.DashboardUserCredential;

public interface DashboardUserCredentialService extends CrudService<DashboardUserCredential,String>, UserDetailsService {

	void post(DashboardRegisterViewModel dashboardUserCredential);

}
