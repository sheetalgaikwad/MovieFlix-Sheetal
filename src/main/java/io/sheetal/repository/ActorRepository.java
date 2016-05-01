package io.sheetal.repository;

import java.util.List;

import io.sheetal.entity.Actor;

public interface ActorRepository {

	public List<Actor> findAllActors();
	public Actor findOneActor(String actorId);
	public Actor findByActorName(String actorName);
	public Actor create(Actor actor);
	public Actor update(Actor actor);
	public void delete(Actor actor);
}
