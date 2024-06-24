package com.company.cart.service.client;

import com.company.cart.dto.client.cart.CartClientRequest;
import com.company.cart.dto.client.cart.CartClientResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public interface CartClientService {

    CartClientResponse getCartWithItems(Long profileId);

    CartClientResponse addItemToTheCart(
            CartClientRequest cartClientRequest, String itemUrl
    );

    Double calculateItemsPriseInCart(Long profileId);

    void removeItemFromCart(
            String itemUrl, Long itemVersion, Long profileId
    );

    void clearCart(Long cartVersion, Long profileId);

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}
