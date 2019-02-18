package com.wisehub.platform.data.model.dao;

import java.util.UUID;

import com.wisehub.platform.data.model.User;

public interface UserRepository extends VersionCrudRepository<User, UUID> {

	Long count();

}