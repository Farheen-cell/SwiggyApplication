package com.masai.service;

import com.masai.exception.CustomerException;
import com.masai.exception.OrderException;
import com.masai.exception.RestaurantException;
import com.masai.model.Orders;
import com.masai.model.Status;

public interface OrderService {

    Orders addOrder(Orders order, int restaurantId, int customerId, int deliveryId) throws OrderException, CustomerException, RestaurantException;
    Orders getOrder(int orderId) throws OrderException;
    Orders updateOrder(int orderId, Status status) throws OrderException;
    Orders deleteOrder(int orderId) throws OrderException;
    Orders orderByCustomerId(int customerId) throws OrderException;
}
