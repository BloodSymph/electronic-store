package com.company.cart.service.admin;

import com.company.cart.dto.admin.CartAdminResponse;
import com.company.cart.dto.admin.CartDetailedAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface CartAdminService {

    Page<CartAdminResponse> getAllCarts(Pageable pageable);

    Page<CartAdminResponse> searchCarts(Pageable pageable, Long profileId);

    CartDetailedAdminResponse getCartItems(Long profileId);

    void deleteCart(Long profileId, Long cartVersion);

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}
