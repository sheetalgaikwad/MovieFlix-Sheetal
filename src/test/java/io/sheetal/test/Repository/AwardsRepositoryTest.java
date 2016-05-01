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

import io.sheetal.entity.Awards;
import io.sheetal.repository.AwardsRepository;
import io.sheetal.repository.AwardsRepositoryImpl;
import io.sheetal.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class AwardsRepositoryTest {
	
	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<Awards> query;
	
	@InjectMocks
	private  AwardsRepository repository=new AwardsRepositoryImpl();
	
	private Awards awards;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		awards=new Awards();
		awards.setAwardsId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllAwardss()
	{
		List<Awards> expected=Arrays.asList(awards);
		
		Mockito.when(em.createQuery("SELECT a from Awards a ORDER BY a.oscarWins DESC",Awards.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		List<Awards> actual=repository.findAllAwards();
		Assert.assertEquals(expected, actual);
		//Mockito.verify(query).getResultList();
	}
	
	@Test
	public void testFindOneAward()
	{
		Mockito.when(em.find(Awards.class, awards.getAwardsId())).thenReturn(awards);
		Awards actual=repository.findOneAward(awards.getAwardsId());
		Assert.assertEquals(awards, actual);
	}	
		
	@Test
	public void testCreate()
	{
		repository.create(awards);
		Mockito.verify(em).persist(awards);
	}
	
	@Test
	public void testUpdateAwards()
	{
		repository.update(awards);
		Mockito.verify(em).merge(awards);
	}
	
	@Test
	public void testDeleteAwards()
	{
		repository.delete(awards);
		Mockito.verify(em).remove(awards);
	}

}
