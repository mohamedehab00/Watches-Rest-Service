package com.rest.watchrestservice.serviceImpl;

import com.rest.watchrestservice.model.Customer;
import com.rest.watchrestservice.model.Watch;
import com.rest.watchrestservice.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    private HashMap<UUID, Customer> customerHashMap;

    public CustomerServiceImpl(){
        this.customerHashMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .name("Mohamed Ehab")
                .version(1)
                .addedAt(LocalDateTime.now())
                .build();
        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .name("Omar Hisham")
                .version(2)
                .addedAt(LocalDateTime.now())
                .build();
        Customer customer3 = Customer.builder()
                .id(UUID.randomUUID())
                .name("Karim El Derestawy")
                .version(3)
                .addedAt(LocalDateTime.now())
                .build();

        this.customerHashMap.put(customer1.getId(),customer1);
        this.customerHashMap.put(customer2.getId(),customer2);
        this.customerHashMap.put(customer3.getId(),customer3);
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return this.customerHashMap.get(id);
    }

    @Override
    public List<Customer> listCustomers() {
        return this.customerHashMap.values().stream().toList();
    }

    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {
        UUID id = UUID.randomUUID();

        while (this.customerHashMap.containsKey(id)){
            id = UUID.randomUUID();
        }

        Customer newCustomer = Customer.builder()
                                .id(id)
                                .name(customer.getName())
                                .version(customer.getVersion())
                                .addedAt(LocalDateTime.now())
                                .build();

        this.customerHashMap.put(id,newCustomer);

        return newCustomer;
    }

    @Override
    public Customer updateById(UUID id, Customer customer) {
        Customer existingCustomer = this.customerHashMap.get(id);

        if (customer.getName() != null)
            existingCustomer.setName(customer.getName());
        if (customer.getVersion() != 0)
            existingCustomer.setVersion(customer.getVersion());

        this.customerHashMap.put(id,existingCustomer);

        return existingCustomer;
    }

    @Override
    public void deleteById(UUID id) {
        this.customerHashMap.remove(id);
    }
}
