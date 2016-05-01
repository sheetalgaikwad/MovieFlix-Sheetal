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

import io.sheetal.entity.Director;
import io.sheetal.exception.DirectorAlreadyExistsException;
import io.sheetal.exception.DirectorNotFoundException;
import io.sheetal.repository.DirectorRepository;
import io.sheetal.service.DirectorService;
import io.sheetal.service.DirectorServiceImpl;
import io.sheetal.test.TestConfig;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class DirectorServiceTest {
	
	@Mock
	private DirectorRepository repository;
	
	@InjectMocks
	private DirectorService service=new DirectorServiceImpl();
	
	private Director director;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		director=new Director();
		director.setDirectorName("Christian Bale");
		director.setDirectorId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllDirectors()
	{
		service.findAllDirectors();
		Mockito.verify(repository).findAllDirectors();		
	}
	
	
	@Test
	public void testFindOneDirector() throws DirectorNotFoundException
	{
		Mockito.when(repository.findOneDirector(director.getDirectorId())).thenReturn(director); 
		Director actual=service.findOneDirector(director.getDirectorId());
		Assert.assertEquals(director, actual);
	}
	
	@Test(expected=DirectorNotFoundException.class)
	public void testFindOneDirectorException() throws DirectorNotFoundException
	{
		Mockito.when(repository.findOneDirector(director.getDirectorId())).thenReturn(null); 
		service.findOneDirector(director.getDirectorId());
	}
	
	@Test
	public void testCreate() throws DirectorAlreadyExistsException
	{
		Mockito.when(repository.findByDirectorName(director.getDirectorName())).thenReturn(null);
		service.create(director);
		Mockito.verify(repository).create(director);
	}
	
	@Test(expected=DirectorAlreadyExistsException.class)
	public void testCreateException() throws DirectorAlreadyExistsException
	{
		Mockito.when(repository.findByDirectorName(director.getDirectorName())).thenReturn(director);
		service.create(director);		
	}
	
	@Test
	public void testUpdate() throws DirectorNotFoundException
	{
		Mockito.when(repository.findOneDirector(director.getDirectorId())).thenReturn(director);
		service.update(director);
		Mockito.verify(repository).update(director);
	}
		
	@Test(expected=DirectorNotFoundException.class)
	public void testUpdateException() throws DirectorNotFoundException
	{
		Mockito.when(repository.findOneDirector(director.getDirectorId())).thenReturn(null);
		service.update(director);		
	}
	
	@Test
	public void testDelete() throws DirectorNotFoundException
	{
		Mockito.when(repository.findOneDirector(director.getDirectorId())).thenReturn(director);
		service.delete(director.getDirectorId());
		Mockito.verify(repository).delete(director);
	}
		
	@Test(expected=DirectorNotFoundException.class)
	public void testDeleteException() throws DirectorNotFoundException
	{
		Mockito.when(repository.findOneDirector(director.getDirectorId())).thenReturn(null);
		service.delete(director.getDirectorId());		
	}
}
