package io.sheetal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.sheetal.entity.Country;
import io.sheetal.exception.CountryAlreadyExistsException;
import io.sheetal.exception.CountryNotFoundException;
import io.sheetal.service.CountryService;

@RestController
@RequestMapping(value="/countries")
public class CountryController {

	@Autowired
	private CountryService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Country> findAllCountries() {
		return service.findAllCountries();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Country findOneCountry(@PathVariable("id")String countryId) throws CountryNotFoundException {
		return service.findOneCountry(countryId);
	}

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Country create(@RequestBody Country country) throws CountryAlreadyExistsException {
		return service.create(country);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Country update(@PathVariable("id") String countryId, @RequestBody Country country) throws CountryNotFoundException {
		return service.update(country);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void delete(@PathVariable("id") String countryId) throws CountryNotFoundException {
		service.delete(countryId);
	}

}


