package com.masai.service;

import com.masai.exception.CustomerException;
import com.masai.exception.RestaurantException;
import com.masai.model.Customer;
import com.masai.model.Restaurant;
import com.masai.repository.RestaurantRepository;
import com.masai.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.spi.RegisterableService;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Override
    public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException {
        try {
            if (restaurantRepository.findById(restaurant.getRestaurantId()).isPresent()) {
                throw new RestaurantException("Restaurant with the same ID already exists");
            }

            return restaurantRepository.save(restaurant);
        } catch (Exception e) {
            throw new RestaurantException("An unexpected error occurred");
        }
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) throws RestaurantException {
        try {
            return restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new RestaurantException("Restaurant not found"));
        } catch (Exception e) {
            throw new RestaurantException("An unexpected error occurred");
        }
    }

    @Override
    public Restaurant updateRestaurantDetails(int customerId, Restaurant restaurant) throws RestaurantException {
        try {
            Restaurant existingRestaurant = restaurantRepository.findById(customerId)
                    .orElseThrow(() -> new RestaurantException("Restaurant not found"));

            existingRestaurant.setRestaurantName(restaurant.getRestaurantName());

            return restaurantRepository.save(existingRestaurant);
        } catch (Exception e) {
            throw new RestaurantException("An unexpected error occurred");
        }
    }

    @Override
    public Restaurant deleteRestaurant(int restaurantId) throws RestaurantException {
        try {
            Restaurant existingRestaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new RestaurantException("Restaurant not found"));

            restaurantRepository.delete(existingRestaurant);

            return existingRestaurant;
        } catch (Exception e) {
            throw new RestaurantException("An unexpected error occurred");
        }
    }

    @Override
    public Restaurant restaurantByNameAndAddress(String name, String address) throws RestaurantException {
        Restaurant restaurant = restaurantRepository.findByRestaurantNameAndAddress(name, address);
        if (restaurant != null) {
            return restaurant;
        } else {
            throw new RestaurantException("Restaurant not found");
        }

    }
}
