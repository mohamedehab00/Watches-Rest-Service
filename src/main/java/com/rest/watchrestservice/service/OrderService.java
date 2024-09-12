package com.rest.watchrestservice.service;

import com.rest.watchrestservice.dto.WatchOrderCreationDto;
import com.rest.watchrestservice.dto.WatchOrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    WatchOrderDto createOrder(WatchOrderCreationDto orderCreationDto);
    WatchOrderDto retrieveOrderById(UUID id);
    void deleteOrderById(UUID id);

    List<WatchOrderDto> getAllOrders();
}
