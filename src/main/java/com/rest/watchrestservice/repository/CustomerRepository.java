package com.rest.watchrestservice.repository;

import com.rest.watchrestservice.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Page<Customer> findCustomersByVersion(Integer version, Pageable pageable);

    Page<Customer> findCustomersByNameLikeIgnoreCase(String name, Pageable pageable);

    Page<Customer> findCustomersByNameLikeIgnoreCaseAndVersion(String name, Integer version, Pageable pageable);
}
