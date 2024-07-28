package com.rest.watchrestservice;

import com.rest.watchrestservice.dto.*;
import com.rest.watchrestservice.model.Category;
import com.rest.watchrestservice.model.Customer;
import com.rest.watchrestservice.model.Watch;
import com.rest.watchrestservice.model.WatchOrderShipment;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

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
                .orderLines((watch.getOrderLines() != null) ? watch.getOrderLines() : new HashSet<>())
                .categories((watch.getCategories() != null) ? watch.getCategories() : new HashSet<>())
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
                .Categories(watchCreationDto.getCategories().stream().map(uuid -> Category.builder().id(uuid).build()).collect(Collectors.toSet()))
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
                .orders(customer.getOrders())
                .orders(customer.getOrders())
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

    public Category categoryCreationDtoToCategory(CategoryCreationDto dto){
        return Category
                .builder()
                .id(null)
                .description(dto.getDescription())
                .version(dto.getVersion())
                .created_at(null)
                .updated_at(null)
                .build();
    }

    public CategoryDto categoryToCategoryDto(Category category){
        return CategoryDto
                .builder()
                .id(category.getId())
                .description(category.getDescription())
                .version(category.getVersion())
                .build();
    }

    public WatchOrderShipment watchOrderShipmentCreationDtoToWatchOrderShipment(WatchOrderShipmentCreationDto dto){
        return WatchOrderShipment.builder()
                .id(null)
                .tracking_number(dto.getTracking_number())
                .version(dto.getVersion())
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    public WatchOrderShipmentDto watchOrderShipmentToWatchOrderShipmentDto(WatchOrderShipment dto){
        return WatchOrderShipmentDto.builder()
                .id(dto.getId())
                .tracking_number(dto.getTracking_number())
                .version(dto.getVersion())
                .build();
    }
}
