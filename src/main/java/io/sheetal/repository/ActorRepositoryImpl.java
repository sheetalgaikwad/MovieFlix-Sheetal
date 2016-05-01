package io.sheetal.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.sheetal.entity.Actor;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Actor> findAllActors() {
		TypedQuery<Actor> query=em.createQuery("SELECT a from Actor a ORDER BY a.actorName ASC",Actor.class);
		List<Actor> actorList=query.getResultList();
		return actorList;
	}

	@Override
	public Actor findOneActor(String actorId) {
		return em.find(Actor.class, actorId);
	}

	@Override
	public Actor findByActorName(String actorName) {
		TypedQuery<Actor> query=em.createQuery("SELECT a from Actor a where a.actorName=:actorName",Actor.class);
		query.setParameter("actorName",actorName);
		List<Actor> actorList=query.getResultList();
		if(actorList!=null && actorList.size()==1)
			return actorList.get(0);
		else
			return null;
	}

	@Override
	public Actor create(Actor actor) {
		em.persist(actor);
		return actor;
	}

	@Override
	public Actor update(Actor actor) {
		return em.merge(actor);
	}

	@Override
	public void delete(Actor actor) {
		em.remove(actor);
		
	}

}
