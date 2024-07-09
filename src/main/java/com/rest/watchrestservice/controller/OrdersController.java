package com.rest.watchrestservice.controller;

import com.rest.watchrestservice.dto.WatchOrderCreationDto;
import com.rest.watchrestservice.model.WatchOrder;
import com.rest.watchrestservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrdersController {
    public static final String ORDER_PATH = "/api/v1/order";
    public static final String ORDER_PATH_ID = ORDER_PATH + "/{id}";

    private final OrderService orderService;

    @PostMapping(ORDER_PATH)
    public ResponseEntity<WatchOrder> createWatchOrder(@RequestBody WatchOrderCreationDto orderCreationDto){
        WatchOrder order = orderService.createOrder(orderCreationDto);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
