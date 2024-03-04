package com.masai.service;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;
import com.masai.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    @Override
    public Customer addCustomer(Customer customer) throws CustomerException {
        if (customer == null) {
            throw new CustomerException("Customer cannot be null");
        }

        if (customerRepository.findById(customer.getCustomerId()).isPresent()) {
            throw new CustomerException("Customer with the same ID already exists");
        }

        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(int id) throws CustomerException {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new CustomerException("Customer not found");
        }
    }

    @Override
    public Customer updateCustomerName(int customerId, String customerName) throws CustomerException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setUsername(customerName);
            return customerRepository.save(existingCustomer);
        } else {
            throw new CustomerException("Customer not found");
        }
    }

    @Override
    public Customer deleteCustomer(int customerId) throws CustomerException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            customerRepository.delete(existingCustomer);
            return existingCustomer;
        } else {
            throw new CustomerException("Customer not found");
        }
    }

    @Override
    public Customer customerByName(String name) throws CustomerException {
        return customerRepository.findByUserName(name)
                .orElseThrow(() -> new ClassCastException("Customer not found"));

    }
}
