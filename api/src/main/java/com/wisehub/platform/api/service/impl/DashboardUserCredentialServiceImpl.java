package com.wisehub.platform.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.exception.ConstraintUsernameException;
import com.wisehub.platform.api.security.JwtUserFactory;
import com.wisehub.platform.api.service.DashboardUserCredentialService;
import com.wisehub.platform.api.service.DashboardUserService;
import com.wisehub.platform.api.view.model.DashboardRegisterViewModel;
import com.wisehub.platform.data.model.DashboardRole;
import com.wisehub.platform.data.model.DashboardUser;
import com.wisehub.platform.data.model.DashboardUserCredential;
import com.wisehub.platform.data.model.DashboardUserRole;
import com.wisehub.platform.data.model.UDTFullName;
import com.wisehub.platform.data.model.dao.CrudRepository;
import com.wisehub.platform.data.model.dao.DTOParameter;
import com.wisehub.platform.data.model.dao.DashboardRoleRepository;
import com.wisehub.platform.data.model.dao.DashboardUserCredentialRepository;
import com.wisehub.platform.data.model.dao.DashboardUserRepository;
import com.wisehub.platform.data.model.dao.DashboardUserRoleRepository;

@Service
public class DashboardUserCredentialServiceImpl extends AbstractCrudService<DashboardUserCredential, String> implements DashboardUserCredentialService {

	private static Logger logger = LoggerFactory.getLogger(DashboardUserCredentialServiceImpl.class.getName());

	@Autowired
	private DashboardUserCredentialRepository dashboardUserCredentialRepository;

	@Autowired
	private DashboardUserRepository dashboardUserRepository;
	
	@Autowired
	private DashboardUserService dashboardUserService;

	@Autowired
	private DashboardUserRoleRepository dashboardUserRoleRepository;
	
	@Autowired
	private DashboardRoleRepository dashboardRoleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected CrudRepository<DashboardUserCredential, String> getRepository() {
		return dashboardUserCredentialRepository;
	}
	
	@Override
	protected void beforePost(DashboardUserCredential model) {
		
		if (dashboardUserCredentialRepository.findByEmail(model.getEmail()) != null){
			throw new ConstraintUsernameException();
		}
		
		DashboardUser dashboardUser = new DashboardUser();
		dashboardUser.setEmail(model.getEmail());
		dashboardUserService.post(dashboardUser);	
		
		if (model.getUserId() == null){
			model.setUserId(dashboardUser.getId());
		}
		
		String encode = passwordEncoder.encode(model.getPassword());
		model.setPassword(encode);
	}
	
	@Override
	protected void afterPost(DashboardUserCredential model) {
		DashboardRole roleUser = dashboardRoleRepository.findByRoleName("USER");
		DashboardRole roleUser1 = dashboardRoleRepository.findByRoleName("ADMIN");
		
		DashboardUserRole dashboardUserRole = new DashboardUserRole();
		dashboardUserRole.setDashboardUserId(model.getUserId());
		dashboardUserRole.setDashboardRoleId(roleUser.getId());

		dashboardUserRoleRepository.save(dashboardUserRole);
		
		dashboardUserRole = new DashboardUserRole();
		dashboardUserRole.setDashboardUserId(model.getUserId());
		dashboardUserRole.setDashboardRoleId(roleUser1.getId());
		dashboardUserRoleRepository.save(dashboardUserRole);
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		DashboardUserCredential userCredential = dashboardUserCredentialRepository.findById(username);
		if (userCredential == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}

		DashboardUser dashboardUser = dashboardUserRepository.findById(userCredential.getUserId());
		if (dashboardUser == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}

		DTOParameter dtoParameter = new DTOParameter();
		dtoParameter.setUserId(dashboardUser.getId().toString());
		List<DashboardUserRole> userRoles = dashboardUserRoleRepository.findAll(dtoParameter);
		List<DashboardRole> roles = dashboardRoleRepository.findsByRolesId(userRoles);

		return JwtUserFactory.create(dashboardUser, roles, userCredential);

	}

	@Override
	public void post(DashboardRegisterViewModel registerViewModel) {
		
		DashboardUserCredential userCrdential = new DashboardUserCredential();
		
		userCrdential.setEmail(registerViewModel.getEmail());
		userCrdential.setPassword(registerViewModel.getPassword());

		
		if (dashboardUserCredentialRepository.findByEmail(registerViewModel.getEmail()) != null){
			throw new ConstraintUsernameException();
		}
		
		DashboardUser dashboardUser = new DashboardUser();
		dashboardUser.setEmail(registerViewModel.getEmail());
		
		UDTFullName name = new UDTFullName();
		name.setFirstName(registerViewModel.getUsername());
		dashboardUser.setName(name);
		
		dashboardUserService.post(dashboardUser);	
		
		userCrdential.setUserId(dashboardUser.getId());
		
		String encode = passwordEncoder.encode(registerViewModel.getPassword());
		userCrdential.setPassword(encode);
	
		dashboardUserCredentialRepository.save(userCrdential);
		
		//Asing roles
		DashboardRole roleUser = dashboardRoleRepository.findByRoleName("USER");
		DashboardRole roleUser1 = dashboardRoleRepository.findByRoleName("ADMIN");
		
		DashboardUserRole dashboardUserRole = new DashboardUserRole();
		dashboardUserRole.setDashboardUserId(userCrdential.getUserId());
		dashboardUserRole.setDashboardRoleId(roleUser.getId());

		dashboardUserRoleRepository.save(dashboardUserRole);
		
		dashboardUserRole = new DashboardUserRole();
		dashboardUserRole.setDashboardUserId(userCrdential.getUserId());
		dashboardUserRole.setDashboardRoleId(roleUser1.getId());
		dashboardUserRoleRepository.save(dashboardUserRole);
		
	}

}
