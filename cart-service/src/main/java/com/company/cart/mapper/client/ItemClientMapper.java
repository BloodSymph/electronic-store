package com.company.cart.mapper.client;

import com.company.cart.dto.client.ItemClientResponse;
import com.company.cart.entity.ItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemClientMapper {

    public static ItemClientResponse mapToItemClientResponse(ItemEntity item) {
        return ItemClientResponse.builder()
                .id(item.getId())
                .title(item.getTitle())
                .url(item.getUrl())
                .price(item.getPrice())
                .photo(item.getPhoto())
                .build();
    }

}
