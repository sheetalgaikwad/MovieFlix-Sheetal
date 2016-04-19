package io.sheetal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.sheetal.entity.WriterRoles;
import io.sheetal.exception.WriterRoleAlreadyExistsException;
import io.sheetal.exception.WriterRoleNotFoundException;
import io.sheetal.service.WriterRoleService;

@RestController
@RequestMapping(value="/writerroles")
public class WriterRoleController {

	@Autowired
	private WriterRoleService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<WriterRoles> findAllWriterRoles() {
		return service.findAllWriterRoles();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public WriterRoles findOneWriterRole(@PathVariable("id")String writerRoleId) throws WriterRoleNotFoundException {
		return service.findOneWriterRole(writerRoleId);
	}

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public WriterRoles create(@RequestBody WriterRoles writerRole) throws WriterRoleAlreadyExistsException {
		return service.create(writerRole);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public WriterRoles update(@PathVariable("id") String writerRoleId, @RequestBody WriterRoles writerRole) throws WriterRoleNotFoundException {
		return service.update(writerRole);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void delete(@PathVariable("id") String writerRoleId) throws WriterRoleNotFoundException {
		service.delete(writerRoleId);
	}

}


