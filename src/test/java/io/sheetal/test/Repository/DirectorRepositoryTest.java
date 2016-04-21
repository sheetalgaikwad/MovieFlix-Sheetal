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

import io.sheetal.entity.Director;
import io.sheetal.repository.DirectorRepository;
import io.sheetal.repository.DirectorRepositoryImpl;
import io.sheetal.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class DirectorRepositoryTest {
	
	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<Director> query;
	
	@InjectMocks
	private  DirectorRepository repository=new DirectorRepositoryImpl();
	
	private Director director;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		director=new Director();
		director.setDirectorName("John Doe");
		director.setDirectorId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllDirectors()
	{
		List<Director> expected=Arrays.asList(director);
		
		Mockito.when(em.createQuery("SELECT d from Director d ORDER BY d.directorName ASC",Director.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		List<Director> actual=repository.findAllDirectors();
		Assert.assertEquals(expected, actual);
		//Mockito.verify(query).getResultList();
	}
	
	@Test
	public void testFindOneDirector()
	{
		Mockito.when(em.find(Director.class, director.getDirectorId())).thenReturn(director);
		Director actual=repository.findOneDirector(director.getDirectorId());
		Assert.assertEquals(director, actual);
	}
	
	@Test
	public void testFindByDirectorName()
	{
		List<Director> expected=Arrays.asList(director);
		Mockito.when(em.createQuery("SELECT d from Director d where d.directorName=:directorName",Director.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		Director actual=repository.findByDirectorName(director.getDirectorName());
		Assert.assertEquals(director, actual);
	}
	
	@Test
	public void testFindByDirectorNameNUll()
	{
		Mockito.when(em.createQuery("SELECT d from Director d where d.directorName=:directorName",Director.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		Director actual=repository.findByDirectorName(director.getDirectorName());
		Assert.assertEquals(null, actual);
	}
	
	@Test
	public void testCreate()
	{
		repository.create(director);
		Mockito.verify(em).persist(director);
	}
	
	@Test
	public void testUpdateDirector()
	{
		repository.update(director);
		Mockito.verify(em).merge(director);
	}
	
	@Test
	public void testDeleteDirector()
	{
		repository.delete(director);
		Mockito.verify(em).remove(director);
	}

}
