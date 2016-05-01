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

import io.sheetal.entity.Country;
import io.sheetal.repository.CountryRepository;
import io.sheetal.repository.CountryRepositoryImpl;
import io.sheetal.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class CountryRepositoryTest {
	
	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<Country> query;
	
	@InjectMocks
	private  CountryRepository repository=new CountryRepositoryImpl();
	
	private Country country;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		country=new Country();
		country.setCountryName("USA");
		country.setCountryId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllCountries()
	{
		List<Country> expected=Arrays.asList(country);
		
		Mockito.when(em.createQuery("SELECT c from Country c ORDER BY c.countryName ASC",Country.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		List<Country> actual=repository.findAllCountries();
		Assert.assertEquals(expected, actual);
		//Mockito.verify(query).getResultList();
	}
	
	@Test
	public void testFindOneCountry()
	{
		Mockito.when(em.find(Country.class, country.getCountryId())).thenReturn(country);
		Country actual=repository.findOneCountry(country.getCountryId());
		Assert.assertEquals(country, actual);
	}
	
	@Test
	public void testFindByCountryName()
	{
		List<Country> expected=Arrays.asList(country);
		Mockito.when(em.createQuery("SELECT c from Country c where c.countryName=:countryName",Country.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		Country actual=repository.findByCountryName(country.getCountryName());
		Assert.assertEquals(country, actual);
	}
	
	@Test
	public void testFindByCountryNameNUll()
	{
		Mockito.when(em.createQuery("SELECT c from Country c where c.countryName=:countryName",Country.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		Country actual=repository.findByCountryName(country.getCountryName());
		Assert.assertEquals(null, actual);
	}
	
	@Test
	public void testCreate()
	{
		repository.create(country);
		Mockito.verify(em).persist(country);
	}
	
	@Test
	public void testUpdateCountry()
	{
		repository.update(country);
		Mockito.verify(em).merge(country);
	}
	
	@Test
	public void testDeleteCountry()
	{
		repository.delete(country);
		Mockito.verify(em).remove(country);
	}

}
