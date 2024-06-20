package com.rest.watchrestservice;

import com.rest.watchrestservice.dto.CustomerCreationDto;
import com.rest.watchrestservice.dto.CustomerDto;
import com.rest.watchrestservice.dto.WatchCreationDto;
import com.rest.watchrestservice.dto.WatchDto;
import com.rest.watchrestservice.model.Customer;
import com.rest.watchrestservice.model.Watch;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public WatchDto watchToWatchDto(Watch watch){
        return WatchDto
                .builder()
                .id(watch.getId())
                .model(watch.getModel())
                .serialNumber(watch.getSerialNumber())
                .price(watch.getPrice())
                .origin(watch.getOrigin())
                .quantityOnHand(watch.getQuantityOnHand())
                .build();
    }

    public Watch watchDtoToWatch(WatchDto watchDto){
        return Watch
                .builder()
                .id(watchDto.getId())
                .model(watchDto.getModel())
                .serialNumber(watchDto.getSerialNumber())
                .price(watchDto.getPrice())
                .origin(watchDto.getOrigin())
                .quantityOnHand(watchDto.getQuantityOnHand())
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    public Watch watchCreationDtoToWatch(WatchCreationDto watchCreationDto){
        return Watch
                .builder()
                .id(null)
                .model(watchCreationDto.getModel())
                .serialNumber(watchCreationDto.getSerialNumber())
                .price(watchCreationDto.getPrice())
                .origin(watchCreationDto.getOrigin())
                .quantityOnHand(watchCreationDto.getQuantityOnHand())
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    public CustomerDto customerToCustomerDto(Customer customer){
        return CustomerDto
                .builder()
                .id(customer.getId())
                .name(customer.getName())
                .version(customer.getVersion())
                .build();
    }

    public Customer customerDtoToCustomer(CustomerDto customerDto){
        return Customer
                .builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .version(customerDto.getVersion())
                .addedAt(null)
                .updatedAt(null)
                .build();
    }

    public Customer customerCreationDtoToCustomer(CustomerCreationDto customerCreationDto){
        return Customer
                .builder()
                .id(null)
                .name(customerCreationDto.getName())
                .version(customerCreationDto.getVersion())
                .addedAt(null)
                .updatedAt(null)
                .build();
    }
}
