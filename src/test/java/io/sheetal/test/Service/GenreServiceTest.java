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

import io.sheetal.entity.Genre;
import io.sheetal.exception.GenreAlreadyExistsException;
import io.sheetal.exception.GenreNotFoundException;
import io.sheetal.repository.GenreRepository;
import io.sheetal.service.GenreService;
import io.sheetal.service.GenreServiceImpl;
import io.sheetal.test.TestConfig;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class GenreServiceTest {
	
	@Mock
	private GenreRepository repository;
	
	@InjectMocks
	private GenreService service=new GenreServiceImpl();
	
	private Genre genre;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		genre=new Genre();
		genre.setGenreType("Comedy");
		genre.setGenreId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllGenres()
	{
		service.findAllGenres();
		Mockito.verify(repository).findAllGenre();		
	}
	
	
	@Test
	public void testFindOneGenre() throws GenreNotFoundException
	{
		Mockito.when(repository.findOneGenre(genre.getGenreId())).thenReturn(genre); 
		Genre actual=service.findOneGenre(genre.getGenreId());
		Assert.assertEquals(genre, actual);
	}
	
	@Test(expected=GenreNotFoundException.class)
	public void testFindOneGenreException() throws GenreNotFoundException
	{
		Mockito.when(repository.findOneGenre(genre.getGenreId())).thenReturn(null); 
		service.findOneGenre(genre.getGenreId());
	}
	
	@Test
	public void testCreate() throws GenreAlreadyExistsException
	{
		Mockito.when(repository.findByGenreType(genre.getGenreType())).thenReturn(null);
		service.create(genre);
		Mockito.verify(repository).create(genre);
	}
	
	@Test(expected=GenreAlreadyExistsException.class)
	public void testCreateException() throws GenreAlreadyExistsException
	{
		Mockito.when(repository.findByGenreType(genre.getGenreType())).thenReturn(genre);
		service.create(genre);		
	}
	
	@Test
	public void testUpdate() throws GenreNotFoundException
	{
		Mockito.when(repository.findOneGenre(genre.getGenreId())).thenReturn(genre);
		service.update(genre);
		Mockito.verify(repository).update(genre);
	}
		
	@Test(expected=GenreNotFoundException.class)
	public void testUpdateException() throws GenreNotFoundException
	{
		Mockito.when(repository.findOneGenre(genre.getGenreId())).thenReturn(null);
		service.update(genre);		
	}
	
	@Test
	public void testDelete() throws GenreNotFoundException
	{
		Mockito.when(repository.findOneGenre(genre.getGenreId())).thenReturn(genre);
		service.delete(genre.getGenreId());
		Mockito.verify(repository).delete(genre);
	}
		
	@Test(expected=GenreNotFoundException.class)
	public void testDeleteException() throws GenreNotFoundException
	{
		Mockito.when(repository.findOneGenre(genre.getGenreId())).thenReturn(null);
		service.delete(genre.getGenreId());		
	}
}
