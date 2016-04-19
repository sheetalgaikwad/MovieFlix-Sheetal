package io.sheetal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.sheetal.entity.Awards;

import io.sheetal.exception.AwardNotFoundException;
import io.sheetal.service.AwardsService;

@RestController
@RequestMapping(value="/awards")
public class AwardsController {

	@Autowired
	private AwardsService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Awards> findAllAwards() {
		return service.findAllAwards();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Awards findOneAward(@PathVariable("id")String awardsId) throws AwardNotFoundException {
		return service.findOneAward(awardsId);
	}

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Awards create(@RequestBody Awards awards) {
		return service.create(awards);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Awards update(@PathVariable("id") String awardsId, @RequestBody Awards awards) throws AwardNotFoundException {
		return service.update(awards);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void delete(@PathVariable("id") String awardsId) throws AwardNotFoundException {
		service.delete(awardsId);
	}

}


