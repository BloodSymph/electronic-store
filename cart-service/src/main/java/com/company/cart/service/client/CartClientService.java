package com.company.cart.service.client;

import com.company.cart.dto.client.CartClientResponse;
import com.company.cart.dto.client.ItemClientResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface CartClientService {

    CartClientResponse getAllItemsInCart();

    ItemClientResponse addItemToCart(String itemUrl);

    Integer calculatePriseOfItemsInCart();

    void removeItemFromCart(String itemUrl, Long itemVersion);

    void clearCart(Long cartVersion);

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}
