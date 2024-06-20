package com.rest.watchrestservice.service;

import com.rest.watchrestservice.dto.CustomerCreationDto;
import com.rest.watchrestservice.dto.CustomerDto;
import com.rest.watchrestservice.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID id);

    List<CustomerDto> listCustomers(String name, Integer version, Integer page, Integer size);

    CustomerDto addCustomer(CustomerCreationDto customer);

    CustomerDto updateById(UUID id, CustomerCreationDto customer);

    void deleteById(UUID id);
}