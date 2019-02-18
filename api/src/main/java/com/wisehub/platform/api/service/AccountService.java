package com.wisehub.platform.api.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.wisehub.platform.api.view.model.AccountInfoViewModel;
import com.wisehub.platform.data.model.Account;
import com.wisehub.platform.data.model.AccountStatus;

public interface AccountService extends VersionCrudService<Account, UUID> {

	List<AccountInfoViewModel> getInfoBy(UUID id);

	Long countByStatus(AccountStatus pending);

	Date findFirstSale();

	Date findLastSale();

}
