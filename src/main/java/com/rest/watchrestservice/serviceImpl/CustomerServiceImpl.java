package com.rest.watchrestservice.serviceImpl;

import com.rest.watchrestservice.model.Customer;
import com.rest.watchrestservice.repository.CustomerRepository;
import com.rest.watchrestservice.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository repository;

    @Autowired
    public void setRepository(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer getCustomerById(String id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Customer> listCustomers() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {
        customer = repository.save(customer);

        return customer;
    }

    @Override
    @Transactional
    public Customer updateById(String id, Customer customer) {
        Customer existingCustomer = repository.findById(id).orElseThrow(RuntimeException::new);

        if (customer.getName() != null)
            existingCustomer.setName(customer.getName());
        if (customer.getVersion() != 0)
            existingCustomer.setVersion(customer.getVersion());

        existingCustomer = repository.save(existingCustomer);

        return existingCustomer;
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
