package com.company.cart.service.client;

import com.company.cart.dto.client.cart.CartClientRequest;
import com.company.cart.dto.client.cart.CartClientDetailedResponse;
import com.company.cart.dto.client.cart.CartClientResponse;
import com.company.cart.dto.client.item.ItemClientRequest;
import com.company.cart.dto.client.item.ItemClientResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;



@Service
public interface CartClientService {

    CartClientDetailedResponse getCartWithItems(Long profileId);

    CartClientResponse createCart(CartClientRequest cartClientRequest);

    ItemClientResponse addItemToTheCart(
            ItemClientRequest itemClientRequest, Long profileId
    );

    Double calculateItemsPriseInCart(Long profileId, Long productId);

    Long getCartIdForOrder(Long profileId);

    void removeItemFromCart(
            Long profileId, Long itemId, Long itemVersion
    );

    void clearCart(
            Long profileId, Long cartVersion
    );

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}
