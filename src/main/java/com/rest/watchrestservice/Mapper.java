package com.rest.watchrestservice;

import com.rest.watchrestservice.dto.*;
import com.rest.watchrestservice.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class Mapper {
    public WatchDto watchToWatchDto(Watch watch){
        Set<WatchOrderLineDto> orderLines = watch.getOrderLines().stream().map(this::watchOrderLineToWatchOrderLineDto).collect(Collectors.toSet());
        Set<CategoryInfoDto> categories = watch.getCategories().stream().map(this::categoryToCategoryInfoDto).collect(Collectors.toSet());

        return WatchDto
                .builder()
                .id(watch.getId())
                .model(watch.getModel())
                .serialNumber(watch.getSerialNumber())
                .price(watch.getPrice())
                .origin(watch.getOrigin())
                .quantityOnHand(watch.getQuantityOnHand())
                .orderLines(orderLines)
                .categories(categories)
                .build();
    }

    public WatchInfoDto watchToWatchInfoDto(Watch watch){
        return WatchInfoDto.builder()
                .id(watch.getId())
                .model(watch.getModel())
                .origin(watch.getOrigin())
                .price(watch.getPrice())
                .quantityOnHand(watch.getQuantityOnHand())
                .serialNumber(watch.getSerialNumber())
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
        List<UUID> uuids = watchCreationDto.getCategories();

        return Watch
                .builder()
                .id(null)
                .model(watchCreationDto.getModel())
                .serialNumber(watchCreationDto.getSerialNumber())
                .price(watchCreationDto.getPrice())
                .origin(watchCreationDto.getOrigin())
                .quantityOnHand(watchCreationDto.getQuantityOnHand())
                .categories(uuids.stream().map(uuid -> Category.builder().id(uuid).build()).collect(Collectors.toSet()))
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    public CustomerDto customerToCustomerDto(Customer customer){
        List<WatchOrderDto> orders = customer.getOrders().stream().map(this::watchOrderToWatchOrderDto).toList();

        return CustomerDto
                .builder()
                .id(customer.getId())
                .name(customer.getName())
                .orders(orders)
                .build();
    }

    public Customer customerDtoToCustomer(CustomerDto customerDto){
        return Customer
                .builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .version(null)
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
                .build();
    }

    CategoryInfoDto categoryToCategoryInfoDto(Category category){
        return CategoryInfoDto.builder()
                .id(category.getId())
                .description(category.getDescription())
                .build();
    }

    public WatchOrderDto watchOrderToWatchOrderDto(WatchOrder order){
        WatchOrderShipmentInfoDto orderShipmentDto = watchOrderShipmentToWatchOrderShipmentInfoDto(order.getOrderShipment());

        Set<WatchOrderLineDto> watchOrderLineDtos = order.getOrderLines().stream().map(this::watchOrderLineToWatchOrderLineDto).collect(Collectors.toSet());

        return WatchOrderDto.builder()
                .id(order.getId())
                .customer_id(order.getCustomer_id())
                .orderShipment(orderShipmentDto)
                .orderLines(watchOrderLineDtos)
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public WatchOrderLineDto watchOrderLineToWatchOrderLineDto(WatchOrderLine watchOrderLine){
        return WatchOrderLineDto.builder()
                .id(watchOrderLine.getId())
                .watch_id(watchOrderLine.getWatch_id())
                .watch_order_id(watchOrderLine.getWatch_order_id())
                .updatedAt(watchOrderLine.getUpdatedAt())
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
        WatchOrderInfoDto orderDto = watchOrderToWatchOrderInfoDto(dto.getOrder());

        return WatchOrderShipmentDto.builder()
                .id(dto.getId())
                .tracking_number(dto.getTracking_number())
                .order(orderDto)
                .build();
    }

    public WatchOrderInfoDto watchOrderToWatchOrderInfoDto(WatchOrder order){
        return WatchOrderInfoDto.builder()
                .id(order.getId())
                .customer_id(order.getCustomer_id())
                .orderLines(order.getOrderLines().stream().map(this::watchOrderLineToWatchOrderLineDto).collect(Collectors.toSet()))
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public WatchOrderShipmentInfoDto watchOrderShipmentToWatchOrderShipmentInfoDto(WatchOrderShipment shipment){
        return WatchOrderShipmentInfoDto.builder()
                .id(shipment.getId())
                .tracking_number(shipment.getTracking_number())
                .build();
    }
}
