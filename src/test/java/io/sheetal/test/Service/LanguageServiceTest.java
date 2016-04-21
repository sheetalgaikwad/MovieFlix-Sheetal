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

import io.sheetal.entity.Language;
import io.sheetal.exception.LanguageAlreadyExistsException;
import io.sheetal.exception.LanguageNotFoundException;
import io.sheetal.repository.LanguageRepository;
import io.sheetal.service.LanguageService;
import io.sheetal.service.LanguageServiceImpl;
import io.sheetal.test.TestConfig;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class LanguageServiceTest {
	
	@Mock
	private LanguageRepository repository;
	
	@InjectMocks
	private LanguageService service=new LanguageServiceImpl();
	
	private Language language;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		language=new Language();
		language.setLanguageName("English");
		language.setLanguageId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllLanguages()
	{
		service.findAllLanguages();
		Mockito.verify(repository).findAllLanguages();		
	}
	
	
	@Test
	public void testFindOneLanguage() throws LanguageNotFoundException
	{
		Mockito.when(repository.findOneLanguage(language.getLanguageId())).thenReturn(language); 
		Language actual=service.findOneLanguage(language.getLanguageId());
		Assert.assertEquals(language, actual);
	}
	
	@Test(expected=LanguageNotFoundException.class)
	public void testFindOneLanguageException() throws LanguageNotFoundException
	{
		Mockito.when(repository.findOneLanguage(language.getLanguageId())).thenReturn(null); 
		service.findOneLanguage(language.getLanguageId());
	}
	
	@Test
	public void testCreate() throws LanguageAlreadyExistsException
	{
		Mockito.when(repository.findByLanguageName(language.getLanguageName())).thenReturn(null);
		service.create(language);
		Mockito.verify(repository).create(language);
	}
	
	@Test(expected=LanguageAlreadyExistsException.class)
	public void testCreateException() throws LanguageAlreadyExistsException
	{
		Mockito.when(repository.findByLanguageName(language.getLanguageName())).thenReturn(language);
		service.create(language);		
	}
	
	@Test
	public void testUpdate() throws LanguageNotFoundException
	{
		Mockito.when(repository.findOneLanguage(language.getLanguageId())).thenReturn(language);
		service.update(language);
		Mockito.verify(repository).update(language);
	}
		
	@Test(expected=LanguageNotFoundException.class)
	public void testUpdateException() throws LanguageNotFoundException
	{
		Mockito.when(repository.findOneLanguage(language.getLanguageId())).thenReturn(null);
		service.update(language);		
	}
	
	@Test
	public void testDelete() throws LanguageNotFoundException
	{
		Mockito.when(repository.findOneLanguage(language.getLanguageId())).thenReturn(language);
		service.delete(language.getLanguageId());
		Mockito.verify(repository).delete(language);
	}
		
	@Test(expected=LanguageNotFoundException.class)
	public void testDeleteException() throws LanguageNotFoundException
	{
		Mockito.when(repository.findOneLanguage(language.getLanguageId())).thenReturn(null);
		service.delete(language.getLanguageId());		
	}
}
