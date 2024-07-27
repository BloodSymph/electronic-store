package com.company.order.service.client;

import com.company.order.dto.client.OrderClientRequest;
import com.company.order.dto.client.OrderClientResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface OrderClientService {

    OrderClientResponse makeAnOrder(OrderClientRequest orderClientRequest);

    OrderClientResponse showTheOrder(Long profileId);

    void removeOrder(Long profileId, Long orderVersion);

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}
