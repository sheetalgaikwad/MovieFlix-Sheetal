package io.sheetal.service;

import java.util.List;

import io.sheetal.entity.Rating;
import io.sheetal.exception.RatingAlreadyExistsException;
import io.sheetal.exception.RatingNotFoundException;

public interface RatingService {

	public List<Rating> findAllRatings();
	public Rating findOneRating(String id) throws RatingNotFoundException;
	public Rating findByRatingName(String ratingType) throws RatingNotFoundException;
	public Rating create(Rating rating) throws RatingAlreadyExistsException;
	public Rating update(Rating rating)throws RatingNotFoundException;
	public void delete(String ratingId)throws RatingNotFoundException;
}
