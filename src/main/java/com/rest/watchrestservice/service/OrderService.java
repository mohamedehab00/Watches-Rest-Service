package com.rest.watchrestservice.service;

import com.rest.watchrestservice.dto.WatchOrderCreationDto;
import com.rest.watchrestservice.model.WatchOrder;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    WatchOrder createOrder(WatchOrderCreationDto orderCreationDto);
    WatchOrder retrieveOrderById(UUID id);
    void deleteOrderById(UUID id);

    List<WatchOrder> getAllOrders();
}
