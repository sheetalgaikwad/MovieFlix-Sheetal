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

import io.sheetal.entity.Language;
import io.sheetal.repository.LanguageRepository;
import io.sheetal.repository.LanguageRepositoryImpl;
import io.sheetal.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class LanguageRepositoryTest {
	
	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<Language> query;
	
	@InjectMocks
	private  LanguageRepository repository=new LanguageRepositoryImpl();
	
	private Language language;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		language=new Language();
		language.setLanguageName("German");
		language.setLanguageId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllLanguages()
	{
		List<Language> expected=Arrays.asList(language);
		
		Mockito.when(em.createQuery("SELECT l from Language l ORDER BY l.languageName ASC",Language.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		List<Language> actual=repository.findAllLanguages();
		Assert.assertEquals(expected, actual);
		//Mockito.verify(query).getResultList();
	}
	
	@Test
	public void testFindOneLanguage()
	{
		Mockito.when(em.find(Language.class, language.getLanguageId())).thenReturn(language);
		Language actual=repository.findOneLanguage(language.getLanguageId());
		Assert.assertEquals(language, actual);
	}
	
	@Test
	public void testFindByLanguageName()
	{
		List<Language> expected=Arrays.asList(language);
		Mockito.when(em.createQuery("SELECT l from Language l where l.languageName=:languageName",Language.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		Language actual=repository.findByLanguageName(language.getLanguageName());
		Assert.assertEquals(language, actual);
	}
	
	@Test
	public void testFindByLanguageNameNUll()
	{
		Mockito.when(em.createQuery("SELECT l from Language l where l.languageName=:languageName",Language.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		Language actual=repository.findByLanguageName(language.getLanguageName());
		Assert.assertEquals(null, actual);
	}
	
	@Test
	public void testCreate()
	{
		repository.create(language);
		Mockito.verify(em).persist(language);
	}
	
	@Test
	public void testUpdateLanguage()
	{
		repository.update(language);
		Mockito.verify(em).merge(language);
	}
	
	@Test
	public void testDeleteLanguage()
	{
		repository.delete(language);
		Mockito.verify(em).remove(language);
	}

}
