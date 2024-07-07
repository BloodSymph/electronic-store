package com.company.order.service.client.implementation;

import com.company.order.dto.client.order.OrderClientDetailedResponse;
import com.company.order.dto.client.order.OrderClientRequest;
import com.company.order.dto.client.order.OrderClientResponse;
import com.company.order.dto.client.ordered.OrderedItemsClientRequest;
import com.company.order.dto.client.ordered.OrderedItemsClientResponse;
import com.company.order.entity.OrderEntity;
import com.company.order.feign.CartFeignClient;
import com.company.order.repository.OrderRepository;
import com.company.order.repository.OrderedItemsRepository;
import com.company.order.service.client.OrderClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.order.mapper.client.OrderClientMapper.mapOrderClientRequestToOrderEntity;
import static com.company.order.mapper.client.OrderClientMapper.mapToOrderClientResponse;
import static com.company.order.util.CacheEvictUtility.evictAllCaches;

@Service
@RequiredArgsConstructor
public class OrderClientServiceImpl implements OrderClientService {

    private final OrderRepository orderRepository;

    private final OrderedItemsRepository orderedItemsRepository;

    private final CartFeignClient cartFeignClient;

    @Override
    @Transactional
    public OrderClientResponse createNewOrder(OrderClientRequest orderClientRequest) {
        OrderEntity order = mapOrderClientRequestToOrderEntity(orderClientRequest);
        orderRepository.save(order);
        return mapToOrderClientResponse(order);
    }

    @Override
    @Transactional
    public OrderedItemsClientResponse addItemsToTheOrder(
            OrderedItemsClientRequest orderedItemsClientRequest, Long profileId) {
        return null;
    }

    @Override
    @Transactional
    public OrderClientDetailedResponse showOrder(Long profileId) {
        return null;
    }

    @Override
    @Transactional
    public void removeOrder(Long profileId, Long orderVersion) {

    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }
}
