package io.sheetal.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.sheetal.entity.User;
import io.sheetal.entity.UserRole;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<User> findAllUsers() {
		TypedQuery<User> query=em.createQuery("SELECT u from User u ORDER BY u.email ASC",User.class);
		List<User> userList=query.getResultList();
		return userList;
	}

	@Override
	public User findOne(String id) {
		return em.find(User.class, id);
	}	

	@Override
	public User findByEmail(String email) {
		TypedQuery<User> query=em.createQuery("SELECT u from User u where u.email=:email",User.class);
		query.setParameter("email",email);
		List<User> userList=query.getResultList();
		if(userList!=null && userList.size()==1)
			return userList.get(0);
		else
			return null;
	}
	
	@Override
	public User findByUserName(String userName) {
		TypedQuery<User> query=em.createQuery("SELECT u from User u where u.username=:username",User.class);
		query.setParameter("username",userName);
		List<User> userList=query.getResultList();
		if(userList!=null && userList.size()==1)
			return userList.get(0);
		else
			return null;
	}
	
	@Override
	public User create(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public User update(User user) {
		return em.merge(user);
	}

	@Override
	public void delete(User user) {
		em.remove(user);
		
	}

	

	


}
