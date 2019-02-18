package com.wisehub.platform.data.model.dao;

import java.util.UUID;

import com.wisehub.platform.data.model.Transaction;

public interface TransactionRepository extends VersionCrudRepository<Transaction, UUID> {

	Transaction findLast();


}