package com.rest.watchrestservice.controller;

import com.rest.watchrestservice.dto.CustomerCreationDto;
import com.rest.watchrestservice.dto.CustomerDto;
import com.rest.watchrestservice.dto.WatchCreationDto;
import com.rest.watchrestservice.dto.WatchDto;
import com.rest.watchrestservice.service.CustomerService;
import com.rest.watchrestservice.service.WatchService;
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
public class HomeController {
    private final WatchService watchService;
    private final CustomerService customerService;

    public static final String WATCH_PATH = "/api/v1/watch";
    public static final String WATCH_PATH_ID = STR."\{WATCH_PATH}/{id}";
    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = STR."\{CUSTOMER_PATH}/{id}";

    @GetMapping(WATCH_PATH)
    List<WatchDto> getAllWatches(){
        log.debug("Retrieve All Available Watches");
        return this.watchService.listWatches();
    }

    @GetMapping(WATCH_PATH_ID)
    WatchDto getWatchById(@PathVariable String id){
        log.debug(STR."Retrieve Watch With Id: \{id}");
        return this.watchService.getWatchById(id);
    }

    @PostMapping(WATCH_PATH)
    ResponseEntity<WatchDto> addWatch(@RequestBody WatchCreationDto watchCreationDto){
        log.debug(STR."Adding New Watch : \{watchCreationDto}");

        WatchDto createdWatch = this.watchService.addWatch(watchCreationDto);

        log.debug(STR."Watch Created with Id: \{createdWatch.getId()}");

        HttpHeaders headers = new HttpHeaders();

        headers.add("Location",STR."/api/v1/watch/\{createdWatch.getId()}");

        return new ResponseEntity<>(createdWatch, headers, HttpStatus.CREATED);
    }

    @PutMapping(WATCH_PATH_ID)
    ResponseEntity<WatchDto> updateWatchById(@PathVariable String id, @RequestBody WatchCreationDto watchCreationDto){
        log.debug(STR."Update Watch with Id: \{id}");

        WatchDto updatedWatch = this.watchService.updateById(id,watchCreationDto);

        log.debug(STR."Watch Updated : \{updatedWatch}");

        HttpHeaders headers = new HttpHeaders();

        headers.add("Location",STR."/api/v1/watch/\{updatedWatch.getId()}");

        return new ResponseEntity<>(updatedWatch, headers, HttpStatus.OK);
    }

    @DeleteMapping(WATCH_PATH_ID)
    ResponseEntity<WatchDto> deleteWatchById(@PathVariable UUID id){
        log.debug(STR."Delete Watch with Id: \{id}");

        this.watchService.deleteById(id.toString());

        log.debug(STR."Watch Deleted with Id: \{id}");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(CUSTOMER_PATH)
    List<CustomerDto> getAllCustomers(){
        log.debug("Retrieve All Available Customers");
        return this.customerService.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    CustomerDto getCustomerById(@PathVariable String id){
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
    ResponseEntity<CustomerDto> updateCustomerById(@PathVariable String id, @RequestBody CustomerCreationDto customerCreationDto){
        log.debug(STR."Update Customer with Id: \{id}");

        CustomerDto updatedCustomer = this.customerService.updateById(id,customerCreationDto);

        log.debug(STR."Customer Updated : \{updatedCustomer}");

        HttpHeaders headers = new HttpHeaders();

        headers.add("Location",STR."/api/v1/customer/\{updatedCustomer.getId()}");

        return new ResponseEntity<>(updatedCustomer, headers, HttpStatus.OK);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    ResponseEntity<CustomerDto> deleteCustomerById(@PathVariable String id){
        log.debug(STR."Delete Customer with Id: \{id}");

        this.customerService.deleteById(id);

        log.debug(STR."Customer Deleted with Id: \{id}");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}