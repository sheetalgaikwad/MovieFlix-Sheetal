package io.sheetal.repository;

import java.util.List;

import io.sheetal.entity.Country;

public interface CountryRepository {

	public List<Country> findAllCountries();
	public Country findOneCountry(String countryId);
	public Country findByCountryName(String countryName);
	public Country create(Country country);
	public Country update(Country country);
	public void delete(Country country);
}
