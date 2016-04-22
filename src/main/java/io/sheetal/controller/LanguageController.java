package io.sheetal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.sheetal.entity.Language;
import io.sheetal.exception.LanguageAlreadyExistsException;
import io.sheetal.exception.LanguageNotFoundException;
import io.sheetal.service.LanguageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/languages")
public class LanguageController {

	@Autowired
	private LanguageService service;	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "find all languages", notes="finds all languages")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=500, message="internal server error")})
	public List<Language> findAllLanguages() {
		return service.findAllLanguages();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "find one language", notes="finds a language")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="language not found"),
			@ApiResponse(code=500, message="internal server error")})
	public Language findOneLanguage(@PathVariable("id")String languageId) throws LanguageNotFoundException {
		return service.findOneLanguage(languageId);
	}

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "create one language", notes="creates a language")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=400, message="bad reuest"),
			@ApiResponse(code=500, message="internal server error")})
	public Language create(@RequestBody Language language) throws LanguageAlreadyExistsException {
		return service.create(language);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "update one language", notes="updates a language")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="language not found"),
			@ApiResponse(code=500, message="internal server error")})
	public Language update(@PathVariable("id") String languageId, @RequestBody Language language) throws LanguageNotFoundException {
		return service.update(language);
	} 

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "delete one language", notes="deletes a language")
	@ApiResponses(value={
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="language not found"),
			@ApiResponse(code=500, message="internal server error")})
	public void delete(@PathVariable("id") String languageId) throws LanguageNotFoundException {
		service.delete(languageId);
	}

}


