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

    @NotNull(message = "User profile id field shod not contains null value!")
    private Long profileId;

    @Range(max = 10000, message ="Order code field shod have maximum of {max} characters!")
    private Integer orderCode;

    @NotNull(message = "Order version field shod not contains null value!")
    private Long version;

}