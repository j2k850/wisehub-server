package com.wisehub.platform.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wisehub.platform.api.service.UserCredentialService;
import com.wisehub.platform.data.model.UserCredential;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/userCredentials")
@Api(value = "API to perform CRUD operation in a UserCredential database maintained in apache cassandra", description = "This API provides the capability to perform CRUD operation in a UserCredential "
		+ "database maintained in apache cassandra", produces = "application/json")
public class UserCredentialController {

	private static final Logger logger = LoggerFactory.getLogger(UserCredentialController.class);

	@Autowired
	private UserCredentialService userCredentialService;

	private static final String NO_RECORDS = "UserCredentials not found  ";

	private static final String NO_RECORD = "UserCredential not found for email : ";

	@ApiOperation(value = "Get All UserCredentials", produces = "application/json")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> get() {
		logger.debug("Get All userCredentials ::");
		List<UserCredential> userCredential = null;
		ResponseEntity<Object> response = null;
		try {
			userCredential = userCredentialService.getAll(null);
			if (userCredential == null) {
				response = new ResponseEntity<Object>(NO_RECORDS, HttpStatus.OK);
			} else {
				response = new ResponseEntity<Object>(userCredential, HttpStatus.OK);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@ApiOperation(value = "Search UserCredential by email", produces = "application/json")
	@RequestMapping(value = "/{userCredentialId}", method = RequestMethod.GET)
	public ResponseEntity<Object> searchById(
			@ApiParam(name = "email", value = "The email of the UserCredential to be viewed", required = true) @PathVariable String email) {
		logger.debug("Searching for userCredential with userCredentialId :: {}", email);
		UserCredential userCredential = null;
		ResponseEntity<Object> response = null;
		try {
			userCredential = userCredentialService.get(email);
			response = new ResponseEntity<Object>(userCredential, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			response = new ResponseEntity<Object>(NO_RECORD + email, HttpStatus.OK);
		}
		return response;
	}

	@ApiOperation(value = "Create a new UserCredential", consumes = "application/json")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createUserCredential(@RequestBody UserCredential userCredential) {
		logger.error("Creating UserCredential with email :: {}", userCredential.toString());
		ResponseEntity<Object> response = null;
		try {
			userCredentialService.post(userCredential);
			response = new ResponseEntity<Object>("UserCredential created successfully with email :" + userCredential.getEmail(), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@ApiOperation(value = "Update a UserCredential", consumes = "application/json")
	@RequestMapping(value = "/{email}", method = RequestMethod.PUT)
	public ResponseEntity<Object> createUserCredential(
			@ApiParam(name = "email", value = "The email of the UserCredential to be viewed", required = true) @PathVariable String email,
			@RequestBody UserCredential userCredential) {
		logger.error("Creating UserCredential with name :: {}", email.toString());
		ResponseEntity<Object> response = null;
		try {
			userCredentialService.put(email, userCredential);
			response = new ResponseEntity<Object>("UserCredential updated successfully with Id :" + email, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@ApiOperation(value = "Delete a UserCredential Object from Database", consumes = "application/json")
	@RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@ApiParam(name = "email", value = "Resource email to delete", required = true) @PathVariable String email) {
		logger.debug("Deleting UserCredential with email :: {}", email);
		ResponseEntity<Object> response = null;
		try {
			userCredentialService.delete(email);
			response = new ResponseEntity<Object>("UserCredential deleted successfully with Id :" + email, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}