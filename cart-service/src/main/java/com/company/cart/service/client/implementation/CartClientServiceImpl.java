package com.company.cart.service.client.implementation;

import com.company.cart.dto.client.cart.CartClientRequest;
import com.company.cart.dto.client.cart.CartClientDetailedResponse;
import com.company.cart.dto.client.cart.CartClientResponse;
import com.company.cart.dto.client.item.ItemClientRequest;
import com.company.cart.dto.client.item.ItemClientResponse;
import com.company.cart.dto.feign.ItemFeignClientDto;
import com.company.cart.entity.CartEntity;
import com.company.cart.entity.ItemEntity;
import com.company.cart.exception.exceptions.cart.CartNotFoundException;
import com.company.cart.exception.exceptions.cart.CartProfileIdNotValidException;
import com.company.cart.exception.exceptions.item.ItemNotFoundException;
import com.company.cart.exception.exceptions.item.ItemVersionNotValidException;
import com.company.cart.feign.ProductFeignClient;
import com.company.cart.repository.CartRepository;
import com.company.cart.repository.ItemRepository;
import com.company.cart.service.client.CartClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.company.cart.mapper.client.CartClientMapper.*;
import static com.company.cart.mapper.client.ItemClientMapper.mapToItemClientResponse;
import static com.company.cart.mapper.feign.ItemFeignClientMapper.mapToItemEntity;
import static com.company.cart.util.CacheEvictUtility.evictAllCaches;


@Service
@RequiredArgsConstructor
public class CartClientServiceImpl implements CartClientService {

    private final CartRepository cartRepository;

    private final ItemRepository itemRepository;

    private final ProductFeignClient productFeignClient;


    @Override
    @Transactional
    @Cacheable(cacheNames = {"carts_details"}, key = "#profileId", unless = "#result == null ")
    public CartClientDetailedResponse getCartWithItems(Long profileId) {
        CartEntity cart = cartRepository
                .findByProfileId(profileId)
                .orElseThrow(
                        () -> new CartNotFoundException(
                                "Can not find cart by current profile id: " + profileId + " !"
                        )
                );
        return mapToCartDetailedClientResponse(cart);
    }

    @Override
    @CachePut(cacheNames = {"carts"}, key = "#cartClientRequest.profileId", unless = "#result == null ")
    public CartClientResponse createCart(CartClientRequest cartClientRequest) {
        CartEntity cart = mapToCartEntity(cartClientRequest);
        cartRepository.save(cart);
        return mapToCartClientResponse(cart);
    }


    @Override
    @Transactional
    @CachePut(cacheNames = {"items"}, key = "#profileId", unless = "#result == null ")
    public ItemClientResponse addItemToTheCart(
            ItemClientRequest itemClientRequest, Long profileId) {

        CartEntity cart = cartRepository
                .findByProfileId(profileId)
                .orElseThrow(
                        () -> new CartNotFoundException(
                                "Can not find cart by current profile id: " + profileId + " !"
                        )
                );

        ItemFeignClientDto itemFeignClientDto = productFeignClient
                .getProductForCart(itemClientRequest.getProductId());

        ItemEntity itemEntity = mapToItemEntity(itemFeignClientDto);
        itemEntity.setItemsCount(itemClientRequest.getItemsCount());
        itemEntity.setVersion(itemClientRequest.getVersion());
        itemEntity.setCart(cart);

        itemRepository.save(itemEntity);

        return mapToItemClientResponse(itemEntity);
    }

    @Override
    @Transactional
    public Double calculateItemsPriseInCart(Long profileId) {
        return itemRepository.countAllByPrice(profileId);
    }

    @Override
    @Transactional
    public Long getCartIdForOrder(Long profileId) {
        return cartRepository.getCartIdByProfileId(profileId);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"items"}, key = "#profileId")
    public void removeItemFromCart(Long profileId, Long itemId, Long itemVersion) {
        if (!cartRepository.existsByProfileId(profileId)) {
            throw new CartProfileIdNotValidException(
                    "Users profile id not valid for current cart: " + profileId + " !"
            );
        }
        if (!itemRepository.existsByProductId(itemId)) {
            throw new ItemNotFoundException(
                    "Can not find item by current id: " + itemId + " !"
            );
        }
        if (!itemRepository.existsByVersion(itemVersion)) {
            throw new ItemVersionNotValidException(
                    "Item Entity version: " + itemVersion + " not valid!"
            );
        }
        itemRepository.deleteByProductIdAndCart_ProfileId(itemId, profileId);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"carts"}, key = "#profileId")
    public void clearCart(Long profileId, Long cartVersion) {
        if (!cartRepository.existsByProfileId(profileId)) {
            throw new CartProfileIdNotValidException(
                    "Users profile id not valid for current cart: " + profileId + " !"
            );
        }
        if (!cartRepository.existsByVersion(cartVersion)) {
            throw new ItemVersionNotValidException(
                    "Cart Entity version: " + cartVersion + " not valid!"
            );
        }
        itemRepository.deleteAllByCart_ProfileId(profileId);
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }
}
