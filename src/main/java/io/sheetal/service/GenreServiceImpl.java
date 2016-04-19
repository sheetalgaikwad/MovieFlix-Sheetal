package io.sheetal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sheetal.entity.Genre;
import io.sheetal.exception.GenreAlreadyExistsException;
import io.sheetal.exception.GenreNotFoundException;
import io.sheetal.repository.GenreRepository;


@Service
@Transactional
public class GenreServiceImpl implements GenreService{

	@Autowired 	
	private GenreRepository repository;
	
	@Override
	public List<Genre> findAllGenres() {
		return repository.findAllGenre();
	}

	@Override
	public Genre findOneGenre(String genreId) throws GenreNotFoundException {
		Genre genre=repository.findOneGenre(genreId);
		if(genre==null)
			throw new GenreNotFoundException();
		else
			return genre;
	}

	@Override
	public Genre create(Genre genre) throws GenreAlreadyExistsException {
		Genre existing=repository.findByGenreType(genre.getGenreType());
		if(existing!=null)
			throw new GenreAlreadyExistsException();
		else
			return repository.create(genre);
	}

	@Override
	public Genre update(Genre genre) throws GenreNotFoundException {
		Genre existing=repository.findOneGenre(genre.getGenreId());
		if(existing==null)
			throw new GenreNotFoundException();
		else
			return repository.update(genre);
	}

	@Override
	public void delete(String genreId) throws GenreNotFoundException {
		Genre existing=repository.findOneGenre(genreId);
		if(existing==null)
			throw new GenreNotFoundException();
		else
			 repository.delete(existing);
		
	}

}
