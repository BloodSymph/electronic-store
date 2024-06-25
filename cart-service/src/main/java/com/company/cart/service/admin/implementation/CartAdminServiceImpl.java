package com.company.cart.service.admin.implementation;

import com.company.cart.dto.admin.CartAdminResponse;
import com.company.cart.dto.admin.CartDetailedAdminResponse;
import com.company.cart.entity.CartEntity;
import com.company.cart.exception.exceptions.cart.CartNotFoundException;
import com.company.cart.exception.exceptions.cart.CartProfileIdNotValidException;
import com.company.cart.exception.exceptions.item.ItemVersionNotValidException;
import com.company.cart.mapper.admin.CartAdminMapper;
import com.company.cart.repository.CartRepository;
import com.company.cart.service.admin.CartAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.cart.mapper.admin.CartAdminMapper.mapToCartDetailedAdminResponse;
import static com.company.cart.util.CacheEvictUtility.evictAllCaches;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"carts"})
public class CartAdminServiceImpl implements CartAdminService {

    private final CartRepository cartRepository;

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<CartAdminResponse> getAllCarts(Pageable pageable) {
        return cartRepository
                .findAll(pageable)
                .map(CartAdminMapper::mapToCartAdminResponse);
    }

    @Cacheable(unless = "#result == null ")
    @Override
    public Page<CartAdminResponse> searchCarts(Pageable pageable, Long profileId) {
        return cartRepository
                .searchByProfileId(pageable, profileId)
                .map(CartAdminMapper::mapToCartAdminResponse);
    }

    @Override
    public CartDetailedAdminResponse getCartItems(Long profileId) {
        CartEntity cart = cartRepository
                .findByProfileId(profileId)
                .orElseThrow(
                        () -> new CartNotFoundException(
                                "Can not find cart by current profile id: " + profileId + " !"
                        )
                );
        return mapToCartDetailedAdminResponse(cart);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void deleteCart(Long profileId, Long cartVersion) {
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
        cartRepository.deleteByProfileId(profileId);
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }

}
