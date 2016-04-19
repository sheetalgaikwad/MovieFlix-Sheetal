package io.sheetal.repository;

import java.util.List;

import io.sheetal.entity.Awards;

public interface AwardsRepository {

	public List<Awards> findAllAwards();
	public Awards findOneAward(String AwardId);
	public Awards create(Awards award);
	public Awards update(Awards award);
	public void delete(Awards award);
}
