package io.sheetal.service;

import java.util.List;

import io.sheetal.entity.Director;
import io.sheetal.exception.DirectorAlreadyExistsException;
import io.sheetal.exception.DirectorNotFoundException;

public interface DirectorService {

	public List<Director> findAllDirectors();
	public Director findOneDirector(String directorId) throws DirectorNotFoundException;
	public Director create(Director director) throws DirectorAlreadyExistsException;
	public Director update(Director director) throws DirectorNotFoundException;
	public void delete(String directorId) throws DirectorNotFoundException;
}
