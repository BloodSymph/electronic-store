package com.company.order.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderClientRequest {

    @NotNull(message = "Order code field shod not contains null value!")
    @Range(max = 1000, message ="Order code field shod have maximum of {max} characters!")
    private Integer orderCode;

    @NotNull(message = "First name field shod not contains null value!")
    @NotBlank(message = "First name field shod not be empty!")
    @Length(max = 120, message = "First name field shod have maximum of {max} characters!")
    private String firstName;

    @NotNull(message = "Last name field shod not contains null value!")
    @NotBlank(message = "Last name field shod not be empty!")
    @Length(max = 120, message = "Last name field shod have maximum of {max} characters!")
    private String lastName;

    @Email(message = "That field shod be email address!")
    @NotNull(message = "Email field shod not contains null value!")
    @NotBlank(message = "Email field shod not be empty!")
    @Length(max = 120, message = "Email field shod have maximum of {max} characters!")
    private String email;

    @NotNull(message = "Order version field shod not contains null value!")
    private Long version;

}
