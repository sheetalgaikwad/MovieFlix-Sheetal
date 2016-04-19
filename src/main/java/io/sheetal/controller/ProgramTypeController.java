package io.sheetal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.sheetal.entity.ProgramType;
import io.sheetal.exception.ProgramTypeAlreadyExistsException;
import io.sheetal.exception.ProgramTypeNotFoundException;
import io.sheetal.service.ProgramTypeService;

@RestController
@RequestMapping(value="/programtypes")
public class ProgramTypeController {

	@Autowired
	private ProgramTypeService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ProgramType> findAllProgramTypes() {
		return service.findAllProgramTypes();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ProgramType findOneProgramType(@PathVariable("id")String programTypeId) throws ProgramTypeNotFoundException {
		return service.findOneProgramType(programTypeId);
	}

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ProgramType create(@RequestBody ProgramType programType) throws ProgramTypeAlreadyExistsException {
		return service.create(programType);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ProgramType update(@PathVariable("id") String programTypeId, @RequestBody ProgramType programType) throws ProgramTypeNotFoundException {
		return service.update(programType);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void delete(@PathVariable("id") String programTypeId) throws ProgramTypeNotFoundException {
		service.delete(programTypeId);
	}

}


