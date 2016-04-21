package io.sheetal.service;

import io.sheetal.entity.User;
import io.sheetal.entity.UserRole;
import io.sheetal.exception.UserAlreadyExistsException;
import io.sheetal.exception.UserNotFoundException;

import java.util.List;
import java.util.Set;

public interface UserService {
	
	public List<User> findAllUsers();
	public User findOne(String id) throws UserNotFoundException;
	public User findByUserName(User user) throws UserNotFoundException;
	public User create(User user) throws UserAlreadyExistsException;
	public User update(User user) throws UserNotFoundException;
	public void delete(String id) throws UserNotFoundException;
	public void checkPermission(Set<UserRole> authorities);
	
}
