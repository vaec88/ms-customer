package com.bank.mscustomer.model;

import com.bank.mscustomer.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Represents a customer entity
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("customer")
public class Customer {

    @Id
    private Long id;

    @NotBlank
    private String name;

    private Gender gender;

    @NotBlank
    private String identification;

    private String address;

    private String phone;

    @NotBlank
    private String password;

    private Boolean status;

    @Column("created_at")
    @NotNull
    private LocalDateTime createdAt;

    @Column("modified_at")
    private LocalDateTime modifiedAt;
}
