package io.sheetal.service;

import java.util.List;

import io.sheetal.entity.Awards;
import io.sheetal.exception.AwardNotFoundException;

public interface AwardsService {

	public List<Awards> findAllAwards();
	public Awards findOneAward(String awardId) throws AwardNotFoundException;
	public Awards create(Awards awards); 
	public Awards update(Awards awards) throws AwardNotFoundException;
	public void delete(String awardsId) throws AwardNotFoundException;
}
