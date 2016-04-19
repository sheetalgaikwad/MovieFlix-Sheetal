package io.sheetal.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.sheetal.entity.Director;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Director> findAllDirectors() {
		TypedQuery<Director> query=em.createQuery("SELECT d from Director d ORDER BY d.directorName ASC",Director.class);
		List<Director> directorList=query.getResultList();
		return directorList;
	}

	@Override
	public Director findOneDirector(String directorId) {
		return em.find(Director.class, directorId);
	}

	@Override
	public Director findByDirectorName(String directorName) {
		TypedQuery<Director> query=em.createQuery("SELECT d from Director d where d.directorName=:directorName",Director.class);
		query.setParameter("directorName",directorName);
		List<Director> directorList=query.getResultList();
		if(directorList!=null && directorList.size()==1)
			return directorList.get(0);
		else
			return null;
	}

	@Override
	public Director create(Director director) {
		em.persist(director);
		return director;
	}

	@Override
	public Director update(Director director) {
		return em.merge(director);
	}

	@Override
	public void delete(Director director) {
		em.remove(director);
		
	}

}
