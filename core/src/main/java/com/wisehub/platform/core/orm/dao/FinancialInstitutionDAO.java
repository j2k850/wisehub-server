package com.wisehub.platform.core.orm.dao;

import com.wisehub.platform.core.orm.entity.FinancialInstitution;
import com.wisehub.platform.core.orm.entity.UserFinancialInstitution;

public interface FinancialInstitutionDAO {

	public void save(FinancialInstitution f);

	public UserFinancialInstitution getCredentials(Integer userId, Integer financialInstitutionId);

}
