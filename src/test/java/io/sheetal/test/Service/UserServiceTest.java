package io.sheetal.test.Service;

import java.util.UUID;

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
import io.sheetal.exception.UserAlreadyExistsException;
import io.sheetal.exception.UserNotFoundException;
import io.sheetal.repository.UserRepository;
import io.sheetal.service.UserService;
import io.sheetal.service.UserServiceImpl;
import io.sheetal.test.TestConfig;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class UserServiceTest {
	
	@Mock
	private UserRepository repository;
	
	@InjectMocks
	private UserService service=new UserServiceImpl();
	
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
	public void testFindAllUsers()
	{
		service.findAllUsers();
		Mockito.verify(repository).findAllUsers();		
	}
	
	
	@Test
	public void testFindOne() throws UserNotFoundException
	{
		Mockito.when(repository.findOne(user.getId())).thenReturn(user); 
		User actual=service.findOne(user.getId());
		Assert.assertEquals(user, actual);
	}
	
	@Test(expected=UserNotFoundException.class)
	public void testFindOneException() throws UserNotFoundException
	{
		Mockito.when(repository.findOne(user.getId())).thenReturn(null); 
		service.findOne(user.getId());
	}
	
	@Test
	public void testCreate() throws UserAlreadyExistsException
	{
		Mockito.when(repository.findByEmail(user.getEmail())).thenReturn(null);
		service.create(user);
		Mockito.verify(repository).create(user);
	}
	
	@Test(expected=UserAlreadyExistsException.class)
	public void testCreateException() throws UserAlreadyExistsException
	{
		Mockito.when(repository.findByEmail(user.getEmail())).thenReturn(user);
		service.create(user);		
	}
	
	@Test
	public void testUpdate() throws UserNotFoundException
	{
		Mockito.when(repository.findOne(user.getId())).thenReturn(user);
		service.update(user);
		Mockito.verify(repository).update(user);
	}
		
	@Test(expected=UserNotFoundException.class)
	public void testUpdateException() throws UserNotFoundException
	{
		Mockito.when(repository.findOne(user.getId())).thenReturn(null);
		service.update(user);		
	}
	
	@Test
	public void testDelete() throws UserNotFoundException
	{
		Mockito.when(repository.findOne(user.getId())).thenReturn(user);
		service.delete(user.getId());
		Mockito.verify(repository).delete(user);
	}
		
	@Test(expected=UserNotFoundException.class)
	public void testDeleteException() throws UserNotFoundException
	{
		Mockito.when(repository.findOne(user.getId())).thenReturn(null);
		service.delete(user.getId());		
	}
	
	
	
	
	

}
