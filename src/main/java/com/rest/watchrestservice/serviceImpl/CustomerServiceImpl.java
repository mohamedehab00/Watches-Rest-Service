package com.rest.watchrestservice.serviceImpl;

import com.rest.watchrestservice.Mapper;
import com.rest.watchrestservice.dto.CustomerCreationDto;
import com.rest.watchrestservice.dto.CustomerDto;
import com.rest.watchrestservice.model.Customer;
import com.rest.watchrestservice.repository.CustomerRepository;
import com.rest.watchrestservice.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private Mapper mapper;
    private CustomerRepository repository;

    @Autowired
    public void setRepository(CustomerRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public CustomerDto getCustomerById(String id) {
        Customer customer = repository.findById(id).orElseThrow(RuntimeException::new);

        return mapper.customerToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> listCustomers() {
        return repository.findAll().stream().map(mapper::customerToCustomerDto).toList();
    }

    @Override
    @Transactional
    public CustomerDto addCustomer(CustomerCreationDto customerCreationDto) {

        Customer customer = repository.save(mapper.customerCreationDtoToCustomer(customerCreationDto));

        return mapper.customerToCustomerDto(customer);
    }

    @Override
    @Transactional
    public CustomerDto updateById(String id, CustomerCreationDto customerCreationDto) {
        Customer existingCustomer = repository.findById(id).orElseThrow(RuntimeException::new);

        if (customerCreationDto.getName() != null)
            existingCustomer.setName(customerCreationDto.getName());
        if (customerCreationDto.getVersion() != 0)
            existingCustomer.setVersion(customerCreationDto.getVersion());

        existingCustomer = repository.save(existingCustomer);

        return mapper.customerToCustomerDto(existingCustomer);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
