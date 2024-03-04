package com.masai.service;

import com.masai.exception.CustomerException;
import com.masai.exception.OrderException;
import com.masai.exception.RestaurantException;
import com.masai.model.*;
import com.masai.repository.CustomerRepository;
import com.masai.repository.DeliveryRepository;
import com.masai.repository.OrderRepository;
import com.masai.repository.RestaurantRepository;
import com.masai.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Orders addOrder(Orders order, int restaurantId, int customerId, int deliveryId) throws OrderException, CustomerException, RestaurantException {


        if (orderRepository.findById(order.getOrderId()).isPresent()) {
            throw new OrderException("Order with the same ID already exists");
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Optional<DeliveryPartner> optionalDelivery = deliveryRepository.findById(deliveryId);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);

        if (optionalCustomer.isPresent() && optionalRestaurant.isPresent() && optionalDelivery.isPresent()) {
            Customer customer = optionalCustomer.get();
            Restaurant restaurant = optionalRestaurant.get();
            DeliveryPartner deliveryPartner = optionalDelivery.get();

            order.setCustomer(customer);
            order.setRestaurant(restaurant);
            order.setDeliveryPartner(deliveryPartner);

            Orders addedOrder = orderRepository.save(order);

            customer.getOrders().add(addedOrder);
            restaurant.getOrders().add(addedOrder);
            deliveryPartner.getOrders().add(addedOrder);

            return addedOrder;
        } else {
            throw new OrderException("Invalid customer, restaurant, or delivery partner");
        }
    }

    @Override
    public Orders getOrder(int orderId) throws OrderException {
        Optional<Orders> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            throw new OrderException("Order not found");
        }
    }

    @Override
    public Orders updateOrder(int orderId, Status status) throws OrderException {
        Optional<Orders> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Orders existingOrder = optionalOrder.get();
            existingOrder.setStatus(status);
            return orderRepository.save(existingOrder);
        } else {
            throw new OrderException("Order not found");
        }
    }

    @Override
    public Orders deleteOrder(int orderId) throws OrderException {
        Optional<Orders> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Orders existingOrder = optionalOrder.get();
            orderRepository.delete(existingOrder);
            return existingOrder;
        } else {
            throw new OrderException("Order not found");
        }
    }

    @Override
    public Orders orderByCustomerId(int customerId) throws OrderException {
        Optional<Orders> orders = orderRepository.findByCustomerId(customerId);
        if (orders.isPresent()) {
            return orders.get();
        } else {
            throw new OrderException("Order not found " );
        }
    }
}
