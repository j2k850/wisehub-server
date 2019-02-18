package com.wisehub.platform.core.orm.dao;

import java.util.List;

import com.wisehub.platform.core.orm.entity.Account;
import com.wisehub.platform.core.orm.entity.AccountStatus;

public interface AccountDAO extends DAO {

 List<Account> findAllByAccountKey(String accountKey, List<AccountStatus> statuses); 

}
