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

import io.sheetal.entity.Actor;
import io.sheetal.repository.ActorRepository;
import io.sheetal.repository.ActorRepositoryImpl;
import io.sheetal.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class ActorRepositoryTest {
	
	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<Actor> query;
	
	@InjectMocks
	private  ActorRepository repository=new ActorRepositoryImpl();
	
	private Actor Actor;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		Actor=new Actor();
		Actor.setActorName("John Doe");
		Actor.setActorId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllActors()
	{
		List<Actor> expected=Arrays.asList(Actor);
		
		Mockito.when(em.createQuery("SELECT a from Actor a ORDER BY a.actorName ASC",Actor.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		List<Actor> actual=repository.findAllActors();
		Assert.assertEquals(expected, actual);
		//Mockito.verify(query).getResultList();
	}
	
	@Test
	public void testFindOneActor()
	{
		Mockito.when(em.find(Actor.class, Actor.getActorId())).thenReturn(Actor);
		Actor actual=repository.findOneActor(Actor.getActorId());
		Assert.assertEquals(Actor, actual);
	}
	
	@Test
	public void testFindByActorName()
	{
		List<Actor> expected=Arrays.asList(Actor);
		Mockito.when(em.createQuery("SELECT a from Actor a where a.actorName=:actorName",Actor.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		Actor actual=repository.findByActorName(Actor.getActorName());
		Assert.assertEquals(Actor, actual);
	}
	
	@Test
	public void testFindByActorNameNUll()
	{
		Mockito.when(em.createQuery("SELECT a from Actor a where a.actorName=:actorName",Actor.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		Actor actual=repository.findByActorName(Actor.getActorName());
		Assert.assertEquals(null, actual);
	}
	
	@Test
	public void testCreate()
	{
		repository.create(Actor);
		Mockito.verify(em).persist(Actor);
	}
	
	@Test
	public void testUpdateActor()
	{
		repository.update(Actor);
		Mockito.verify(em).merge(Actor);
	}
	
	@Test
	public void testDeleteActor()
	{
		repository.delete(Actor);
		Mockito.verify(em).remove(Actor);
	}

}
