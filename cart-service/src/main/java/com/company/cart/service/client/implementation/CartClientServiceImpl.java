package com.company.cart.service.client.implementation;

import com.company.cart.dto.client.CartClientResponse;
import com.company.cart.dto.client.ItemClientResponse;
import com.company.cart.entity.CartEntity;
import com.company.cart.feign.ProductFeignClient;
import com.company.cart.mapper.client.CartClientMapper;
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
    //todo: Make getting items by username or profile id
    public CartClientResponse getAllItemsInCart(Long cartId) {
        CartEntity cart = cartRepository.findAllById(cartId);
        return mapToCartClientResponse(cart);
    }

    @Override
    @Transactional
    @CachePut(value = "items", unless = "#result == null ")
    //todo: Make adding items by username or profile id
    public ItemClientResponse addItemToCart(String itemUrl) {
        ItemClientResponse item = productFeignClient.getProductForCart(itemUrl);

        return null;
    }

    @Override
    @Transactional
    //todo: Make calculation price of items by username or profile id
    public Integer calculatePriseOfItemsInCart(Long cartId) {
        return null;
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    //todo: Make removing items by username or profile id
    public void removeItemFromCart(String itemUrl, Long itemVersion) {

    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    //todo: Make clear cart by username or profile id
    public void clearCart(Long itemVersion) {

    }

    @Override
    public void evictAllCacheWithTime() {

    }
}
