package io.sheetal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.sheetal.entity.Genre;
import io.sheetal.exception.GenreAlreadyExistsException;
import io.sheetal.exception.GenreNotFoundException;
import io.sheetal.service.GenreService;;

@RestController
@RequestMapping(value="/genres")
public class GenreController {

	@Autowired
	private GenreService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Genre> findAllGenres() {
		return service.findAllGenres();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Genre findOneGenre(@PathVariable("id")String genreId) throws GenreNotFoundException {
		return service.findOneGenre(genreId);
	}

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Genre create(@RequestBody Genre genre) throws GenreAlreadyExistsException {
		return service.create(genre);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Genre update(@PathVariable("id") String genreId, @RequestBody Genre genre) throws GenreNotFoundException {
		return service.update(genre);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void delete(@PathVariable("id") String genreId) throws GenreNotFoundException {
		service.delete(genreId);
	}

}

