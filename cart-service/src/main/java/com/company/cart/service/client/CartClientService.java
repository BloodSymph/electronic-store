package com.company.cart.service.client;

import com.company.cart.dto.client.cart.CartClientRequest;
import com.company.cart.dto.client.cart.CartClientDetailedResponse;
import com.company.cart.dto.client.cart.CartClientResponse;
import com.company.cart.dto.client.item.ItemClientResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;



@Service
public interface CartClientService {

    CartClientDetailedResponse getCartWithItems(Long profileId);

    CartClientResponse createCart(CartClientRequest cartClientRequest);

    ItemClientResponse addItemToTheCart(
            Long profileId, Long itemId, Long itemVersion
    );

    Double calculateItemsPriseInCart(Long profileId);

    void removeItemFromCart(
            Long profileId, Long itemId, Long itemVersion
    );

    void clearCart(
            Long profileId, Long cartVersion
    );

}
