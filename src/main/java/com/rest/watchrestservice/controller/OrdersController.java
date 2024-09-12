package com.rest.watchrestservice.controller;

import com.rest.watchrestservice.dto.WatchOrderCreationDto;
import com.rest.watchrestservice.dto.WatchOrderDto;
import com.rest.watchrestservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrdersController {
    public static final String ORDER_PATH = "/api/v1/order";
    public static final String ORDER_PATH_ID = ORDER_PATH + "/{id}";

    private final OrderService orderService;

    @PostMapping(ORDER_PATH)
    public ResponseEntity<WatchOrderDto> createWatchOrder(@RequestBody WatchOrderCreationDto orderCreationDto){
        WatchOrderDto order = orderService.createOrder(orderCreationDto);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping(ORDER_PATH_ID)
    public ResponseEntity<WatchOrderDto> getWatchOrderById(@PathVariable UUID id){
        WatchOrderDto order = orderService.retrieveOrderById(id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(ORDER_PATH)
    public ResponseEntity<List<WatchOrderDto>> getWatchOrders(){
        List<WatchOrderDto> orders = orderService.getAllOrders();

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping(ORDER_PATH_ID)
    public ResponseEntity deleteWatchOrderById(@PathVariable UUID id){
        orderService.deleteOrderById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
