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

import io.sheetal.entity.Actor;
import io.sheetal.exception.ActorAlreadyExistsException;
import io.sheetal.exception.ActorNotFoundException;
import io.sheetal.repository.ActorRepository;
import io.sheetal.service.ActorService;
import io.sheetal.service.ActorServiceImpl;
import io.sheetal.test.TestConfig;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class ActorServiceTest {
	
	@Mock
	private ActorRepository repository;
	
	@InjectMocks
	private ActorService service=new ActorServiceImpl();
	
	private Actor actor;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		actor=new Actor();
		actor.setActorName("Christian Bale");
		actor.setActorId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllActors()
	{
		service.findAllActors();
		Mockito.verify(repository).findAllActors();		
	}
	
	
	@Test
	public void testFindOneActor() throws ActorNotFoundException
	{
		Mockito.when(repository.findOneActor(actor.getActorId())).thenReturn(actor); 
		Actor actual=service.findOneActor(actor.getActorId());
		Assert.assertEquals(actor, actual);
	}
	
	@Test(expected=ActorNotFoundException.class)
	public void testFindOneActorException() throws ActorNotFoundException
	{
		Mockito.when(repository.findOneActor(actor.getActorId())).thenReturn(null); 
		service.findOneActor(actor.getActorId());
	}
	
	@Test
	public void testCreate() throws ActorAlreadyExistsException
	{
		Mockito.when(repository.findByActorName(actor.getActorName())).thenReturn(null);
		service.create(actor);
		Mockito.verify(repository).create(actor);
	}
	
	@Test(expected=ActorAlreadyExistsException.class)
	public void testCreateException() throws ActorAlreadyExistsException
	{
		Mockito.when(repository.findByActorName(actor.getActorName())).thenReturn(actor);
		service.create(actor);		
	}
	
	@Test
	public void testUpdate() throws ActorNotFoundException
	{
		Mockito.when(repository.findOneActor(actor.getActorId())).thenReturn(actor);
		service.update(actor);
		Mockito.verify(repository).update(actor);
	}
		
	@Test(expected=ActorNotFoundException.class)
	public void testUpdateException() throws ActorNotFoundException
	{
		Mockito.when(repository.findOneActor(actor.getActorId())).thenReturn(null);
		service.update(actor);		
	}
	
	@Test
	public void testDelete() throws ActorNotFoundException
	{
		Mockito.when(repository.findOneActor(actor.getActorId())).thenReturn(actor);
		service.delete(actor.getActorId());
		Mockito.verify(repository).delete(actor);
	}
		
	@Test(expected=ActorNotFoundException.class)
	public void testDeleteException() throws ActorNotFoundException
	{
		Mockito.when(repository.findOneActor(actor.getActorId())).thenReturn(null);
		service.delete(actor.getActorId());		
	}
}
