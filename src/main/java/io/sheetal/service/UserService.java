package io.sheetal.service;

import io.sheetal.entity.User;
import io.sheetal.exception.UserAlreadyExistsException;
import io.sheetal.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
	
	public List<User> findAllUsers();
	public User findOne(String id) throws UserNotFoundException;
	public User create(User user) throws UserAlreadyExistsException;
	public User update(User user) throws UserNotFoundException;
	public void delete(String id) throws UserNotFoundException;
	
}
