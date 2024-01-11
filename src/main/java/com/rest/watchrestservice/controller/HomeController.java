package com.rest.watchrestservice.controller;

import com.rest.watchrestservice.model.Customer;
import com.rest.watchrestservice.model.Watch;
import com.rest.watchrestservice.service.CustomerService;
import com.rest.watchrestservice.service.WatchService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HomeController {
    private final WatchService watchService;
    private final CustomerService customerService;

    @GetMapping("/watch")
    List<Watch> getAllWatches(){
        getLog().debug("Retrieve All Available Watches");
        return this.watchService.listWatches() ;
    }

    @GetMapping("/watch/{id}")
    Watch getWatchById(@PathVariable UUID id){
        getLog().debug("Retrieve Watch With Id: "+id);
        return this.watchService.getWatchById(id);
    }

    @PostMapping("/watch")
    ResponseEntity<Watch> addWatch(@RequestBody Watch watch){
        getLog().debug("Adding New Watch : "+watch);

        Watch createdWatch = this.watchService.addWatch(watch);

        log.debug("Watch Created with Id: "+createdWatch.getId());

        HttpHeaders headers = new HttpHeaders();

        headers.add("Location","/api/v1/watch/"+createdWatch.getId());

        return new ResponseEntity<>(createdWatch, headers, HttpStatus.CREATED);
    }

    @PutMapping("/watch/{id}")
    ResponseEntity<Watch> updateWatchById(@PathVariable UUID id, @RequestBody Watch watch){
        getLog().debug("Update Watch with Id: " + id);

        Watch updatedWatch = this.watchService.updateById(id,watch);

        log.debug("Watch Updated : "+updatedWatch);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Location","/api/v1/watch/"+updatedWatch.getId());

        return new ResponseEntity<>(updatedWatch, headers, HttpStatus.OK);
    }

    @DeleteMapping("/watch/{id}")
    ResponseEntity<Watch> deleteWatchById(@PathVariable UUID id){
        getLog().debug("Delete Watch with Id: " + id);

        this.watchService.deleteById(id);

        log.debug("Watch Deleted with Id: "+id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/customer")
    List<Customer> getAllCustomers(){
        getLog().debug("Retrieve All Available Customers");
        return this.customerService.listCustomers();
    }

    @GetMapping("/customer/{id}")
    Customer getCustomerById(@PathVariable UUID id){
        getLog().debug("Retrieve Customer With Id: "+id);
        return this.customerService.getCustomerById(id);
    }

    @PostMapping("/customer")
    ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        getLog().debug("Adding New Customer : "+customer);

        Customer createdCustomer = this.customerService.addCustomer(customer);

        log.debug("Customer Created with Id: "+createdCustomer.getId());

        HttpHeaders headers = new HttpHeaders();

        headers.add("Location","/api/v1/customer/"+createdCustomer.getId());

        return new ResponseEntity<>(createdCustomer, headers, HttpStatus.CREATED);
    }

    @PutMapping("/customer/{id}")
    ResponseEntity<Customer> updateCustomerById(@PathVariable UUID id, @RequestBody Customer customer){
        getLog().debug("Update Customer with Id: " + id);

        Customer updatedCustomer = this.customerService.updateById(id,customer);

        log.debug("Customer Updated : "+updatedCustomer);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Location","/api/v1/customer/"+updatedCustomer.getId());

        return new ResponseEntity<>(updatedCustomer, headers, HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    ResponseEntity<Customer> deleteCustomerById(@PathVariable UUID id){
        getLog().debug("Delete Customer with Id: " + id);

        this.customerService.deleteById(id);

        log.debug("Customer Deleted with Id: "+id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public static Logger getLog() {
        return log;
    }
}
