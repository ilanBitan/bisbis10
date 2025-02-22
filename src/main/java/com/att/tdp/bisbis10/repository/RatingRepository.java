package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query("SELECT AVG(r.ratingValue) FROM Rating r WHERE r.restaurant.id = :restaurantId")
    Double calculateAverageRatingByRestaurantId(Long restaurantId);
}
