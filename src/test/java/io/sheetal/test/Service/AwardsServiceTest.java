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

import io.sheetal.entity.Awards;
import io.sheetal.exception.AwardNotFoundException;
import io.sheetal.repository.AwardsRepository;
import io.sheetal.service.AwardsService;
import io.sheetal.service.AwardsServiceImpl;
import io.sheetal.test.TestConfig;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class AwardsServiceTest {
	
	@Mock
	private AwardsRepository repository;
	
	@InjectMocks
	private AwardsService service=new AwardsServiceImpl();
	
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
		service.findAllAwards();
		Mockito.verify(repository).findAllAwards();		
	}
	
	
	@Test
	public void testFindOneAwards() throws AwardNotFoundException
	{
		Mockito.when(repository.findOneAward(awards.getAwardsId())).thenReturn(awards); 
		Awards actual=service.findOneAward(awards.getAwardsId());
		Assert.assertEquals(awards, actual);
	}
	
	@Test(expected=AwardNotFoundException.class)
	public void testFindOneAwardsException() throws AwardNotFoundException
	{
		Mockito.when(repository.findOneAward(awards.getAwardsId())).thenReturn(null); 
		service.findOneAward(awards.getAwardsId());
	}
	
	@Test
	public void testCreate() 
	{
		Mockito.when(repository).thenReturn(null);
		service.create(awards);
		Mockito.verify(repository).create(awards);
	}
	
		
	@Test
	public void testUpdate() throws AwardNotFoundException
	{
		Mockito.when(repository.findOneAward(awards.getAwardsId())).thenReturn(awards);
		service.update(awards);
		Mockito.verify(repository).update(awards);
	}
		
	@Test(expected=AwardNotFoundException.class)
	public void testUpdateException() throws AwardNotFoundException
	{
		Mockito.when(repository.findOneAward(awards.getAwardsId())).thenReturn(null);
		service.update(awards);		
	}
	
	@Test
	public void testDelete() throws AwardNotFoundException
	{
		Mockito.when(repository.findOneAward(awards.getAwardsId())).thenReturn(awards);
		service.delete(awards.getAwardsId());
		Mockito.verify(repository).delete(awards);
	}
		
	@Test(expected=AwardNotFoundException.class)
	public void testDeleteException() throws AwardNotFoundException
	{
		Mockito.when(repository.findOneAward(awards.getAwardsId())).thenReturn(null);
		service.delete(awards.getAwardsId());		
	}
}
