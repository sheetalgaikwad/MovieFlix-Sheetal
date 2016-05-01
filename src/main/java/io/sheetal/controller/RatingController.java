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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/ratings")
public class RatingController {
	
	@Autowired
	private RatingService service;		
		
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "find all ratings", notes="finds all ratings")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=500, message="internal server error")})
	public List<Rating> findAllRatings() {
		return service.findAllRatings();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "find one rating", notes="find one rating")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="rating not found"),
			@ApiResponse(code=500, message="internal server error")})
	public Rating findOne(@PathVariable("id")String id) throws RatingNotFoundException {
		return service.findOneRating(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "create a rating", notes= "creates a rating")			
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=400, message="bad request"),
			@ApiResponse(code=500, message="internal server error")})
	public Rating create(@RequestBody Rating rating) throws RatingAlreadyExistsException {
		return service.create(rating);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "update one rating", notes="updates one rating")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="rating not found"),
			@ApiResponse(code=500, message="internal server error")})
	public Rating update(@PathVariable("id") String id, @RequestBody Rating rating) throws RatingNotFoundException   {
		return service.update(rating);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "delete one rating", notes="deletes one rating")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="rating not found"),
			@ApiResponse(code=500, message="internal server error")})
	public void delete(@PathVariable("id") String id) throws RatingNotFoundException {
		service.delete(id);
	}

}
