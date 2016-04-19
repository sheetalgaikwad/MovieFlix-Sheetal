package io.sheetal.repository;

import java.util.List;

import io.sheetal.entity.Genre;

public interface GenreRepository {

	public List<Genre> findAllGenre();
	public Genre findOneGenre(String id);
	public Genre findByGenreType(String email);
	public Genre create(Genre genre);
	public Genre update(Genre genre);
	public void delete(Genre genre);
}
