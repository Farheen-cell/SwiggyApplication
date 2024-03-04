package com.masai.repository;

import com.masai.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findByRestaurantNameAndAddress(String name, String address);
}
