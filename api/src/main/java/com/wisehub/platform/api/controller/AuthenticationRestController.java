package com.wisehub.platform.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wisehub.platform.api.exception.ConstraintUsernameException;
import com.wisehub.platform.api.security.JwtAuthenticationResponse;
import com.wisehub.platform.api.security.JwtTokenUtil;
import com.wisehub.platform.api.security.JwtUser;
import com.wisehub.platform.api.service.DashboardUserCredentialService;
import com.wisehub.platform.api.view.model.DashboardRegisterViewModel;
import com.wisehub.platform.data.model.DashboardUserCredential;

import io.swagger.annotations.ApiOperation;

@RestController
public class AuthenticationRestController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private DashboardUserCredentialService dashboardUserCredentialService;

	@RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody DashboardUserCredential authenticationRequest, Device device)
			throws AuthenticationException {

		// Perform the security
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-security so we can generate token
		final UserDetails userDetails = dashboardUserCredentialService.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails, device);

		// Return the token
		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	@RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) dashboardUserCredentialService.loadUserByUsername(username);

		if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@ApiOperation(value = "Register a new dashboard User", consumes = "application/json")
	@RequestMapping(value = "${jwt.route.authentication.signin}", method = RequestMethod.POST)
	public ResponseEntity<Object> createUserCredential(@RequestBody DashboardRegisterViewModel dashboardUserCredential) {
		ResponseEntity<Object> response = null;
		try {
			dashboardUserCredentialService.post(dashboardUserCredential);
			response = new ResponseEntity<Object>("UserCredential created successfully with email :" + dashboardUserCredential.getEmail(), HttpStatus.OK);

		} catch (ConstraintUsernameException ex) {
			return new ResponseEntity<Object>("The username/email is already in use", HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}