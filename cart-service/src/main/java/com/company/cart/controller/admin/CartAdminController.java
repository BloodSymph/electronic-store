package com.company.cart.controller.admin;

import com.company.cart.dto.admin.cart.CartAdminResponse;
import com.company.cart.dto.admin.cart.CartDetailedAdminResponse;
import com.company.cart.service.admin.CartAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart-service/admin")
public class CartAdminController {

    private final CartAdminService cartAdminService;

    @GetMapping("/carts")
    @ResponseStatus(HttpStatus.OK)
    public Page<CartAdminResponse> getAllCarts(
            @PageableDefault(
                    sort = "created",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 15) Pageable pageable) {
        return cartAdminService.getAllCarts(pageable);
    }

    @GetMapping("/carts/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<CartAdminResponse> searchCart(
            @RequestParam(
                    value = "profileId",
                    required = false,
                    defaultValue = "") Long profileId,
            @PageableDefault(
                    sort = "created",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 15) Pageable pageable) {
        return cartAdminService.searchCarts(pageable, profileId);
    }

    @GetMapping("/carts/{profileId}/items")
    @ResponseStatus(HttpStatus.OK)
    public CartDetailedAdminResponse getCartUserItems(
            @PathVariable(value = "profileId") Long profileId) {
        return cartAdminService.getCartUserItems(profileId);
    }

    @DeleteMapping("/carts/{profileId}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCart(
            @PathVariable(value = "profileId") Long profileId,
            @RequestParam(value = "cartVersion") Long cartVersion) {

        cartAdminService.deleteCart(
                profileId, cartVersion
        );
        return new ResponseEntity<>(
                "Cart successful deleted by profileId: " + profileId + " !",
                HttpStatus.OK
        );
    }

}
