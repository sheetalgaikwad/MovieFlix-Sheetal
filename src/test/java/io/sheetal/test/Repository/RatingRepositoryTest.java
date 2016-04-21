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

import io.sheetal.entity.Rating;
import io.sheetal.repository.RatingRepository;
import io.sheetal.repository.RatingRepositoryImpl;
import io.sheetal.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class RatingRepositoryTest {
	
	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<Rating> query;
	
	@InjectMocks
	private  RatingRepository repository=new RatingRepositoryImpl();
	
	private Rating rating;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		rating=new Rating();
		rating.setRatingType("PG-13");
		rating.setRatingId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllRatings()
	{
		List<Rating> expected=Arrays.asList(rating);
		
		Mockito.when(em.createQuery("SELECT r from Rating r ORDER BY r.ratingType ASC",Rating.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		List<Rating> actual=repository.findAllRatings();
		Assert.assertEquals(expected, actual);
		//Mockito.verify(query).getResultList();
	}
	
	@Test
	public void testFindOneRating()
	{
		Mockito.when(em.find(Rating.class, rating.getRatingId())).thenReturn(rating);
		Rating actual=repository.findOneRating(rating.getRatingId());
		Assert.assertEquals(rating, actual);
	}
	
	@Test
	public void testFindByRatingType()
	{
		List<Rating> expected=Arrays.asList(rating);
		Mockito.when(em.createQuery("SELECT r from Rating r where r.ratingType=:ratingType",Rating.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		Rating actual=repository.findByRatingType(rating.getRatingType());
		Assert.assertEquals(rating, actual);
	}
	
	@Test
	public void testFindByRatingNameNUll()
	{
		Mockito.when(em.createQuery("SELECT r from Rating r where r.ratingType=:ratingType",Rating.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		Rating actual=repository.findByRatingType(rating.getRatingType());
		Assert.assertEquals(null, actual);
	}
	
	@Test
	public void testCreate()
	{
		repository.create(rating);
		Mockito.verify(em).persist(rating);
	}
	
	@Test
	public void testUpdateRating()
	{
		repository.update(rating);
		Mockito.verify(em).merge(rating);
	}
	
	@Test
	public void testDeleteRating()
	{
		repository.delete(rating);
		Mockito.verify(em).remove(rating);
	}

}
