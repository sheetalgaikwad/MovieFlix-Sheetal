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

import io.sheetal.entity.ProgramType;
import io.sheetal.repository.ProgramTypeRepository;
import io.sheetal.repository.ProgramTypeRepositoryImpl;
import io.sheetal.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class ProgramTypeRepositoryTest {
	
	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<ProgramType> query;
	
	@InjectMocks
	private  ProgramTypeRepository repository=new ProgramTypeRepositoryImpl();
	
	private ProgramType programType;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		programType=new ProgramType();
		programType.setProgramType("PG-13");
		programType.setProgramTypeId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllProgramTypes()
	{
		List<ProgramType> expected=Arrays.asList(programType);
		
		Mockito.when(em.createQuery("SELECT p from ProgramType p ORDER BY p.programType ASC",ProgramType.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		List<ProgramType> actual=repository.findAllProgramTypes();
		Assert.assertEquals(expected, actual);		
	}
	
	@Test
	public void testFindOneProgramType()
	{
		Mockito.when(em.find(ProgramType.class, programType.getProgramTypeId())).thenReturn(programType);
		ProgramType actual=repository.findOneProgramType(programType.getProgramTypeId());
		Assert.assertEquals(programType, actual);
	}
	
	@Test
	public void testFindByProgramType()
	{
		List<ProgramType> expected=Arrays.asList(programType);
		Mockito.when(em.createQuery("SELECT p from ProgramType p where p.programType=:programType",ProgramType.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		ProgramType actual=repository.findByProgramType(programType.getProgramType());
		Assert.assertEquals(programType, actual);
	}
	
	@Test
	public void testFindByProgramTypeNUll()
	{
		Mockito.when(em.createQuery("SELECT p from ProgramType p where p.programType=:programType",ProgramType.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		ProgramType actual=repository.findByProgramType(programType.getProgramType());
		Assert.assertEquals(null, actual);
	}
	
	@Test
	public void testCreate()
	{
		repository.create(programType);
		Mockito.verify(em).persist(programType);
	}
	
	@Test
	public void testUpdateProgramType()
	{
		repository.update(programType);
		Mockito.verify(em).merge(programType);
	}
	
	@Test
	public void testDeleteProgramType()
	{
		repository.delete(programType);
		Mockito.verify(em).remove(programType);
	}

}
