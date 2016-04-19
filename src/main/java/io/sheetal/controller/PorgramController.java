package io.sheetal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.sheetal.entity.Program;
import io.sheetal.exception.ProgramAlreadyExistsException;
import io.sheetal.exception.ProgramNotFoundException;
import io.sheetal.service.ProgramService;

@RestController
@RequestMapping(value="/programs")
public class PorgramController {

	@Autowired
	private ProgramService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Program> findAllPrograms() {
		return service.findAllPrograms();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Program findOneProgram(@PathVariable("id")String programId) throws ProgramNotFoundException {
		return service.findOneProgram(programId);
	}

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Program create(@RequestBody Program program) throws ProgramAlreadyExistsException {
		return service.create(program);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Program update(@PathVariable("id") String programId, @RequestBody Program program) throws ProgramNotFoundException {
		return service.update(program);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void delete(@PathVariable("id") String programId) throws ProgramNotFoundException {
		service.delete(programId);
	}

}


