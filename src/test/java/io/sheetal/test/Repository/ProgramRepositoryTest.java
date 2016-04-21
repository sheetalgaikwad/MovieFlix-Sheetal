package io.sheetal.test.Repository;

import java.math.BigInteger;
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

import io.sheetal.entity.Program;
import io.sheetal.entity.Rating;
import io.sheetal.repository.ProgramRepository;
import io.sheetal.repository.ProgramRepositoryImpl;
import io.sheetal.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class ProgramRepositoryTest {
	
	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<Program> query;
	
	@InjectMocks
	private  ProgramRepository repository=new ProgramRepositoryImpl();
	
	private Program program;
	private Rating rating;
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		program=new Program();
		program.setTitle("Friends");
		program.setImdbId("FF002154");	
		program.setImdbVotes(new BigInteger("2350"));
		program.setImdbRating(9.2);
		program.setPlot("story of friends in nyc");
		program.setRating(rating);
		
		program.setProgramId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllPrograms()
	{
		List<Program> expected=Arrays.asList(program);
		
		Mockito.when(em.createQuery("SELECT p from Program p ORDER BY p.title ASC",Program.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		List<Program> actual=repository.findAllPrograms();
		Assert.assertEquals(expected, actual);
		//Mockito.verify(query).getResultList();
	}
	
	@Test
	public void testFindOneProgram()
	{
		Mockito.when(em.find(Program.class, program.getProgramId())).thenReturn(program);
		Program actual=repository.findOneProgram(program.getProgramId());
		Assert.assertEquals(program, actual);
	}
	
	@Test
	public void testFindByProgramType()
	{
		List<Program> expected=Arrays.asList(program);
		Mockito.when(em.createQuery("SELECT p from Program p where p.title=:programTitle",Program.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		Program actual=repository.findByProgramTitle(program.getTitle());
		Assert.assertEquals(program, actual);
	}
	
	@Test
	public void testFindByProgramTypeNUll()
	{
		Mockito.when(em.createQuery("SELECT p from Program p where p.title=:programTitle",Program.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		Program actual=repository.findByProgramTitle(program.getTitle());
		Assert.assertEquals(null, actual);
	}
	
	@Test
	public void testCreate()
	{
		repository.create(program);
		Mockito.verify(em).persist(program);
	}
	
	@Test
	public void testUpdateProgram()
	{
		repository.update(program);
		Mockito.verify(em).merge(program);
	}
	
	@Test
	public void testDeleteProgram()
	{
		repository.delete(program);
		Mockito.verify(em).remove(program);
	}

}
