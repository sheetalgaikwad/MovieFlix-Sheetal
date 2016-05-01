package io.sheetal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sheetal.entity.Awards;

import io.sheetal.exception.AwardNotFoundException;

import io.sheetal.repository.AwardsRepository;

@Service
@Transactional
public class AwardsServiceImpl implements AwardsService{

	@Autowired 	
	private AwardsRepository repository;
	
	@Override
	public List<Awards> findAllAwards() {
		return repository.findAllAwards();
	}

	@Override
	public Awards findOneAward(String awardsId) throws AwardNotFoundException {
		Awards awards=repository.findOneAward(awardsId);
		if(awards==null)
			throw new AwardNotFoundException();
		else
			return awards;
	}

	@Override
	public Awards create(Awards awards)  {
		return repository.create(awards);		
	}

	@Override
	public Awards update(Awards awards) throws AwardNotFoundException {
		Awards existingAwards=repository.findOneAward(awards.getAwardsId());
		if(existingAwards==null)
			throw new AwardNotFoundException();
		else
			return repository.update(awards);
	}

	@Override
	public void delete(String awardsId) throws AwardNotFoundException {
		Awards existingAwards=repository.findOneAward(awardsId);
		if(existingAwards==null)
			throw new AwardNotFoundException();
		else
			repository.delete(existingAwards);
		
	}
}
