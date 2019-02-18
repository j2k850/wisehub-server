package com.wisehub.platform.data.model.dao;

import java.util.Date;
import java.util.UUID;

import com.wisehub.platform.data.model.Account;
import com.wisehub.platform.data.model.AccountStatus;

public interface AccountRepository extends VersionCrudRepository<Account, UUID> {

	Long acountByStatus(DTOParameter dtoParameter, AccountStatus accountStatus);

	Date findFirstSale();

	Date findLastSale();

}