package com.wisehub.platform.api.service;

import java.util.UUID;

import com.wisehub.platform.data.model.User;

public interface UserService extends VersionCrudService<User,UUID> {

	Long count();

}
