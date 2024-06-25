package com.company.cart.mapper.feign;

import com.company.cart.dto.feign.ItemFeignClientDto;
import com.company.cart.entity.ItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemFeignClientMapper {

    public static ItemFeignClientDto mapToItemFeignClientResponse(ItemEntity item) {
        return ItemFeignClientDto.builder()
                .productId(item.getProductId())
                .price(item.getPrice())
                .build();
    }

    public static ItemEntity mapToItemEntity(ItemFeignClientDto item) {
        return ItemEntity.builder()
                .productId(item.getProductId())
                .price(item.getPrice())
                .build();
    }

}
