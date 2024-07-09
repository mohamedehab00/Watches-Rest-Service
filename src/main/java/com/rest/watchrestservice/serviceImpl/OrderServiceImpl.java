package com.rest.watchrestservice.serviceImpl;

import com.rest.watchrestservice.Mapper;
import com.rest.watchrestservice.dto.WatchOrderCreationDto;
import com.rest.watchrestservice.dto.WatchOrderLineCreationDto;
import com.rest.watchrestservice.exceptions.ElementNotFoundException;
import com.rest.watchrestservice.model.Customer;
import com.rest.watchrestservice.model.Watch;
import com.rest.watchrestservice.model.WatchOrder;
import com.rest.watchrestservice.model.WatchOrderLine;
import com.rest.watchrestservice.repository.CustomerRepository;
import com.rest.watchrestservice.repository.WatchOrderRepository;
import com.rest.watchrestservice.repository.WatchRepository;
import com.rest.watchrestservice.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
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
    public WatchOrder createOrder(WatchOrderCreationDto orderCreationDto) {
        WatchOrder.WatchOrderBuilder createdOrder = WatchOrder.builder();

        Optional<Customer> currentCustomer = customerRepository.findById(orderCreationDto.getCustomerId());

        if (currentCustomer.isEmpty()){
            throw new ElementNotFoundException(STR."Customer with id: \{orderCreationDto.getCustomerId()} is not found");
        }

        createdOrder = createdOrder.customer(currentCustomer.get())
                        .version(orderCreationDto.getVersion())
                        .customer_ref(orderCreationDto.getCustomerRef());

        Set<WatchOrderLine> orderLines = new HashSet<>();

        WatchOrder readyCreatedOrder = createdOrder
                .orderLines(orderLines)
                .build();

        for (WatchOrderLineCreationDto dto : orderCreationDto.getOrderLines()){
            Optional<Watch> watch = watchRepository.findById(dto.getWatchId());

            if (watch.isEmpty()){
                throw new ElementNotFoundException(STR."Watch with id: \{dto.getWatchId()} is not found");
            }

            WatchOrderLine orderLine = WatchOrderLine.builder()
                    .watchOrder(readyCreatedOrder)
                    .watch(watch.get())
                    .order_quantity(dto.getOrder_quantity())
                    .quantity_allocated(dto.getQuantity_allocated())
                    .version(dto.getVersion())
                    .build();

            Integer watchQuantity = watch.get().getQuantityOnHand();

            if (watchQuantity < orderLine.getOrder_quantity()){
                throw new RuntimeException("Insufficient amount of watches");
            }

            watch.get().setQuantityOnHand(watchQuantity - orderLine.getOrder_quantity());

            watchRepository.save(watch.get());

            orderLines.add(orderLine);
        }

        readyCreatedOrder = watchOrderRepository.save(readyCreatedOrder);

        return readyCreatedOrder;
    }
}
