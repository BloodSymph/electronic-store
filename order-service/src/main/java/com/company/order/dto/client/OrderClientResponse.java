package com.company.order.dto.client;


import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderClientResponse {

    private Long id;

    private Integer orderCode;

    private Long cartId;

    private Double orderPrice;

}
