package com.company.cart.service.client;

import com.company.cart.dto.client.CartClientResponse;
import com.company.cart.dto.client.ItemClientResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface CartClientService {

    CartClientResponse getAllItemsInCart(Long cartId);

    ItemClientResponse addItemToCart(String itemUrl);

    Integer calculatePriseOfItemsInCart(Long cartId);

    void removeItemFromCart(String itemUrl, Long itemVersion);

    void clearCart(Long itemVersion);

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}
