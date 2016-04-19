package io.sheetal.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.sheetal.entity.Genre;


@Repository
public class GenreRepositoryImp implements GenreRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Genre> findAllGenre() {
		TypedQuery<Genre> query=em.createQuery("SELECT g from Genre g ORDER BY g.genreType ASC",Genre.class);
		List<Genre> genreList=query.getResultList();
		return genreList;
	}

	@Override
	public Genre findOneGenre(String id) {
		return em.find(Genre.class, id);
	}

	@Override
	public Genre findByGenreType(String genreType) {
		TypedQuery<Genre> query=em.createQuery("SELECT g from Genre g where g.genreType=:genreType",Genre.class);
		query.setParameter("genreType",genreType);
		List<Genre> genreList=query.getResultList();
		if(genreList!=null && genreList.size()==1)
			return genreList.get(0);
		else
			return null;
	}

	@Override
	public Genre create(Genre genre) {
		em.persist(genre);
		return genre;
	}

	@Override
	public Genre update(Genre genre) {
		return em.merge(genre);
	}

	@Override
	public void delete(Genre genre) {
		em.remove(genre);
		
	}

}
