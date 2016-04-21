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

import io.sheetal.entity.ProgramType;
import io.sheetal.exception.ProgramTypeAlreadyExistsException;
import io.sheetal.exception.ProgramTypeNotFoundException;
import io.sheetal.repository.ProgramTypeRepository;
import io.sheetal.service.ProgramTypeService;
import io.sheetal.service.ProgramTypeServiceImpl;
import io.sheetal.test.TestConfig;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class ProgramTypeServiceTest {
	
	@Mock
	private ProgramTypeRepository repository;
	
	@InjectMocks
	private ProgramTypeService service=new ProgramTypeServiceImpl();
	
	private ProgramType programType;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		programType=new ProgramType();
		programType.setProgramType("Series");
		programType.setProgramTypeId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllProgramTypes()
	{
		service.findAllProgramTypes();
		Mockito.verify(repository).findAllProgramTypes();		
	}
	
	
	@Test
	public void testFindOneProgramType() throws ProgramTypeNotFoundException
	{
		Mockito.when(repository.findOneProgramType(programType.getProgramTypeId())).thenReturn(programType); 
		ProgramType actual=service.findOneProgramType(programType.getProgramTypeId());
		Assert.assertEquals(programType, actual);
	}
	
	@Test(expected=ProgramTypeNotFoundException.class)
	public void testFindOneProgramTypeException() throws ProgramTypeNotFoundException
	{
		Mockito.when(repository.findOneProgramType(programType.getProgramTypeId())).thenReturn(null); 
		service.findOneProgramType(programType.getProgramTypeId());
	}
	
	@Test
	public void testCreate() throws ProgramTypeAlreadyExistsException
	{
		Mockito.when(repository.findByProgramType(programType.getProgramType())).thenReturn(null);
		service.create(programType);
		Mockito.verify(repository).create(programType);
	}
	
	@Test(expected=ProgramTypeAlreadyExistsException.class)
	public void testCreateException() throws ProgramTypeAlreadyExistsException
	{
		Mockito.when(repository.findByProgramType(programType.getProgramType())).thenReturn(programType);
		service.create(programType);		
	}
	
	@Test
	public void testUpdate() throws ProgramTypeNotFoundException
	{
		Mockito.when(repository.findOneProgramType(programType.getProgramTypeId())).thenReturn(programType);
		service.update(programType);
		Mockito.verify(repository).update(programType);
	}
		
	@Test(expected=ProgramTypeNotFoundException.class)
	public void testUpdateException() throws ProgramTypeNotFoundException
	{
		Mockito.when(repository.findOneProgramType(programType.getProgramTypeId())).thenReturn(null);
		service.update(programType);		
	}
	
	@Test
	public void testDelete() throws ProgramTypeNotFoundException
	{
		Mockito.when(repository.findOneProgramType(programType.getProgramTypeId())).thenReturn(programType);
		service.delete(programType.getProgramTypeId());
		Mockito.verify(repository).delete(programType);
	}
		
	@Test(expected=ProgramTypeNotFoundException.class)
	public void testDeleteException() throws ProgramTypeNotFoundException
	{
		Mockito.when(repository.findOneProgramType(programType.getProgramTypeId())).thenReturn(null);
		service.delete(programType.getProgramTypeId());		
	}
}
