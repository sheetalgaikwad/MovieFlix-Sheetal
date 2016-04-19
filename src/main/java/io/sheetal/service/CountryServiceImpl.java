package io.sheetal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sheetal.entity.Country;
import io.sheetal.exception.CountryAlreadyExistsException;
import io.sheetal.exception.CountryNotFoundException;

import io.sheetal.repository.CountryRepository;

@Service
@Transactional
public class CountryServiceImpl implements CountryService{

	@Autowired 	
	private CountryRepository repository;
	
	@Override
	public List<Country> findAllCountries() {
		return repository.findAllCountries();
	}

	@Override
	public Country findOneCountry(String countryId) throws CountryNotFoundException {
		Country country=repository.findOneCountry(countryId);
		if(country==null)
			throw new CountryNotFoundException();
		else
			return country;
	}

	@Override
	public Country create(Country country) throws CountryAlreadyExistsException {
		Country existingCountry=repository.findByCountryName(country.getCountryName());
		if(existingCountry!=null)
			throw new CountryAlreadyExistsException();
		else
			return repository.create(country);
	}

	@Override
	public Country update(Country country) throws CountryNotFoundException {
		Country existingCountry=repository.findOneCountry(country.getCountryId());
		if(existingCountry==null)
			throw new CountryNotFoundException();
		else
			return repository.update(country);
	}

	@Override
	public void delete(String countryId) throws CountryNotFoundException {
		Country existingCountry=repository.findOneCountry(countryId);
		if(existingCountry==null)
			throw new CountryNotFoundException();
		else
			repository.delete(existingCountry);
		
	}
}
