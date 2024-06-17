package com.rest.watchrestservice.service;

import com.rest.watchrestservice.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer getCustomerById(String id);

    List<Customer> listCustomers();

    Customer addCustomer(Customer customer);

    Customer updateById(String id, Customer customer);

    void deleteById(String id);
}