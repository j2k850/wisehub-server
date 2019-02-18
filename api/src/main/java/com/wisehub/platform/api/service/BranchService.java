package com.wisehub.platform.api.service;

import java.util.UUID;

import com.wisehub.platform.api.view.model.BranchViewModel;
import com.wisehub.platform.data.model.Branch;

public interface BranchService extends VersionCrudService<Branch,UUID> {

	BranchViewModel getBranchBy(UUID id);

}
