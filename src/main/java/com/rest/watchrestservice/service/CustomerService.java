package com.rest.watchrestservice.service;

import com.rest.watchrestservice.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer getCustomerById(UUID id);

    List<Customer> listCustomers();

    Customer addCustomer(Customer customer);

    Customer updateById(UUID id, Customer customer);

    void deleteById(UUID id);
}