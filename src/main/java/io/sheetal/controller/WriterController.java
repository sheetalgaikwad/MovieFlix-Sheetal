package io.sheetal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.sheetal.entity.Writer;
import io.sheetal.exception.WriterAlreadyExistsException;
import io.sheetal.exception.WriterNotFoundException;
import io.sheetal.service.WriterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/writers")
public class WriterController {

	@Autowired
	private WriterService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "find all writers", notes="finds all writer")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=500, message="internal server error")})
	public List<Writer> findAllWriters() {
		return service.findAllWriters();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "find one writer", notes="finds one writer")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="writer not found"),
			@ApiResponse(code=500, message="internal server error")})
	public Writer findOneWriter(@PathVariable("id")String writerId) throws WriterNotFoundException {
		return service.findOneWriter(writerId);
	}

	@RequestMapping(value="/{writerRoleType}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "create writer", notes="creates one writer")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=400, message="bad request"),
			@ApiResponse(code=500, message="internal server error")})
	public Writer create(@PathVariable("writerRoleType")String writerRoleType, @RequestBody Writer writer) throws WriterAlreadyExistsException {
		return service.create(writer);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "update one writer", notes="updates one writer")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="writer not found"),
			@ApiResponse(code=500, message="internal server error")})
	public Writer update(@PathVariable("id") String writerId, @RequestBody Writer writer) throws WriterNotFoundException {
		return service.update(writer);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "delete one writer", notes="deletes one writer")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="writer not found"),
			@ApiResponse(code=500, message="internal server error")})
	public void delete(@PathVariable("id") String writerId) throws WriterNotFoundException {
		service.delete(writerId);
	}

}


