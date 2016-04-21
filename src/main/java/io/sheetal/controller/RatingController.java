package io.sheetal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.sheetal.entity.Rating;
import io.sheetal.exception.RatingAlreadyExistsException;
import io.sheetal.exception.RatingNotFoundException;
import io.sheetal.service.RatingService;

@RestController
@RequestMapping(value="/ratings")
public class RatingController {
	
	@Autowired
	private RatingService service;		
		
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Rating> findAllRatings() {
		return service.findAllRatings();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Rating findOne(@PathVariable("id")String id) throws RatingNotFoundException {
		return service.findOneRating(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Rating create(@RequestBody Rating rating) throws RatingAlreadyExistsException {
		return service.create(rating);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Rating update(@PathVariable("id") String id, @RequestBody Rating rating) throws RatingNotFoundException   {
		return service.update(rating);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void delete(@PathVariable("id") String id) throws RatingNotFoundException {
		service.delete(id);
	}

}
