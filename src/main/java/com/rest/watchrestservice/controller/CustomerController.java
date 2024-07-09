package com.rest.watchrestservice.controller;

import com.rest.watchrestservice.dto.CustomerCreationDto;
import com.rest.watchrestservice.dto.CustomerDto;
import com.rest.watchrestservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {
    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{id}";

    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH)
    List<CustomerDto> getAllCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer version,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ){
        log.debug("Retrieve All Available Customers");
        return this.customerService.listCustomers(name, version, page, size);
    }

    @GetMapping(CUSTOMER_PATH_ID)
    CustomerDto getCustomerById(@PathVariable UUID id){
        log.debug(STR."Retrieve Customer With Id: \{id}");
        return this.customerService.getCustomerById(id);
    }

    @PostMapping(CUSTOMER_PATH)
    ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerCreationDto customerCreationDto){
        log.debug(STR."Adding New Customer : \{customerCreationDto}");

        CustomerDto createdCustomer = this.customerService.addCustomer(customerCreationDto);

        log.debug(STR."Customer Created with Id: \{createdCustomer.getId()}");

        HttpHeaders headers = new HttpHeaders();

        headers.add("Location",STR."/api/v1/customer/\{createdCustomer.getId()}");

        return new ResponseEntity<>(createdCustomer, headers, HttpStatus.CREATED);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    ResponseEntity<CustomerDto> updateCustomerById(@PathVariable UUID id, @RequestBody CustomerCreationDto customerCreationDto){
        log.debug(STR."Update Customer with Id: \{id}");

        CustomerDto updatedCustomer = this.customerService.updateById(id,customerCreationDto);

        log.debug(STR."Customer Updated : \{updatedCustomer}");

        HttpHeaders headers = new HttpHeaders();

        headers.add("Location",STR."/api/v1/customer/\{updatedCustomer.getId()}");

        return new ResponseEntity<>(updatedCustomer, headers, HttpStatus.OK);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    ResponseEntity<CustomerDto> deleteCustomerById(@PathVariable UUID id){
        log.debug(STR."Delete Customer with Id: \{id}");

        this.customerService.deleteById(id);

        log.debug(STR."Customer Deleted with Id: \{id}");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
