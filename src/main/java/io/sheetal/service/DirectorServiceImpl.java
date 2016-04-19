package io.sheetal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sheetal.entity.Director;
import io.sheetal.exception.DirectorAlreadyExistsException;
import io.sheetal.exception.DirectorNotFoundException;

import io.sheetal.repository.DirectorRepository;

@Service
@Transactional
public class DirectorServiceImpl implements DirectorService{

	@Autowired 	
	private DirectorRepository repository;
	
	@Override
	public List<Director> findAllDirectors() {
		return repository.findAllDirectors();
	}

	@Override
	public Director findOneDirector(String directorId) throws DirectorNotFoundException {
		Director director=repository.findOneDirector(directorId);
		if(director==null)
			throw new DirectorNotFoundException();
		else
			return director;
	}

	@Override
	public Director create(Director director) throws DirectorAlreadyExistsException {
		Director existingDirector=repository.findByDirectorName(director.getDirectorName());
		if(existingDirector!=null)
			throw new DirectorAlreadyExistsException();
		else
			return repository.create(director);
	}

	@Override
	public Director update(Director director) throws DirectorNotFoundException {
		Director existingDirector=repository.findOneDirector(director.getDirectorId());
		if(existingDirector==null)
			throw new DirectorNotFoundException();
		else
			return repository.update(director);
	}

	@Override
	public void delete(String directorId) throws DirectorNotFoundException {
		Director existingDirector=repository.findOneDirector(directorId);
		if(existingDirector==null)
			throw new DirectorNotFoundException();
		else
			repository.delete(existingDirector);
		
	}
}
