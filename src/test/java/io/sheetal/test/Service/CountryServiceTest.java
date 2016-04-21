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

import io.sheetal.entity.Country;
import io.sheetal.exception.CountryAlreadyExistsException;
import io.sheetal.exception.CountryNotFoundException;
import io.sheetal.repository.CountryRepository;
import io.sheetal.service.CountryService;
import io.sheetal.service.CountryServiceImpl;
import io.sheetal.test.TestConfig;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class CountryServiceTest {
	
	@Mock
	private CountryRepository repository;
	
	@InjectMocks
	private CountryService service=new CountryServiceImpl();
	
	private Country country;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		country=new Country();
		country.setCountryName("USA");
		country.setCountryId(UUID.randomUUID().toString());
	}	
	
	@Test
	public void testFindAllCountrys()
	{
		service.findAllCountries();
		Mockito.verify(repository).findAllCountries();		
	}
	
	
	@Test
	public void testFindOneCountry() throws CountryNotFoundException
	{
		Mockito.when(repository.findOneCountry(country.getCountryId())).thenReturn(country); 
		Country actual=service.findOneCountry(country.getCountryId());
		Assert.assertEquals(country, actual);
	}
	
	@Test(expected=CountryNotFoundException.class)
	public void testFindOneCountryException() throws CountryNotFoundException
	{
		Mockito.when(repository.findOneCountry(country.getCountryId())).thenReturn(null); 
		service.findOneCountry(country.getCountryId());
	}
	
	@Test
	public void testCreate() throws CountryAlreadyExistsException
	{
		Mockito.when(repository.findByCountryName(country.getCountryName())).thenReturn(null);
		service.create(country);
		Mockito.verify(repository).create(country);
	}
	
	@Test(expected=CountryAlreadyExistsException.class)
	public void testCreateException() throws CountryAlreadyExistsException
	{
		Mockito.when(repository.findByCountryName(country.getCountryName())).thenReturn(country);
		service.create(country);		
	}
	
	@Test
	public void testUpdate() throws CountryNotFoundException
	{
		Mockito.when(repository.findOneCountry(country.getCountryId())).thenReturn(country);
		service.update(country);
		Mockito.verify(repository).update(country);
	}
		
	@Test(expected=CountryNotFoundException.class)
	public void testUpdateException() throws CountryNotFoundException
	{
		Mockito.when(repository.findOneCountry(country.getCountryId())).thenReturn(null);
		service.update(country);		
	}
	
	@Test
	public void testDelete() throws CountryNotFoundException
	{
		Mockito.when(repository.findOneCountry(country.getCountryId())).thenReturn(country);
		service.delete(country.getCountryId());
		Mockito.verify(repository).delete(country);
	}
		
	@Test(expected=CountryNotFoundException.class)
	public void testDeleteException() throws CountryNotFoundException
	{
		Mockito.when(repository.findOneCountry(country.getCountryId())).thenReturn(null);
		service.delete(country.getCountryId());		
	}
}
