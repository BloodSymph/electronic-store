package com.company.cart.dto.client.cart;

import com.company.cart.dto.feign.ItemFeignClientDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartClientResponse {

    private Long id;

    private Long profileId;

    private Set<ItemFeignClientDto> items;

}
