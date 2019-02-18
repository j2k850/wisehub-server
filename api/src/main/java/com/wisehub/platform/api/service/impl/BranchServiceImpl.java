package com.wisehub.platform.api.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.BranchService;
import com.wisehub.platform.api.view.model.BranchViewModel;
import com.wisehub.platform.data.model.Branch;
import com.wisehub.platform.data.model.dao.BranchRepository;
import com.wisehub.platform.data.model.dao.VersionCrudRepository;

@Service
public class BranchServiceImpl extends AbstractVersionCrudService<Branch> implements BranchService {

	private static Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class.getName());

	@Autowired
	BranchRepository branchRepository;

	@Override
	protected VersionCrudRepository<Branch, UUID> getRepository() {
		return branchRepository;
	}

	@Override
	public BranchViewModel getBranchBy(UUID id) {
		/**Fake Product Recommeded"**/

		long highbits = id.getMostSignificantBits();
		
		BranchViewModel branchViewModel = new BranchViewModel();
		
		if(highbits%2==0){
			branchViewModel.setFirstname("George");
			branchViewModel.setLastname("Weah");
			branchViewModel.setSubject("Sales Rep");
			branchViewModel.setBank("Big Bank");
		}
		
		if(highbits%3==0){
			branchViewModel.setFirstname("Roger");
			branchViewModel.setLastname("Milla");
			branchViewModel.setSubject("Sales Rep");
			branchViewModel.setBank("Small Bank");
		}
		if(highbits%5==0){
			branchViewModel.setFirstname("Didier");
			branchViewModel.setLastname("Drogba");
			branchViewModel.setSubject("Sales Rep");
			branchViewModel.setBank("Medium Bank");

		}
		
		return branchViewModel;
	}

}
