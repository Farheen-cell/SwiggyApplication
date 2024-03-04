package com.masai.service;

import com.masai.exception.CustomerException;
import com.masai.exception.SwiggyException;
import com.masai.model.Customer;

public interface CustomerService {

    Customer addCustomer(Customer customer) throws CustomerException;
    Customer getCustomer(int id) throws CustomerException;
    Customer updateCustomerName(int customerId, String customerName) throws CustomerException;
    Customer deleteCustomer(int customerId) throws CustomerException;
    Customer customerByName(String name) throws CustomerException;
}
