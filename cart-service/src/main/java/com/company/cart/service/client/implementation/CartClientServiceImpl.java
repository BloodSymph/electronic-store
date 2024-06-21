package com.company.cart.service.client.implementation;

import com.company.cart.dto.client.CartClientResponse;
import com.company.cart.dto.client.ItemClientResponse;
import com.company.cart.feign.ProductFeignClient;
import com.company.cart.repository.CartRepository;
import com.company.cart.repository.ItemRepository;
import com.company.cart.service.client.CartClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.cart.util.CacheEvictUtility.evictAllCaches;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"carts", "items"})
public class CartClientServiceImpl implements CartClientService {

    private final CartRepository cartRepository;

    private final ItemRepository itemRepository;

    private final ProductFeignClient productFeignClient;

    @Override
    @Cacheable(value = "carts", unless = "#result == null ")
    public CartClientResponse getAllItemsInCart() {
        return null;
    }

    @Override
    @Transactional
    @Cacheable(value = "items", unless = "#result == null ")
    public ItemClientResponse addItemToCart(String itemUrl) {
        return null;
    }

    @Override
    @Transactional
    public Integer calculatePriseOfItemsInCart() {
        return null;
    }

    @Override
    @Transactional
    public void removeItemFromCart(String itemUrl, Long itemVersion) {

    }

    @Override
    @Transactional
    public void clearCart(Long cartVersion) {

    }

    @Override
    public void evictAllCacheWithTime() {

    }
}
