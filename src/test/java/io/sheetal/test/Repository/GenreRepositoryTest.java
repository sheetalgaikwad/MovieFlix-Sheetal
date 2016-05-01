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

import io.sheetal.entity.Genre;
import io.sheetal.repository.GenreRepository;
import io.sheetal.repository.GenreRepositoryImp;
import io.sheetal.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class GenreRepositoryTest {
	
	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<Genre> query;
	
	@InjectMocks
	private  GenreRepository repository=new GenreRepositoryImp();
	
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
		List<Genre> expected=Arrays.asList(genre);
		
		Mockito.when(em.createQuery("SELECT g from Genre g ORDER BY g.genreType ASC",Genre.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		List<Genre> actual=repository.findAllGenre();
		Assert.assertEquals(expected, actual);
		//Mockito.verify(query).getResultList();
	}
	
	@Test
	public void testFindOneGenre()
	{
		Mockito.when(em.find(Genre.class, genre.getGenreId())).thenReturn(genre);
		Genre actual=repository.findOneGenre(genre.getGenreId());
		Assert.assertEquals(genre, actual);
	}
	
	@Test
	public void testFindByGenreType()
	{
		List<Genre> expected=Arrays.asList(genre);
		Mockito.when(em.createQuery("SELECT g from Genre g where g.genreType=:genreType",Genre.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		Genre actual=repository.findByGenreType(genre.getGenreType());
		Assert.assertEquals(genre, actual);
	}
	
	@Test
	public void testFindByGenreTypeNUll()
	{
		Mockito.when(em.createQuery("SELECT g from Genre g where g.genreType=:genreType",Genre.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		Genre actual=repository.findByGenreType(genre.getGenreType());
		Assert.assertEquals(null, actual);
	}
	
	@Test
	public void testCreate()
	{
		repository.create(genre);
		Mockito.verify(em).persist(genre);
	}
	
	@Test
	public void testUpdateGenre()
	{
		repository.update(genre);
		Mockito.verify(em).merge(genre);
	}
	
	@Test
	public void testDeleteGenre()
	{
		repository.delete(genre);
		Mockito.verify(em).remove(genre);
	}

}
