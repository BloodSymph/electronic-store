package com.company.order.dto.client.order;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderClientResponse {

    private Long id;

    private Long profileId;

    private Integer orderCode;

}
