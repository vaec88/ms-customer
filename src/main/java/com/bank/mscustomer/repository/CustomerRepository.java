package com.bank.mscustomer.repository;

import com.bank.mscustomer.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for customer operations
 */
@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
}
