package io.sheetal.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.sheetal.entity.Rating;

@Repository
public class RatingRepositoryImpl implements RatingRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Rating> findAllRatings() {
		TypedQuery<Rating> query=em.createQuery("SELECT r from Rating r ORDER BY r.ratingType ASC",Rating.class);
		List<Rating> ratingList=query.getResultList();
		return ratingList;
	}

	@Override
	public Rating findOneRating(String id) {
		if(id==null)
			System.out.println("--------id is null-------");
		return em.find(Rating.class, id);
	}

	@Override
	public Rating findByRatingType(String ratingType) {
		TypedQuery<Rating> query=em.createQuery("SELECT r from Rating r where r.ratingType=:ratingType",Rating.class);
		query.setParameter("ratingType",ratingType);
		List<Rating> ratingList=query.getResultList();
		if(ratingList!=null && ratingList.size()==1)
			return ratingList.get(0);
		else
			return null;
	}

	@Override
	public Rating create(Rating rating) {
		em.persist(rating);
		return rating;
	}

	@Override
	public Rating update(Rating rating) {
		return em.merge(rating);
	}

	@Override
	public void delete(Rating rating) {
		em.remove(rating);		
	}

}
