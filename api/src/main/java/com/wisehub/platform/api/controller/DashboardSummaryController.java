package com.wisehub.platform.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wisehub.platform.api.dto.DTODashoard;
import com.wisehub.platform.api.security.JwtUser;
import com.wisehub.platform.api.service.AccountService;
import com.wisehub.platform.api.service.BranchService;
import com.wisehub.platform.api.service.CustomerService;
import com.wisehub.platform.api.service.ProductService;
import com.wisehub.platform.api.service.UserService;
import com.wisehub.platform.api.service.WeeklyIncomeByUserService;
import com.wisehub.platform.api.view.model.AccountInfoViewModel;
import com.wisehub.platform.api.view.model.BranchViewModel;
import com.wisehub.platform.api.view.model.CustomerViewModel;
import com.wisehub.platform.api.view.model.DashboardCustomerViewModel;
import com.wisehub.platform.api.view.model.ProductRecommendedViewModel;
import com.wisehub.platform.api.view.model.RevenueViewModel;
import com.wisehub.platform.data.model.DashboardUser;
import com.wisehub.platform.data.model.dao.DashboardUserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/board")
@Api(value = "API miscellaneous services to provide dashboard", produces = "application/json")
public class DashboardSummaryController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardSummaryController.class);

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

	@ApiOperation(value = "Return initially information regarding to Customer", produces = "application/json")
	@RequestMapping(value = "/customers_statistics", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Object> generateInitiallyScreen(@Validated DTODashoard dto) {
		logger.debug("GenerateCustomerScreen {} by  with id :: {}", this.getClass().getSimpleName());
		ResponseEntity<Object> response = null;
		try {
			JwtUser customerLoggedIn = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			logger.debug("GenerateCustomerScreen {}  for :: {}", this.getClass().getSimpleName(), customerLoggedIn.getEmail());

			DashboardUser user = dashboardUserRepository.findById(customerLoggedIn.getId());
			if (user == null) {
				throw new UsernameNotFoundException(String.format("No user found with username '%s'.", customerLoggedIn.getEmail()));
			}

			CustomerViewModel customerViewModel = customerService.generate(dto);

			response = new ResponseEntity<Object>(customerViewModel, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			response = new ResponseEntity<Object>(HttpStatus.OK);
		}
		return response;
	}

	@ApiOperation(value = "Return information regarding to User/Customer", produces = "application/json")
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
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

			DashboardCustomerViewModel customerViewModel = new DashboardCustomerViewModel();
			customerViewModel.setUsername(customerLoggedIn.getEmail());
			customerViewModel.setFirstname(user.getName() != null ? user.getName().getFirstName() : "NaN");
			customerViewModel.setLastname(user.getName() != null ? user.getName().getLastName() : "NaN");

			List<ProductRecommendedViewModel> recommended = productService.getRecommendedBy(customerLoggedIn.getId());
			customerViewModel.setProductRecommended(recommended);

			List<AccountInfoViewModel> accountInfo = accountService.getInfoBy(customerLoggedIn.getId());
			customerViewModel.setAccountInfo(accountInfo);

			BranchViewModel branch = branchService.getBranchBy(customerLoggedIn.getId());
			customerViewModel.setBranch(branch);

			RevenueViewModel revenue = weeklyIncomeByUserService.getRevenue(customerLoggedIn.getId());
			customerViewModel.setRevenue(revenue);

			response = new ResponseEntity<Object>(customerViewModel, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			response = new ResponseEntity<Object>(HttpStatus.OK);
		}
		return response;
	}

}