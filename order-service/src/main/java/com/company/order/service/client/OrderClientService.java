package com.company.order.service.client;

import com.company.order.dto.client.order.OrderClientDetailedResponse;
import com.company.order.dto.client.order.OrderClientRequest;
import com.company.order.dto.client.order.OrderClientResponse;
import com.company.order.dto.client.ordered.OrderedItemsClientRequest;
import com.company.order.dto.client.ordered.OrderedItemsClientResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface OrderClientService {

    OrderClientResponse createNewOrder(OrderClientRequest orderClientRequest);

    OrderedItemsClientResponse addItemsToTheOrder(
            OrderedItemsClientRequest orderedItemsClientRequest, Long profileId
    );

    OrderClientDetailedResponse showOrder(Long profileId);

    void removeOrder(
            Long profileId, Long orderVersion
    );

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}
