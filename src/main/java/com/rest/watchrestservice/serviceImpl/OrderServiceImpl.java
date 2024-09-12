package com.rest.watchrestservice.serviceImpl;

import com.rest.watchrestservice.Mapper;
import com.rest.watchrestservice.dto.WatchOrderCreationDto;
import com.rest.watchrestservice.dto.WatchOrderDto;
import com.rest.watchrestservice.dto.WatchOrderLineCreationDto;
import com.rest.watchrestservice.exceptions.ElementNotFoundException;
import com.rest.watchrestservice.model.*;
import com.rest.watchrestservice.repository.CustomerRepository;
import com.rest.watchrestservice.repository.WatchOrderRepository;
import com.rest.watchrestservice.repository.WatchRepository;
import com.rest.watchrestservice.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Getter
public class OrderServiceImpl implements OrderService {
    private WatchOrderRepository watchOrderRepository;
    private CustomerRepository customerRepository;
    private WatchRepository watchRepository;
    Mapper mapper;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setWatchRepository(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    @Autowired
    public void setWatchOrderRepository(WatchOrderRepository watchOrderRepository) {
        this.watchOrderRepository = watchOrderRepository;
    }

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public WatchOrderDto createOrder(WatchOrderCreationDto orderCreationDto) {
        WatchOrder.WatchOrderBuilder createdOrder = WatchOrder.builder();

        Optional<Customer> currentCustomer = getCustomerRepository().findById(orderCreationDto.getCustomerId());

        if (currentCustomer.isEmpty()){
            throw new ElementNotFoundException(STR."Customer with id: \{orderCreationDto.getCustomerId()} is not found");
        }

        UUID createdOrderId = getWatchOrderRepository().getNextIdValue();

        WatchOrderShipment shipment = getMapper().watchOrderShipmentCreationDtoToWatchOrderShipment(orderCreationDto.getShipment());

        WatchOrder readyCreatedOrder = createdOrder
                .id(createdOrderId)
                .customer_id(currentCustomer.get().getId())
                .version(orderCreationDto.getVersion())
                .customer_ref(orderCreationDto.getCustomerRef())
                .orderShipment(shipment)
                .build();

        Set<WatchOrderLine> orderLines = new HashSet<>();

        for (WatchOrderLineCreationDto dto : orderCreationDto.getOrderLines()){
            Optional<Watch> watch = getWatchRepository().findById(dto.getWatchId());

            if (watch.isEmpty()){
                throw new ElementNotFoundException(STR."Watch with id: \{dto.getWatchId()} is not found");
            }

            WatchOrderLine orderLine = WatchOrderLine.builder()
                    .watch_order_id(createdOrderId)
                    .watch_id(watch.get().getId())
                    .order_quantity(dto.getOrder_quantity())
                    .quantity_allocated(dto.getQuantity_allocated())
                    .version(dto.getVersion())
                    .build();

            Integer watchQuantity = watch.get().getQuantityOnHand();

            if (watchQuantity < orderLine.getOrder_quantity()){
                throw new RuntimeException("Insufficient amount of watches");
            }

            watch.get().setQuantityOnHand(watchQuantity - orderLine.getOrder_quantity());

            getWatchRepository().save(watch.get());

            orderLines.add(orderLine);
        }

        readyCreatedOrder.setOrderLines(orderLines);
        readyCreatedOrder.setCreatedAt(LocalDateTime.now());

        readyCreatedOrder = getWatchOrderRepository().save(readyCreatedOrder);

        return getMapper().watchOrderToWatchOrderDto(readyCreatedOrder);
    }

    @Override
    public WatchOrderDto retrieveOrderById(UUID id) {
        Optional<WatchOrder> order = getWatchOrderRepository().findById(id);

        if (order.isEmpty()){
            throw new ElementNotFoundException(STR."Order with id: \{id} is not found");
        }

        return mapper.watchOrderToWatchOrderDto(order.get());
    }

    @Override
    public void deleteOrderById(UUID id) {
        getWatchOrderRepository().deleteById(id);
    }

    @Override
    public List<WatchOrderDto> getAllOrders() {
        return getWatchOrderRepository().findAll().stream().map(getMapper()::watchOrderToWatchOrderDto).toList();
    }
}
