package io.sheetal.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.sheetal.entity.User;
import io.sheetal.exception.UserAlreadyExistsException;
import io.sheetal.exception.UserNotFoundException;
import io.sheetal.service.UserDeatilsServiceImpl;
import io.sheetal.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/users")
@Api(tags="users")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserDeatilsServiceImpl detailService;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Find all users", notes="returns a list of users")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=500, message="internal server error")})
	public List<User> findAllUsers() {
		return service.findAllUsers();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Find one user by userId", notes="returns an user")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="user not found"),
			@ApiResponse(code=500, message="internal server error")})
	public User findOne(@PathVariable("id")String id) throws UserNotFoundException {
		return service.findOne(id);
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Find one user by name", notes="returns an user")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="user not found"),
			@ApiResponse(code=500, message="internal server error")})
	public void findByUserName(@RequestBody User user) throws UserNotFoundException {
		User existing=service.findByUserName(user);
		service.checkPermission(existing.getUserRoles());		
	}	

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Create user", notes="creates an user. Requires unique email")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=400, message="bad request"),
			@ApiResponse(code=500, message="internal server error")})
	public User create(@RequestBody User user) throws UserAlreadyExistsException {
		return service.create(user);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Update user", notes="Updates an user")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=400, message="bad request"),
			@ApiResponse(code=404, message="user not found"),
			@ApiResponse(code=500, message="internal server error")})
	public User update(@PathVariable("id") String id, @RequestBody User user) throws UserNotFoundException {
		return service.update(user);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Delete user", notes="deletes an user")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=400, message="bad request"),
			@ApiResponse(code=404, message="user not found"),
			@ApiResponse(code=500, message="internal server error")})	
	public void delete(@PathVariable("id") String id) throws UserNotFoundException {
		service.delete(id);
	}

}
