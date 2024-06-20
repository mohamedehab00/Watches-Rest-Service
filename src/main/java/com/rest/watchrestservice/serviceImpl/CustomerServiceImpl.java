package com.rest.watchrestservice.serviceImpl;

import com.rest.watchrestservice.Mapper;
import com.rest.watchrestservice.UtilClass;
import com.rest.watchrestservice.dto.CustomerCreationDto;
import com.rest.watchrestservice.dto.CustomerDto;
import com.rest.watchrestservice.exceptions.ElementNotFoundException;
import com.rest.watchrestservice.model.Customer;
import com.rest.watchrestservice.repository.CustomerRepository;
import com.rest.watchrestservice.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public CustomerDto getCustomerById(UUID id) {
        Optional<Customer> optionalCustomer = repository.findById(id);

        if (optionalCustomer.isEmpty()){
            throw new ElementNotFoundException(STR."Customer with id: \{id} is not found");
        }

        Customer customer = optionalCustomer.get();

        return mapper.customerToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> listCustomers(String name, Integer version, Integer page, Integer size) {
        PageRequest pageRequest = UtilClass.buildPageRequest(size, page);

        Page<Customer> customers;

        if (StringUtils.hasText(name) && version != null) {
            customers = repository.findCustomersByNameLikeIgnoreCaseAndVersion(STR."%\{name}%",version,pageRequest);

            return new PageImpl<>(customers.stream().map(mapper::customerToCustomerDto).toList()).getContent();
        } else if (StringUtils.hasText(name)) {
            customers = repository.findCustomersByNameLikeIgnoreCase(STR."%\{name}%",pageRequest);

            return new PageImpl<>(customers.stream().map(mapper::customerToCustomerDto).toList()).getContent();
        } else if (version != null) {
            customers = repository.findCustomersByVersion(version,pageRequest);

            return new PageImpl<>(customers.stream().map(mapper::customerToCustomerDto).toList()).getContent();
        }
        else {
            customers = repository.findAll(pageRequest);

            return new PageImpl<>(customers.stream().map(mapper::customerToCustomerDto).toList()).getContent();
        }
    }

    @Override
    @Transactional
    public CustomerDto addCustomer(CustomerCreationDto customerCreationDto) {

        Customer customer = repository.save(mapper.customerCreationDtoToCustomer(customerCreationDto));

        return mapper.customerToCustomerDto(customer);
    }

    @Override
    @Transactional
    public CustomerDto updateById(UUID id, CustomerCreationDto customerCreationDto) {
        Optional<Customer> optionalCustomer = repository.findById(id);

        if (optionalCustomer.isEmpty()){
            throw new ElementNotFoundException(STR."Customer with id: \{id} is not found");
        }

        Customer existingCustomer = optionalCustomer.get();

        if (customerCreationDto.getName() != null)
            existingCustomer.setName(customerCreationDto.getName());
        if (customerCreationDto.getVersion() != 0)
            existingCustomer.setVersion(customerCreationDto.getVersion());

        existingCustomer = repository.save(existingCustomer);

        return mapper.customerToCustomerDto(existingCustomer);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
