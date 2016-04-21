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

import io.sheetal.entity.Rating;
import io.sheetal.exception.RatingAlreadyExistsException;
import io.sheetal.exception.RatingNotFoundException;
import io.sheetal.repository.RatingRepository;
import io.sheetal.service.RatingService;
import io.sheetal.service.RatingServiceImpl;
import io.sheetal.test.TestConfig;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class RatingServiceTest {
	
	@Mock
	private RatingRepository repository;
	
	@InjectMocks
	private RatingService service=new RatingServiceImpl();
	
	private Rating rating;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		rating=new Rating();
		rating.setRatingType("R");
		rating.setRatingId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllRatings()
	{
		service.findAllRatings();
		Mockito.verify(repository).findAllRatings();		
	}
	
	
	@Test
	public void testFindOneRating() throws RatingNotFoundException
	{
		Mockito.when(repository.findOneRating(rating.getRatingId())).thenReturn(rating); 
		Rating actual=service.findOneRating(rating.getRatingId());
		Assert.assertEquals(rating, actual);
	}
	
	@Test(expected=RatingNotFoundException.class)
	public void testFindOneRatingException() throws RatingNotFoundException
	{
		Mockito.when(repository.findOneRating(rating.getRatingId())).thenReturn(null); 
		service.findOneRating(rating.getRatingId());
	}
	
	@Test
	public void testCreate() throws RatingAlreadyExistsException
	{
		Mockito.when(repository.findByRatingType(rating.getRatingType())).thenReturn(null);
		service.create(rating);
		Mockito.verify(repository).create(rating);
	}
	
	@Test(expected=RatingAlreadyExistsException.class)
	public void testCreateException() throws RatingAlreadyExistsException
	{
		Mockito.when(repository.findByRatingType(rating.getRatingType())).thenReturn(rating);
		service.create(rating);		
	}
	
	@Test
	public void testUpdate() throws RatingNotFoundException
	{
		Mockito.when(repository.findOneRating(rating.getRatingId())).thenReturn(rating);
		service.update(rating);
		Mockito.verify(repository).update(rating);
	}
		
	@Test(expected=RatingNotFoundException.class)
	public void testUpdateException() throws RatingNotFoundException
	{
		Mockito.when(repository.findOneRating(rating.getRatingId())).thenReturn(null);
		service.update(rating);		
	}
	
	@Test
	public void testDelete() throws RatingNotFoundException
	{
		Mockito.when(repository.findOneRating(rating.getRatingId())).thenReturn(rating);
		service.delete(rating.getRatingId());
		Mockito.verify(repository).delete(rating);
	}
		
	@Test(expected=RatingNotFoundException.class)
	public void testDeleteException() throws RatingNotFoundException
	{
		Mockito.when(repository.findOneRating(rating.getRatingId())).thenReturn(null);
		service.delete(rating.getRatingId());		
	}
}
