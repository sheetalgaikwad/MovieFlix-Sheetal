package io.sheetal.test.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.sheetal.entity.User;
import io.sheetal.repository.UserRepository;
import io.sheetal.repository.UserRepositoryImpl;
import io.sheetal.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class UserRepositoryTest {
	
	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<User> query;
	
	@InjectMocks
	private UserRepository repository=new UserRepositoryImpl();
	
	private User user;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		user=new User();
		user.setFirstName("john");
		user.setLastName("doe");
		user.setEmail("johnd@example.com");
		user.setId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllusers()
	{
		List<User> expected=Arrays.asList(user);
		
		Mockito.when(em.createQuery("SELECT u from User u ORDER BY u.email ASC",User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		List<User> actual=repository.findAllUsers();
		Assert.assertEquals(expected, actual);
		//Mockito.verify(query).getResultList();
	}
	
	@Test
	public void testFindOne()
	{
		Mockito.when(em.find(User.class, user.getId())).thenReturn(user);
		User actual=repository.findOne(user.getId());
		Assert.assertEquals(user, actual);
	}
	
	@Test
	public void testFindByEmail()
	{
		List<User> expected=Arrays.asList(user);
		Mockito.when(em.createQuery("SELECT u from User u where u.email=:email",User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		User actual=repository.findByEmail(user.getEmail());
		Assert.assertEquals(user, actual);
	}
	
	@Test
	public void testFindByEmailNUll()
	{
		Mockito.when(em.createQuery("SELECT u from User u where u.email=:email",User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		User actual=repository.findByEmail(user.getEmail());
		Assert.assertEquals(null, actual);
	}
	
	@Test
	public void testCreate()
	{
		repository.create(user);
		Mockito.verify(em).persist(user);
	}
	
	@Test
	public void testUpdateUser()
	{
		repository.update(user);
		Mockito.verify(em).merge(user);
	}
	
	@Test
	public void testDeleteUser()
	{
		repository.delete(user);
		Mockito.verify(em).remove(user);
	}

}
