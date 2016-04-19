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

@RestController
@RequestMapping(value="/actors")
public class ActorController {

	@Autowired
	private ActorService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Actor> findAllActors() {
		return service.findAllActors();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Actor findOneActor(@PathVariable("id")String actorId) throws ActorNotFoundException {
		return service.findOneActor(actorId);
	}

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Actor create(@RequestBody Actor actor) throws ActorAlreadyExistsException {
		return service.create(actor);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Actor update(@PathVariable("id") String actorId, @RequestBody Actor actor) throws ActorNotFoundException {
		return service.update(actor);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void delete(@PathVariable("id") String actorId) throws ActorNotFoundException {
		service.delete(actorId);
	}

}


