package io.sheetal.repository;

import java.util.List;

import io.sheetal.entity.Rating;

public interface RatingRepository {

	public List<Rating> findAllRatings();
	public Rating findOneRating(String id);
	public Rating findByRatingType(String ratingType);
	public Rating create(Rating rating);
	public Rating update(Rating rating);
	public void delete(Rating rating);
}
