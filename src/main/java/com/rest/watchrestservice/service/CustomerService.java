package com.rest.watchrestservice.service;

import com.rest.watchrestservice.dto.CustomerCreationDto;
import com.rest.watchrestservice.dto.CustomerDto;
import com.rest.watchrestservice.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(String id);

    List<CustomerDto> listCustomers();

    CustomerDto addCustomer(CustomerCreationDto customer);

    CustomerDto updateById(String id, CustomerCreationDto customer);

    void deleteById(String id);
}