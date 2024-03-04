package com.masai.repository;

import com.masai.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

    Optional<Orders> findByCustomerId(int customerId);
}
