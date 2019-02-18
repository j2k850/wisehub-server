package com.wisehub.platform.api.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wisehub.platform.api.security.JwtUser;
import com.wisehub.platform.api.service.AccountService;
import com.wisehub.platform.api.service.BranchService;
import com.wisehub.platform.api.service.CrudService;
import com.wisehub.platform.api.service.CustomerService;
import com.wisehub.platform.api.service.DashboardUserService;
import com.wisehub.platform.api.service.ProductService;
import com.wisehub.platform.api.service.WeeklyIncomeByUserService;
import com.wisehub.platform.api.view.model.CustomerViewModel;
import com.wisehub.platform.api.view.model.DashboardCustomerProfileViewModel;
import com.wisehub.platform.api.view.model.DashboardCustomerViewModel;
import com.wisehub.platform.data.model.DashboardUser;
import com.wisehub.platform.data.model.dao.DashboardUserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/dashboard_users")
@Api(value = "API to perform CRUD operation in a Dashboard User database maintained in apache cassandra", description = "This API provides the capability to perform CRUD operation in a Dashboard User "
		+ "database maintained in apache cassandra", produces = "application/json")
public class DashboardUserController extends BaseCrudController<DashboardUser, UUID> {

	private static final Logger logger = LoggerFactory.getLogger(DashboardUserController.class);

	@Autowired
	private DashboardUserService service;

	@Override
	public CrudService<DashboardUser, UUID> getService() {
		return service;
	}
	
	@Autowired
	private ProductService productService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private BranchService branchService;

	@Autowired
	private WeeklyIncomeByUserService weeklyIncomeByUserService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private DashboardUserRepository dashboardUserRepository;


	@ApiOperation(value = "Return profile of Customer", produces = "application/json")
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Object> generateCustomerScreen() {
		logger.debug("GenerateCustomerScreen {} by  with id :: {}", this.getClass().getSimpleName());
		ResponseEntity<Object> response = null;
		try {
			JwtUser customerLoggedIn = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			logger.debug("GenerateCustomerScreen {}  for :: {}", this.getClass().getSimpleName(), customerLoggedIn.getEmail());

			DashboardUser user = dashboardUserRepository.findById(customerLoggedIn.getId());
			if (user == null) {
				throw new UsernameNotFoundException(String.format("No user found with username '%s'.", customerLoggedIn.getEmail()));
			}

			DashboardCustomerProfileViewModel model = customerService.getProfile(user);
			
			response = new ResponseEntity<Object>(model, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			response = new ResponseEntity<Object>(HttpStatus.OK);
		}
		return response;
	}

}