package io.sheetal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.sheetal.entity.Director;
import io.sheetal.exception.DirectorAlreadyExistsException;
import io.sheetal.exception.DirectorNotFoundException;
import io.sheetal.service.DirectorService;

@RestController
@RequestMapping(value="/directors")
public class DirectorController {

	@Autowired
	private DirectorService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Director> findAllDirectors() {
		return service.findAllDirectors();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Director findOneDirector(@PathVariable("id")String directorId) throws DirectorNotFoundException {
		return service.findOneDirector(directorId);
	}

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Director create(@RequestBody Director director) throws DirectorAlreadyExistsException {
		return service.create(director);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Director update(@PathVariable("id") String directorId, @RequestBody Director director) throws DirectorNotFoundException {
		return service.update(director);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void delete(@PathVariable("id") String directorId) throws DirectorNotFoundException {
		service.delete(directorId);
	}

}


