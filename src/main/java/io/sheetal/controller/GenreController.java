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
import io.sheetal.service.GenreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;;

@RestController
@RequestMapping(value="/genres")
public class GenreController {

	@Autowired
	private GenreService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "find all genres", notes="finds all genre")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=500, message="internal server error")})
	public List<Genre> findAllGenres() {
		return service.findAllGenres();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "find one genre", notes="finds a genre")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="genre not found"),
			@ApiResponse(code=500, message="internal server error")})
	public Genre findOneGenre(@PathVariable("id")String genreId) throws GenreNotFoundException {
		return service.findOneGenre(genreId);
	}

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "create one genre", notes="creates a genre")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=400, message="bad request"),
			@ApiResponse(code=500, message="internal server error")})
	public Genre create(@RequestBody Genre genre) throws GenreAlreadyExistsException {
		return service.create(genre);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "update genre", notes="update a genre")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="genre not found"),
			@ApiResponse(code=400, message="bad request"),
			@ApiResponse(code=500, message="internal server error")})
	public Genre update(@PathVariable("id") String genreId, @RequestBody Genre genre) throws GenreNotFoundException {
		return service.update(genre);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "delete one genre", notes="deletes a genre")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="genre not found"),
			@ApiResponse(code=500, message="internal server error")})
	public void delete(@PathVariable("id") String genreId) throws GenreNotFoundException {
		service.delete(genreId);
	}

}

