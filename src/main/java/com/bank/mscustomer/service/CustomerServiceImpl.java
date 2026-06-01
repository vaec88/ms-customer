package com.bank.mscustomer.service;

import com.bank.mscustomer.controller.dto.CreateCustomerRequestDto;
import com.bank.mscustomer.controller.dto.CreateCustomerResponseDto;
import com.bank.mscustomer.controller.dto.UpdateCustomerRequestDto;
import com.bank.mscustomer.exception.CustomerNotFoundException;
import com.bank.mscustomer.model.Customer;
import com.bank.mscustomer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Implementation of the {@link CustomerService} interface
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Mono<CreateCustomerResponseDto> save(CreateCustomerRequestDto customerRequest) {
        log.info("Start save customer");
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .gender(customerRequest.getGender())
                .identification(customerRequest.getIdentification())
                .address(customerRequest.getAddress())
                .phone(customerRequest.getPhone())
                .password(passwordEncoder.encode(customerRequest.getPassword()))
                .status(true)
                .createdAt(LocalDateTime.now())
                .build();
        return customerRepository.save(customer)
                .map(customerSaved -> CreateCustomerResponseDto.builder()
                        .id(customerSaved.getId())
                        .name(customerSaved.getName())
                        .gender(customerSaved.getGender())
                        .identification(customerSaved.getIdentification())
                        .address(customerSaved.getAddress())
                        .phone(customerSaved.getPhone())
                        .status(customerSaved.getStatus())
                        .createdAt(customerSaved.getCreatedAt())
                        .build())
                .doOnSuccess(response ->
                        log.info("End save customer. id = {}, name = {}, address = {}, phone = {}, status = {}",
                                Objects.requireNonNull(response).getId(),
                                response.getName(),
                                response.getAddress(),
                                response.getPhone(),
                                response.getStatus()));
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<CreateCustomerResponseDto> findAll() {
        return customerRepository.findAll()
                .map(customer -> CreateCustomerResponseDto.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .gender(customer.getGender())
                        .identification(customer.getIdentification())
                        .address(customer.getAddress())
                        .phone(customer.getPhone())
                        .status(customer.getStatus())
                        .build());
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<CreateCustomerResponseDto> findById(Long id) {
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomerNotFoundException(id)))
                .map(customer -> CreateCustomerResponseDto.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .gender(customer.getGender())
                        .identification(customer.getIdentification())
                        .address(customer.getAddress())
                        .phone(customer.getPhone())
                        .status(customer.getStatus())
                        .build());
    }

    @Override
    public Mono<CreateCustomerResponseDto> update(Long id, UpdateCustomerRequestDto customerRequest) {
        log.info("Start update customer");
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomerNotFoundException(id)))
                .flatMap(customer -> {
                    validateEditFields(customer, customerRequest);
                    customer.setModifiedAt(LocalDateTime.now());
                    return customerRepository.save(customer)
                            .map(customerUpdated -> CreateCustomerResponseDto.builder()
                                    .id(customerUpdated.getId())
                                    .name(customerUpdated.getName())
                                    .address(customerUpdated.getAddress())
                                    .phone(customerUpdated.getPhone())
                                    .status(customerUpdated.getStatus())
                                    .modifiedAt(customerUpdated.getModifiedAt())
                                    .build())
                            .doOnSuccess(response ->
                                    log.info("End update customer. id = {}, name = {}, address = {}, phone = {}, status = {}",
                                            Objects.requireNonNull(response).getId(),
                                            response.getName(),
                                            response.getAddress(),
                                            response.getPhone(),
                                            response.getStatus()));
                });
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.info("Start delete customer. id = {}", id);
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomerNotFoundException(id)))
                .flatMap(customerRepository::delete)
                .doOnSuccess(response ->
                        log.info("End delete customer. id = {}", id));
    }

    private void validateEditFields(Customer customer, UpdateCustomerRequestDto customerRequest) {
        if (null != customerRequest.getName()) {
            customer.setName(customerRequest.getName());
        }
        if (null != customerRequest.getAddress()) {
            customer.setAddress(customerRequest.getAddress());
        }
        if (null != customerRequest.getPhone()) {
            customer.setPhone(customerRequest.getPhone());
        }
        if (null != customerRequest.getPassword()) {
            customer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
        }
    }
}
