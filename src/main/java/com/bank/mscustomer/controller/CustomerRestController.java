package com.bank.mscustomer.controller;

import com.bank.mscustomer.controller.dto.CreateCustomerRequestDto;
import com.bank.mscustomer.controller.dto.CreateCustomerResponseDto;
import com.bank.mscustomer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * Rest controller for customer operations
 */
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    /**
     * Creates a new customer
     *
     * @param customerRequest the request containing customer data
     * @return a Mono of the created customer {@link CreateCustomerResponseDto}
     */
    @PostMapping
    public Mono<ResponseEntity<CreateCustomerResponseDto>> save(
            @Valid @RequestBody CreateCustomerRequestDto customerRequest) {
        return customerService.save(customerRequest)
                .map(response -> ResponseEntity
                        .created(URI.create("/api/v1/customers" + response.getId()))
                        .body(response)
                );
    }

    /**
     * Retrieves all customers
     *
     * @return a Flux of all customers {@link CreateCustomerResponseDto}
     */
    @GetMapping
    public Flux<CreateCustomerResponseDto> findAll() {
        return customerService.findAll();
    }

    /**
     * Retrieves a customer by ID
     *
     * @param id the ID of the customer
     * @return a Mono of the customer {@link CreateCustomerResponseDto}
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CreateCustomerResponseDto>> findById(@PathVariable Long id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok);
    }

    /**
     * Updates a customer by ID
     *
     * @param id              the ID of the customer
     * @param customerRequest the request containing customer data
     * @return a Mono of the updated customer {@link CreateCustomerResponseDto}
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<CreateCustomerResponseDto>> update(
            @PathVariable Long id, @RequestBody CreateCustomerRequestDto customerRequest) {
        return customerService.update(id, customerRequest)
                .map(ResponseEntity::ok);
    }

    /**
     * Deletes a customer by ID
     *
     * @param id the ID of the customer
     * @return a Mono of the no content response
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long id) {
        return customerService.delete(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
