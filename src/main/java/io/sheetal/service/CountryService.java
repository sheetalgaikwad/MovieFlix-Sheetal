package io.sheetal.service;

import java.util.List;

import io.sheetal.entity.Country;
import io.sheetal.exception.CountryAlreadyExistsException;
import io.sheetal.exception.CountryNotFoundException;

public interface CountryService {

	public List<Country> findAllCountries();
	public Country findOneCountry(String countryId) throws CountryNotFoundException;
	public Country create(Country country) throws CountryAlreadyExistsException;
	public Country update(Country country) throws CountryNotFoundException;
	public void delete(String countryId) throws CountryNotFoundException;
}
