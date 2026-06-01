package com.bank.mscustomer.controller.dto;

import com.bank.mscustomer.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a customer creation response data transfer object
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCustomerResponseDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    private Gender gender;

    @NotBlank
    private String identification;

    private String address;

    private String phone;

    private Boolean status;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
