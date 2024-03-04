package com.masai.controller;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        try {
            Customer addedCustomer = customerService.addCustomer(customer);
            return new ResponseEntity<>(addedCustomer, HttpStatus.CREATED);
        } catch (CustomerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        try {
            Customer customer = customerService.getCustomer(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (CustomerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomerName(@Valid @PathVariable int id, @RequestParam String customerName) {
        try {
            Customer updatedCustomer = customerService.updateCustomerName(id, customerName);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (CustomerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable int id) {
        try {
            Customer deletedCustomer = customerService.deleteCustomer(id);
            return new ResponseEntity<>(deletedCustomer, HttpStatus.OK);
        } catch (CustomerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byname/{name}")
    public ResponseEntity<Customer> getCustomerByName(@Valid @PathVariable String name) {
        try {
            Customer customer = customerService.customerByName(name);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (CustomerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
