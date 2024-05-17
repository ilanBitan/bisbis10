package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.Dish;
import com.att.tdp.bisbis10.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    List<Dish> findByRestaurant(Restaurant restaurant);

    @Query("SELECT d FROM Dish d WHERE d.id = :dishId AND d.restaurant = :restaurant")
    Optional<Dish> findByIdAndRestaurant(Long dishId, Restaurant restaurant);
}
