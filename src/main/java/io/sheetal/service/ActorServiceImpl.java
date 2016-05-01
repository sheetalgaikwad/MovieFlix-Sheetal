package io.sheetal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sheetal.entity.Actor;
import io.sheetal.exception.ActorAlreadyExistsException;
import io.sheetal.exception.ActorNotFoundException;

import io.sheetal.repository.ActorRepository;

@Service
@Transactional
public class ActorServiceImpl implements ActorService{

	@Autowired 	
	private ActorRepository repository;
	
	@Override
	public List<Actor> findAllActors() {
		return repository.findAllActors();
	}

	@Override
	public Actor findOneActor(String actorId) throws ActorNotFoundException {
		Actor actor=repository.findOneActor(actorId);
		if(actor==null)
			throw new ActorNotFoundException();
		else
			return actor;
	}

	@Override
	public Actor create(Actor actor) throws ActorAlreadyExistsException {
		Actor existingActor=repository.findByActorName(actor.getActorName());
		if(existingActor!=null)
			throw new ActorAlreadyExistsException();
		else
			return repository.create(actor);
	}

	@Override
	public Actor update(Actor actor) throws ActorNotFoundException {
		Actor existingActor=repository.findOneActor(actor.getActorId());
		if(existingActor==null)
			throw new ActorNotFoundException();
		else
			return repository.update(actor);
	}

	@Override
	public void delete(String actorId) throws ActorNotFoundException {
		Actor existingActor=repository.findOneActor(actorId);
		if(existingActor==null)
			throw new ActorNotFoundException();
		else
			repository.delete(existingActor);
		
	}
}
