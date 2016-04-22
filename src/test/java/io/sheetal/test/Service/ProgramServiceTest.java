package io.sheetal.test.Service;

import java.util.ArrayList;
import java.util.List;
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

import io.sheetal.entity.Genre;
import io.sheetal.entity.Program;
import io.sheetal.exception.ProgramAlreadyExistsException;
import io.sheetal.exception.ProgramNotFoundException;
import io.sheetal.repository.ProgramRepository;
import io.sheetal.service.ProgramService;
import io.sheetal.service.ProgramServiceImpl;
import io.sheetal.test.TestConfig;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class ProgramServiceTest {
	
	@Mock
	private ProgramRepository repository;
	
	@InjectMocks
	private ProgramService service=new ProgramServiceImpl();
	
	private Program program;
	private Genre genre;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		program=new Program();
		program.setTitle("Friends");
		program.setProgramId(UUID.randomUUID().toString());
		genre=new Genre();
		genre.setGenreType("comedy");
		genre.setGenreId(UUID.randomUUID().toString());		
		
		List<Genre> genreList=new ArrayList<>();
		genreList.add(genre);
		program.setGenres(genreList);
	}	
	
	@Test
	public void testFindAllPrograms()
	{
		service.findAllPrograms();
		Mockito.verify(repository).findAllPrograms();		
	}
	
	@Test
	public void testFindByGenre()
	{
		service.findByGenre(genre.getGenreType());
		Mockito.verify(repository).findByGenre(genre.getGenreType());		
	}
	
	@Test
	public void testFindOneProgram() throws ProgramNotFoundException
	{
		Mockito.when(repository.findOneProgram(program.getProgramId())).thenReturn(program); 
		Program actual=service.findOneProgram(program.getProgramId());
		Assert.assertEquals(program, actual);
	}
	
	@Test(expected=ProgramNotFoundException.class)
	public void testFindOneProgramException() throws ProgramNotFoundException
	{
		Mockito.when(repository.findOneProgram(program.getProgramId())).thenReturn(null); 
		service.findOneProgram(program.getProgramId());
	}
	
	@Test
	public void testCreate() throws ProgramAlreadyExistsException
	{
		Mockito.when(repository.findByProgramTitle(program.getTitle())).thenReturn(null);
		service.create(program);
		Mockito.verify(repository).create(program);
	}
	
	@Test(expected=ProgramAlreadyExistsException.class)
	public void testCreateException() throws ProgramAlreadyExistsException
	{
		Mockito.when(repository.findByProgramTitle(program.getTitle())).thenReturn(program);
		service.create(program);		
	}
	
	@Test
	public void testUpdate() throws ProgramNotFoundException
	{
		Mockito.when(repository.findOneProgram(program.getProgramId())).thenReturn(program);
		service.update(program);
		Mockito.verify(repository).update(program);
	}
		
	@Test(expected=ProgramNotFoundException.class)
	public void testUpdateException() throws ProgramNotFoundException
	{
		Mockito.when(repository.findOneProgram(program.getProgramId())).thenReturn(null);
		service.update(program);		
	}
	
	@Test
	public void testDelete() throws ProgramNotFoundException
	{
		Mockito.when(repository.findOneProgram(program.getProgramId())).thenReturn(program);
		service.delete(program.getProgramId());
		Mockito.verify(repository).delete(program);
	}
		
	@Test(expected=ProgramNotFoundException.class)
	public void testDeleteException() throws ProgramNotFoundException
	{
		Mockito.when(repository.findOneProgram(program.getProgramId())).thenReturn(null);
		service.delete(program.getProgramId());		
	}
}
