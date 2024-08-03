package com.company.order.service.admin;

import com.company.order.dto.admin.OrderAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface OrderAdminService {

    Page<OrderAdminResponse> getListOfOrders(Pageable pageable);

    OrderAdminResponse getOrderByUserProfileId(Long profileId);

    void deleteUserOrder(Long profileId, Long orderVersion);

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}
