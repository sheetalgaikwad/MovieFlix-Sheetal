package io.sheetal.repository;

import java.util.List;

import io.sheetal.entity.Director;

public interface DirectorRepository {

	public List<Director> findAllDirectors();
	public Director findOneDirector(String directorId);
	public Director findByDirectorName(String directorName);
	public Director create(Director director);
	public Director update(Director director);
	public void delete(Director director);
}
