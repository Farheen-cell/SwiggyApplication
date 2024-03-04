package com.masai.service;

import com.masai.exception.CustomerException;
import com.masai.exception.DeliveryException;
import com.masai.model.Customer;
import com.masai.model.DeliveryPartner;
import com.masai.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface DeliveryService {

    DeliveryPartner addDeliveryPartner(DeliveryPartner deliveryPartner) throws DeliveryException;
    DeliveryPartner getDeliveryPartner(int deliveryPartnerId) throws DeliveryException;
    DeliveryPartner updateDeliveryPartner(int deliveryPartnerId, DeliveryPartner deliveryPartner) throws DeliveryException;
    DeliveryPartner deleteDeliveryPartner(int deliveryPartnerId) throws DeliveryException;

    @Service
    @NoArgsConstructor
    @AllArgsConstructor
    class CustomerServiceImpl implements CustomerService {
        @Autowired
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
            return null;
        }

        @Override
        public Customer updateCustomerName(int customerId, String customerName) throws CustomerException {
            return null;
        }

        @Override
        public Customer deleteCustomer(int customerId) throws CustomerException {
            return null;
        }

        @Override
        public Customer customerByName(String name) throws CustomerException {
            return null;
        }
    }
}
