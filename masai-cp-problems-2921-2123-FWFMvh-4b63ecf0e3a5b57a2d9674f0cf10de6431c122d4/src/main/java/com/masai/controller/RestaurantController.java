package com.masai.controller;

import com.masai.exception.RestaurantException;
import com.masai.model.Restaurant;
import com.masai.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/restaurants")
@Validated
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody Restaurant restaurant) {
        try {
            Restaurant addedRestaurant = restaurantService.addRestaurant(restaurant);
            return new ResponseEntity<>(addedRestaurant, HttpStatus.CREATED);
        } catch (RestaurantException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable int id) {
        try {
            Restaurant restaurant = restaurantService.getRestaurant(id);
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch (RestaurantException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurantDetails(@PathVariable int id, @RequestBody Restaurant restaurant) {
        try {
            Restaurant updatedRestaurant = restaurantService.updateRestaurantDetails(id, restaurant);
            return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
        } catch (RestaurantException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable int id) {
        try {
            Restaurant deletedRestaurant = restaurantService.deleteRestaurant(id);
            return new ResponseEntity<>(deletedRestaurant, HttpStatus.OK);
        } catch (RestaurantException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/bynameandaddress")
    public ResponseEntity<Restaurant> restaurantByNameAndAddress(@RequestParam String name, @RequestParam String address) {
        try {
            Restaurant restaurant = restaurantService.restaurantByNameAndAddress(name, address);
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch (RestaurantException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
