package io.sheetal.repository;

import java.util.List;

import io.sheetal.entity.User;

public interface UserRepository {

	public List<User> findAllUsers();
	public User findOne(String id);
	public User findByEmail(String email);
	public User create(User user);
	public User update(User user);
	public void delete(User user);
}
