package com.bank.mscustomer.service;

import com.bank.mscustomer.controller.dto.CreateCustomerRequestDto;
import com.bank.mscustomer.controller.dto.CreateCustomerResponseDto;
import com.bank.mscustomer.controller.dto.UpdateCustomerRequestDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for customer operations
 */
public interface CustomerService {

    /**
     * Saves a new customer
     *
     * @param customerRequest the request containing customer data
     * @return a Mono of the created customer {@link CreateCustomerResponseDto}
     */
    Mono<CreateCustomerResponseDto> save(CreateCustomerRequestDto customerRequest);

    /**
     * Finds all customers
     *
     * @return a Flux of all customers {@link CreateCustomerResponseDto}
     */
    Flux<CreateCustomerResponseDto> findAll();

    /**
     * Find a customer by ID
     *
     * @param id the ID of the customer
     * @return a Mono of the customer {@link CreateCustomerResponseDto}
     */
    Mono<CreateCustomerResponseDto> findById(Long id);

    /**
     * Updates a customer by ID
     *
     * @param id              the ID of the customer
     * @param customerRequest the request containing customer data
     * @return a Mono of the updated customer {@link CreateCustomerResponseDto}
     */
    Mono<CreateCustomerResponseDto> update(Long id, UpdateCustomerRequestDto customerRequest);

    /**
     * Deletes a customer by ID
     *
     * @param id the ID of the customer
     * @return a Mono of the no content response
     */
    Mono<Void> delete(Long id);
}
