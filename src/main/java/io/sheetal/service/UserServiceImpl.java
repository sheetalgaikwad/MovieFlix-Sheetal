package io.sheetal.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sheetal.entity.User;
import io.sheetal.entity.UserRole;
import io.sheetal.exception.UserAlreadyExistsException;
import io.sheetal.exception.UserNotFoundException;
import io.sheetal.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired 	
	private UserRepository repository;
	
	@Override
	public List<User> findAllUsers() {
		return repository.findAllUsers();
	}

	@Override
	public User findOne(String id) throws UserNotFoundException {
		User user= repository.findOne(id);
		if(user==null){
			throw new UserNotFoundException();
		}
		else{
			return user;
		}
			
	}

	@Override
	public User create(User user) throws UserAlreadyExistsException {
		User existing=repository.findByEmail(user.getEmail());
		if(existing!=null){
			throw new UserAlreadyExistsException();
		}
		else{
			return repository.create(user);
		}
	}

	@Override
	public User update(User user) throws UserNotFoundException {
		User existing=repository.findOne(user.getId());
		if(existing==null){
			throw new UserNotFoundException();			
		}
		else{
			return repository.update(user);
		}
	}

	@Override
	public void delete(String id) throws UserNotFoundException {
		User existing=repository.findOne(id);
		if(existing==null){
			throw new UserNotFoundException();		
		} 
		else{
			repository.delete(existing);
		}
	}

	@Override
	public User findByUserName(User user) throws UserNotFoundException {
		User existing=repository.findByUserName(user.getUsername());
		if(existing==null){
			throw new UserNotFoundException();		
		} 
		else
			return existing;
	}
	
	public void checkPermission(Set<UserRole> authorities)
	{		
		for(UserRole userRole:authorities){
			if(userRole.getRole().trim().equals("admin"))
				System.out.println("Permitted!");
		else
			System.out.println("Permission denied!");
		}
	}

}
