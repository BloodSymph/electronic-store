package com.company.cart.mapper.feign;

import com.company.cart.dto.feign.ItemFeignClientDto;
import com.company.cart.entity.ItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemFeignClientMapper {

    public static ItemFeignClientDto mapToItemFeignClientResponse(ItemEntity item) {
        return ItemFeignClientDto.builder()
                .id(item.getId())
                .title(item.getTitle())
                .url(item.getUrl())
                .price(item.getPrice())
                .photo(item.getPhoto())
                .build();
    }

    public static ItemEntity mapToItemEntity(ItemFeignClientDto item) {
        return ItemEntity.builder()
                .id(item.getId())
                .title(item.getTitle())
                .url(item.getUrl())
                .price(item.getPrice())
                .photo(item.getPhoto())
                .version(item.getVersion())
                .build();
    }

}
