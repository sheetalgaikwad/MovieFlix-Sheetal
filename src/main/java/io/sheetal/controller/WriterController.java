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
import io.sheetal.entity.WriterRoles;
import io.sheetal.exception.WriterAlreadyExistsException;
import io.sheetal.exception.WriterNotFoundException;
import io.sheetal.exception.WriterRoleAlreadyExistsException;
import io.sheetal.service.WriterService;

@RestController
@RequestMapping(value="/writers")
public class WriterController {

	@Autowired
	private WriterService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Writer> findAllWriters() {
		return service.findAllWriters();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Writer findOneWriter(@PathVariable("id")String writerId) throws WriterNotFoundException {
		return service.findOneWriter(writerId);
	}

	@RequestMapping(value="/{writerRoleType}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Writer create(@PathVariable("writerRoleType")String writerRoleType, @RequestBody Writer writer) throws WriterAlreadyExistsException, WriterRoleAlreadyExistsException {
		WriterRoles writerRoles=new WriterRoles();
		writerRoles.setWriterRoleType(writerRoleType);		
		WriterRoleController wrc=new WriterRoleController();		
		writerRoles=wrc.create(writerRoles);
		return service.create(writer);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Writer update(@PathVariable("id") String writerId, @RequestBody Writer writer) throws WriterNotFoundException {
		return service.update(writer);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void delete(@PathVariable("id") String writerId) throws WriterNotFoundException {
		service.delete(writerId);
	}

}


