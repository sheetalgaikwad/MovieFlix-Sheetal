package io.sheetal.service;

import java.util.List;

import io.sheetal.entity.Genre;
import io.sheetal.exception.GenreAlreadyExistsException;
import io.sheetal.exception.GenreNotFoundException;;

public interface GenreService {

	public List<Genre> findAllGenres();
	public Genre findOneGenre(String id) throws GenreNotFoundException;
	public Genre create(Genre genre) throws GenreAlreadyExistsException;
	public Genre update(Genre genre) throws GenreNotFoundException;
	public void delete(String id) throws GenreNotFoundException;
}
