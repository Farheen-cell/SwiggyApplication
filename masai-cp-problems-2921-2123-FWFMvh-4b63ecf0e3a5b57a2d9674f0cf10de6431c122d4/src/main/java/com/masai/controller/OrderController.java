package com.masai.controller;

import com.masai.exception.CustomerException;
import com.masai.exception.OrderException;
import com.masai.exception.RestaurantException;
import com.masai.model.Orders;
import com.masai.model.Status;
import com.masai.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{restaurantId}/{customerId}/{deliveryId}")
    public ResponseEntity<Orders> addOrder(@Valid @RequestBody Orders order,
                                           @PathVariable int restaurantId,
                                           @PathVariable int customerId,
                                           @PathVariable int deliveryId) {
        try {
            Orders addedOrder = orderService.addOrder(order, restaurantId, customerId, deliveryId);
            return new ResponseEntity<>(addedOrder, HttpStatus.CREATED);
        } catch (OrderException | CustomerException | RestaurantException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable int id) {
        try {
            Orders order = orderService.getOrder(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (OrderException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable int id, @RequestParam Status status) {
        try {
            Orders updatedOrder = orderService.updateOrder(id, status);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (OrderException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Orders> deleteOrder(@PathVariable int id) {
        try {
            Orders deletedOrder = orderService.deleteOrder(id);
            return new ResponseEntity<>(deletedOrder, HttpStatus.OK);
        } catch (OrderException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Orders> orderByCustomerId(@PathVariable int customerId) {
        try {
            Orders orders = orderService.orderByCustomerId(customerId);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (OrderException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
