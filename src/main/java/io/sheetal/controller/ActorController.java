package io.sheetal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.sheetal.entity.Actor;
import io.sheetal.exception.ActorAlreadyExistsException;
import io.sheetal.exception.ActorNotFoundException;
import io.sheetal.service.ActorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/actors")
public class ActorController {

	@Autowired
	private ActorService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "find all actors", notes="finds all actors")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=400, message="bad request"),
			@ApiResponse(code=500, message="internal server error")})	
	public List<Actor> findAllActors() {
		return service.findAllActors();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "find one actor", notes="finds an actors")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="Actor not found"),
			@ApiResponse(code=500, message="internal server error")})	
	public Actor findOneActor(@PathVariable("id")String actorId) throws ActorNotFoundException {
		return service.findOneActor(actorId);
	}

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "create an actor", notes="createss an actor")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=400, message="bad request"),
			@ApiResponse(code=500, message="internal server error")})	
	public Actor create(@RequestBody Actor actor) throws ActorAlreadyExistsException {
		return service.create(actor);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "update actor", notes="updates an actor")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="Actor not found"),
			@ApiResponse(code=400, message="bad request"),
			@ApiResponse(code=500, message="internal server error")})	
	public Actor update(@PathVariable("id") String actorId, @RequestBody Actor actor) throws ActorNotFoundException {
		return service.update(actor);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "delete actor", notes="deletes an actors")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="Actor not found"),
			@ApiResponse(code=500, message="internal server error")})	
	public void delete(@PathVariable("id") String actorId) throws ActorNotFoundException {
		service.delete(actorId);
	}

}


