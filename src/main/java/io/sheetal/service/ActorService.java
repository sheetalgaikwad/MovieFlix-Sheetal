package io.sheetal.service;

import java.util.List;

import io.sheetal.entity.Actor;
import io.sheetal.exception.ActorAlreadyExistsException;
import io.sheetal.exception.ActorNotFoundException;

public interface ActorService {

	public List<Actor> findAllActors();
	public Actor findOneActor(String actorId) throws ActorNotFoundException;
	public Actor create(Actor actor) throws ActorAlreadyExistsException;
	public Actor update(Actor actor) throws ActorNotFoundException;
	public void delete(String actorId) throws ActorNotFoundException;
}
