package com.rest.watchrestservice.service;

import com.rest.watchrestservice.dto.WatchOrderCreationDto;
import com.rest.watchrestservice.model.WatchOrder;

public interface OrderService {
    public WatchOrder createOrder(WatchOrderCreationDto orderCreationDto);
}
