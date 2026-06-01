package com.bank.mscustomer.controller.dto;

import com.bank.mscustomer.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a customer creation request data transfer object
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequestDto {

    @NotBlank
    private String name;

    private Gender gender;

    @NotBlank
    private String identification;

    private String address;

    private String phone;

    @NotBlank
    private String password;

}
