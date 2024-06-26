package com.company.cart.service.client.implementation;

import com.company.cart.dto.client.cart.CartClientRequest;
import com.company.cart.dto.client.cart.CartClientResponse;
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


import static com.company.cart.mapper.client.CartClientMapper.mapToCartClientResponse;
import static com.company.cart.mapper.client.CartClientMapper.mapToCartEntity;
import static com.company.cart.mapper.feign.ItemFeignClientMapper.mapToItemEntity;
import static com.company.cart.util.CacheEvictUtility.evictAllCaches;


@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"carts"})
public class CartClientServiceImpl implements CartClientService {

    private final CartRepository cartRepository;

    private final ItemRepository itemRepository;

    private final ProductFeignClient productFeignClient;


    @Override
    @Transactional
    @Cacheable(unless = "#result == null ", key = "#profileId")
    public CartClientResponse getCartWithItems(Long profileId) {
        CartEntity cart = cartRepository
                .findByProfileId(profileId)
                .orElseThrow(
                        () -> new CartNotFoundException(
                                "Can not find cart by current profile id: " + profileId + " !"
                        )
                );
        return mapToCartClientResponse(cart);
    }

    @Override
    @Transactional
    @CachePut(unless = "#result == null ", key = "#cartClientRequest.profileId")
    public CartClientResponse addItemToTheCart(
            CartClientRequest cartClientRequest, Long itemId, Long itemVersion) {

        CartEntity cart = cartRepository
                .findByProfileId(cartClientRequest.getProfileId())
                .orElseThrow(
                        () -> new CartNotFoundException(
                                "Can not find cart by current profile id: "
                                        + cartClientRequest.getProfileId() + " !"
                        )
                );

        ItemFeignClientDto item = productFeignClient.getProductForCart(itemId);

        ItemEntity itemEntity = mapToItemEntity(item);

        itemEntity.setVersion(itemVersion);

        if (!cart.getProfileId().equals(cartClientRequest.getProfileId())) {
            cartRepository.save(mapToCartEntity(cartClientRequest));
        } else {
            cart.getItems().add(itemEntity);
        }

        itemRepository.save(itemEntity);

        return mapToCartClientResponse(cart);
    }

    @Override
    @Transactional
    @Cacheable(unless = "#result == null ", key = "#profileId")
    public Double calculateItemsPriseInCart(Long profileId) {
        return itemRepository.countAllByPrice(profileId);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#profileId")
    public void removeItemFromCart(Long profileId, Long itemId, Long itemVersion) {
        if (!cartRepository.existsByProfileId(profileId)) {
            throw new CartProfileIdNotValidException(
                    "Users profile id not valid for current cart: " + profileId + " !"
            );
        }
        if (!itemRepository.existsById(itemId)) {
            throw new ItemNotFoundException(
                    "Can not find item by current id: " + itemId + " !"
            );
        }
        if (!itemRepository.existsByVersion(itemVersion)) {
            throw new ItemVersionNotValidException(
                    "Item Entity version: " + itemVersion + " not valid!"
            );
        }
        itemRepository.deleteByIdAndCart_ProfileId(itemId, profileId);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#profileId")
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
