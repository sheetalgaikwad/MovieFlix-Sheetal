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

import io.sheetal.entity.Writer;
import io.sheetal.repository.WriterRepository;
import io.sheetal.repository.WriterRepositoryImpl;
import io.sheetal.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class WriterRepositoryTest {
	
	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<Writer> query;
	
	@InjectMocks
	private  WriterRepository repository=new WriterRepositoryImpl();
	
	private Writer writer;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		writer=new Writer();
		writer.setWriterName("John Doe");
		writer.setWriterId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllWriters()
	{
		List<Writer> expected=Arrays.asList(writer);
		
		Mockito.when(em.createQuery("SELECT w from Writer w ORDER BY w.writerName ASC",Writer.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		List<Writer> actual=repository.findAllWriters();
		Assert.assertEquals(expected, actual);
		//Mockito.verify(query).getResultList();
	}
	
	@Test
	public void testFindOneWriter()
	{
		Mockito.when(em.find(Writer.class, writer.getWriterId())).thenReturn(writer);
		Writer actual=repository.findOneWriter(writer.getWriterId());
		Assert.assertEquals(writer, actual);
	}
	
	@Test
	public void testFindByWriterName()
	{
		List<Writer> expected=Arrays.asList(writer);
		Mockito.when(em.createQuery("SELECT w from Writer w where w.writerName=:writerName",Writer.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		Writer actual=repository.findByWriterName(writer.getWriterName());
		Assert.assertEquals(writer, actual);
	}
	
	@Test
	public void testFindByWriterNameNUll()
	{
		Mockito.when(em.createQuery("SELECT w from Writer w where w.writerName=:writerName",Writer.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		Writer actual=repository.findByWriterName(writer.getWriterName());
		Assert.assertEquals(null, actual);
	}
	
	@Test
	public void testCreate() 
	{
		repository.create(writer);
		Mockito.verify(em).persist(writer);
	}
	
	@Test
	public void testUpdateWriter()
	{
		repository.update(writer);
		Mockito.verify(em).merge(writer);
	}
	
	@Test
	public void testDeleteWriter()
	{
		repository.delete(writer);
		Mockito.verify(em).remove(writer);
	}

}
