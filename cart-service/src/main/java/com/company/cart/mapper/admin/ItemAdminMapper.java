package com.company.cart.mapper.admin;

import com.company.cart.dto.admin.item.ItemAdminResponse;
import com.company.cart.entity.ItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemAdminMapper {

    public static ItemAdminResponse mapToItemAdminResponse(ItemEntity item) {
        return ItemAdminResponse.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .price(item.getPrice())
                .itemsCount(item.getItemsCount())
                .created(item.getCreated())
                .updated(item.getUpdated())
                .version(item.getVersion())
                .build();
    }

}
