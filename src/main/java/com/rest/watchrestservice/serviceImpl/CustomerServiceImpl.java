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
    private HashMap<String, Customer> customerHashMap;

    public CustomerServiceImpl(){
        this.customerHashMap = new HashMap<>();
    }

    @Override
    public Customer getCustomerById(String id) {
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

        while (this.customerHashMap.containsKey(id.toString())){
            id = UUID.randomUUID();
        }

        Customer newCustomer = Customer.builder()
                                .id(id.toString())
                                .name(customer.getName())
                                .version(customer.getVersion())
                                .addedAt(LocalDateTime.now())
                                .build();

        this.customerHashMap.put(id.toString(),newCustomer);

        return newCustomer;
    }

    @Override
    public Customer updateById(String id, Customer customer) {
        Customer existingCustomer = this.customerHashMap.get(id);

        if (customer.getName() != null)
            existingCustomer.setName(customer.getName());
        if (customer.getVersion() != 0)
            existingCustomer.setVersion(customer.getVersion());

        this.customerHashMap.put(id,existingCustomer);

        return existingCustomer;
    }

    @Override
    public void deleteById(String id) {
        this.customerHashMap.remove(id);
    }
}
