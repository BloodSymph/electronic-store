package com.company.order.dto.client;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderClientResponse {

    private Long id;

    private Integer orderCode;

    private String firstName;

    private String lastName;

    private String email;

}
