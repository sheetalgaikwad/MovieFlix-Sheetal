package io.sheetal.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sheetal.entity.Rating;
import io.sheetal.exception.RatingAlreadyExistsException;
import io.sheetal.exception.RatingNotFoundException;
import io.sheetal.repository.RatingRepository;


@Service
@Transactional
public class RatingServiceImpl implements RatingService {

	@Autowired 	
	private RatingRepository repository;
	
	@Override
	public List<Rating> findAllRatings() {
		return repository.findAllRatings();
	}

	@Override
	public Rating findOneRating(String id) throws RatingNotFoundException {
		Rating rating=repository.findOneRating(id);
		if(rating==null)
			throw new RatingNotFoundException();
		else
			return rating;			
	}

	@Override
	public Rating findByRatingName(String ratingType) throws RatingNotFoundException {
		Rating rating=repository.findByRatingType(ratingType);
		if(rating==null)
			throw new RatingNotFoundException();
		else
			return rating;	
	}

	@Override
	public Rating create(Rating rating) throws RatingAlreadyExistsException {
		Rating existing=repository.findByRatingType(rating.getRatingType());
		if(existing!=null)
			throw new RatingAlreadyExistsException();
		else
			return repository.create(rating);	
	}

	@Override
	public Rating update(Rating rating) throws RatingNotFoundException {
		Rating existing=repository.findOneRating(rating.getRatingId());
		if(existing==null)
			throw new RatingNotFoundException();
		else
			return repository.update(rating);	
	}

	@Override
	public void delete(String ratingId) throws RatingNotFoundException {
		Rating existing=repository.findOneRating(ratingId);
		if(existing==null)
			throw new RatingNotFoundException();
		else
			repository.delete(existing);
		
	}

}
