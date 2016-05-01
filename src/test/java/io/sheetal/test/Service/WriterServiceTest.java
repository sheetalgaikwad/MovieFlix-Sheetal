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

import io.sheetal.entity.Writer;
import io.sheetal.exception.WriterAlreadyExistsException;
import io.sheetal.exception.WriterNotFoundException;
import io.sheetal.repository.WriterRepository;
import io.sheetal.service.WriterService;
import io.sheetal.service.WriterServiceImpl;
import io.sheetal.test.TestConfig;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class WriterServiceTest {
	
	@Mock
	private WriterRepository repository;
	
	@InjectMocks
	private WriterService service=new WriterServiceImpl();
	
	private Writer writer;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		writer=new Writer();
		writer.setWriterName("Christian Bale");
		writer.setWriterId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllWriters()
	{
		service.findAllWriters();
		Mockito.verify(repository).findAllWriters();		
	}
	
	
	@Test
	public void testFindOneWriter() throws WriterNotFoundException
	{
		Mockito.when(repository.findOneWriter(writer.getWriterId())).thenReturn(writer); 
		Writer actual=service.findOneWriter(writer.getWriterId());
		Assert.assertEquals(writer, actual);
	}
	
	@Test(expected=WriterNotFoundException.class)
	public void testFindOneWriterException() throws WriterNotFoundException
	{
		Mockito.when(repository.findOneWriter(writer.getWriterId())).thenReturn(null); 
		service.findOneWriter(writer.getWriterId());
	}
	
	@Test
	public void testCreate() throws WriterAlreadyExistsException
	{
		Mockito.when(repository.findByWriterName(writer.getWriterName())).thenReturn(null);
		service.create(writer);
		Mockito.verify(repository).create(writer);
	}
	
	@Test(expected=WriterAlreadyExistsException.class)
	public void testCreateException() throws WriterAlreadyExistsException
	{
		Mockito.when(repository.findByWriterName(writer.getWriterName())).thenReturn(writer);
		service.create(writer);		
	}
	
	@Test
	public void testUpdate() throws WriterNotFoundException
	{
		Mockito.when(repository.findOneWriter(writer.getWriterId())).thenReturn(writer);
		service.update(writer);
		Mockito.verify(repository).update(writer);
	}
		
	@Test(expected=WriterNotFoundException.class)
	public void testUpdateException() throws WriterNotFoundException
	{
		Mockito.when(repository.findOneWriter(writer.getWriterId())).thenReturn(null);
		service.update(writer);		
	}
	
	@Test
	public void testDelete() throws WriterNotFoundException
	{
		Mockito.when(repository.findOneWriter(writer.getWriterId())).thenReturn(writer);
		service.delete(writer.getWriterId());
		Mockito.verify(repository).delete(writer);
	}
		
	@Test(expected=WriterNotFoundException.class)
	public void testDeleteException() throws WriterNotFoundException
	{
		Mockito.when(repository.findOneWriter(writer.getWriterId())).thenReturn(null);
		service.delete(writer.getWriterId());		
	}
}
