package io.sheetal.controller;

import java.util.Date;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/programs")
public class ProgramController {

	@Autowired
	private ProgramService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "find all programs", notes="finds a program")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=500, message="internal server error")})
	public List<Program> findAllPrograms() {
		return service.findAllPrograms();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "find one program", notes="finds a program")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="program not found"),
			@ApiResponse(code=500, message="internal server error")})
	public Program findOneProgram(@PathVariable("id")String programId) throws ProgramNotFoundException {
		return service.findOneProgram(programId);
	}
	
	@RequestMapping(value="/genre/{genre}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "find programs by genre", notes="finds program by genre")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=500, message="internal server error")})
	public List<Program> findByGenre(@PathVariable("genre")String genre){
		return service.findByGenre(genre);
	}
	

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "create a program", notes="creates a program")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=400, message="bad request"),
			@ApiResponse(code=500, message="internal server error")})
	public Program create(@RequestBody Program program) throws ProgramAlreadyExistsException {
		return service.create(program);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "update program", notes="updates a program")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="program not found"),
			@ApiResponse(code=500, message="internal server error")})
	public Program update(@PathVariable("id") String programId, @RequestBody Program program) throws ProgramNotFoundException {
		return service.update(program);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "delete one program", notes="delets a program")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="program not found"),
			@ApiResponse(code=500, message="internal server error")})
	public void delete(@PathVariable("id") String programId) throws ProgramNotFoundException {
		service.delete(programId);
	}

}


