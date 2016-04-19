package io.sheetal.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.sheetal.entity.Awards;

@Repository
public class AwardsRepositoryImpl implements AwardsRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Awards> findAllAwards() {
		TypedQuery<Awards> query=em.createQuery("SELECT a from Awards a ORDER BY a.oscarWins DESC",Awards.class);
		List<Awards> awardsList=query.getResultList();
		return awardsList;
	}

	@Override
	public Awards findOneAward(String awardId) {
		return em.find(Awards.class, awardId);
	}

	@Override
	public Awards create(Awards award) {
		em.persist(award);
		return award;
	}

	@Override
	public Awards update(Awards award) {
		return em.merge(award);
	}

	@Override
	public void delete(Awards award) {
		em.remove(award);
		
	}

}
