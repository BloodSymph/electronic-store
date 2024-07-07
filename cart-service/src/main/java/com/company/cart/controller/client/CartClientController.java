package com.company.cart.controller.client;

import com.company.cart.dto.client.cart.CartClientRequest;
import com.company.cart.dto.client.cart.CartClientDetailedResponse;
import com.company.cart.dto.client.cart.CartClientResponse;
import com.company.cart.dto.client.item.ItemClientRequest;
import com.company.cart.dto.client.item.ItemClientResponse;
import com.company.cart.service.client.CartClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart-service/client")
public class CartClientController {

    private final CartClientService cartClientService;

    @GetMapping("/{profileId}/cart")
    @ResponseStatus(HttpStatus.OK)
    public CartClientDetailedResponse getCartWithItems(
            @PathVariable(value = "profileId") Long profileId){
        return cartClientService.getCartWithItems(profileId);
    }

    @PostMapping("/create/cart")
    @ResponseStatus(HttpStatus.CREATED)
    public CartClientResponse createCart(
            @Valid @RequestBody CartClientRequest cartClientRequest){
        return cartClientService.createCart(cartClientRequest);
    }

    @PostMapping("/{profileId}/cart/item/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemClientResponse addItemToCart(
            @Valid @RequestBody ItemClientRequest itemClientRequest,
            @PathVariable(value = "profileId") Long profileId) {
        return cartClientService.addItemToTheCart(itemClientRequest, profileId);
    }

    @GetMapping("/{profileId}/cart/calculate/price")
    @ResponseStatus(HttpStatus.OK)
    public Double calculatePricesOfItemsInCart(
            @PathVariable(value = "profileId") Long profileId) {
        return cartClientService.calculateItemsPriseInCart(profileId);
    }

    @GetMapping("/{profileId}/cart/get/id")
    @ResponseStatus(HttpStatus.OK)
    public Long getCartIdForOrder(
            @PathVariable(value = "profileId") Long profileId) {
        return cartClientService.getCartIdForOrder(profileId);
    }

    @DeleteMapping("/{profileId}/cart/{itemId}/remove")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeItemFromCart(
            @PathVariable(value = "profileId") Long profileId,
            @PathVariable(value = "itemId") Long itemId,
            @RequestParam(value = "itemVersion") Long itemVersion) {

        cartClientService.removeItemFromCart(profileId, itemId, itemVersion);

        return new ResponseEntity<>(
                "Item successful removed by profileId and itemId: "
                        + profileId + " " + itemId + " !",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{profileId}/cart/clear")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> clearCart(
            @PathVariable(value = "profileId") Long profileId,
            @RequestParam(value = "cartVersion") Long cartVersion) {

        cartClientService.clearCart(profileId, cartVersion);

        return new ResponseEntity<>(
                "Cart successful cleared by profileId: " + profileId + " !",
                HttpStatus.OK
        );
    }

}
