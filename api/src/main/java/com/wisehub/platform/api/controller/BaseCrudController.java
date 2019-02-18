package com.wisehub.platform.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wisehub.platform.api.exception.InvalidTxException;
import com.wisehub.platform.api.service.CrudService;
import com.wisehub.platform.data.model.dao.DTOParameter;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

public abstract class BaseCrudController<M,ID> {

	private static final Logger logger = LoggerFactory.getLogger(BaseCrudController.class);

	private static final String NO_RECORDS = "Resources not found  ";

	private static final String NO_RECORD = "Resource not found for id : ";

	public abstract CrudService<M, ID> getService();

	@ApiOperation(value = "Get All resources", produces = "application/json")
	@RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Object> get(@Validated DTOParameter dtoParameter) {
		logger.debug("Get All ::");
		List<M> models = null;
		ResponseEntity<Object> response = null;
		try {
			models = getService().getAll(dtoParameter);
			if (models == null) {
				response = new ResponseEntity<Object>(NO_RECORDS, HttpStatus.OK);
			} else {
				response = new ResponseEntity<Object>(models, HttpStatus.OK);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@ApiOperation(value = "Search resource by id", produces = "application/json")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> searchById(@ApiParam(name = "id", required = true) @PathVariable ID id) {
		logger.debug("Searching {} by  with id :: {}", this.getClass().getSimpleName(), id);
		M model = null;
		ResponseEntity<Object> response = null;
		try {
			model = getService().get(id);
			response = new ResponseEntity<Object>(model, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			response = new ResponseEntity<Object>(NO_RECORD + id, HttpStatus.OK);
		}
		return response;
	}

	@ApiOperation(value = "Create a new resource", consumes = "application/json")
	@RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> create(@RequestBody M model) {
		logger.error("Creating resource with id :: {}", model.toString());
		ResponseEntity<Object> response = null;
		try {
			getService().post(model);
			response = new ResponseEntity<Object>("M created successfully", HttpStatus.OK);
		} catch (InvalidTxException ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@ApiOperation(value = "Update a resource by id", consumes = "application/json")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> update(@ApiParam(name = "id", value = "The id of the resource to be viewed", required = true) @PathVariable ID id,
			@RequestBody M model) {
		logger.error("Creating resource with name :: {}", model.toString());
		ResponseEntity<Object> response = null;
		try {
			getService().put(id, model);
			response = new ResponseEntity<Object>("M updated successfully with Id :" + id, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@ApiOperation(value = "Delete a resource", consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@ApiParam(name = "id", value = "Resource id to delete", required = true) @PathVariable(name = "id") ID id) {
		logger.debug("Deleting resource with id :: {}", id);
		ResponseEntity<Object> response = null;
		try {
			getService().delete(id);
			response = new ResponseEntity<Object>("M deleted successfully with Id :" + id, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}