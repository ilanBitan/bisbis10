package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.model.Dish;
import com.att.tdp.bisbis10.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes(@PathVariable Long restaurantId) {
        List<Dish> dishes = dishService.getDishesByRestaurant(restaurantId);
        if (dishes != null && !dishes.isEmpty()) {
            return ResponseEntity.ok(dishes);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{dishId}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long restaurantId, @PathVariable Long dishId) {
        List<Dish> dishes = dishService.getDishesByRestaurant(restaurantId);
        return dishes.stream()
                .filter(d -> d.getId().equals(dishId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Dish> addDish(@PathVariable Long restaurantId, @RequestBody Dish dish) {
        boolean added = dishService.addDish(restaurantId, dish);
        if (added) {
            return ResponseEntity.ok(dish);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{dishId}")
    public ResponseEntity<Dish> updateDish(@PathVariable Long restaurantId, @PathVariable Long dishId, @RequestBody Dish dish) {
        boolean updated = dishService.updateDish(restaurantId, dishId, dish);
        if (updated) {
            return ResponseEntity.ok(dish);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{dishId}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long restaurantId, @PathVariable Long dishId) {
        boolean deleted = dishService.deleteDish(restaurantId, dishId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
