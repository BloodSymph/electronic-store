package com.company.order.service.client.implementation;

import com.company.order.dto.client.OrderClientRequest;
import com.company.order.dto.client.OrderClientResponse;
import com.company.order.entity.OrderEntity;
import com.company.order.exception.exceptions.OrderNotFoundException;
import com.company.order.exception.exceptions.OrderVersionNotValidException;
import com.company.order.feign.CartFeignClient;
import com.company.order.repository.OrderRepository;
import com.company.order.service.client.OrderClientService;
import com.company.order.util.OrderCodeGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.company.order.mapper.client.OrderClientMapper.mapOrderClientRequestToOrderEntity;
import static com.company.order.mapper.client.OrderClientMapper.mapToOrderClientResponse;
import static com.company.order.util.CacheEvictUtility.evictAllCaches;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"orders_client"})
public class OrderClientServiceImpl implements OrderClientService {

    private final OrderRepository orderRepository;

    private final CartFeignClient cartFeignClient;

    @Override
    @Transactional
    @CachePut(key = "#orderClientRequest.profileId", unless = "#result == null ")
    public OrderClientResponse makeAnOrder(OrderClientRequest orderClientRequest) {
        OrderEntity order = mapOrderClientRequestToOrderEntity(orderClientRequest);
        order.setCartId(cartFeignClient.getCartIdForOrder(orderClientRequest.getProfileId()));
        order.setOrderPrice(cartFeignClient.itemsPrice(orderClientRequest.getProfileId()));
        order.setOrderCode(OrderCodeGeneratorUtil.generateOrderCode());
        orderRepository.save(order);
        return mapToOrderClientResponse(order);
    }

    @Override
    @Cacheable(key = "#profileId", unless = "#result == null ")
    public OrderClientResponse showTheOrder(Long profileId) {
        OrderEntity order = orderRepository
                .showOrderByProfileId(profileId)
                .orElseThrow(
                        () -> new OrderNotFoundException(
                                "Can not find order by current profile id: " + profileId + " !"
                        )
                );
        return mapToOrderClientResponse(order);
    }

    @Override
    @Transactional
    public void removeOrder(Long profileId, Long orderVersion) {
        if (!orderRepository.existsByProfileId(profileId)) {
            throw new OrderNotFoundException(
                    "Can not find order by current profile id: " + profileId + " !"
            );
        }
        if (!orderRepository.existsByVersion(orderVersion)) {
            throw new OrderVersionNotValidException(
                    "Order Entity version: " + orderVersion + " not valid!"
            );
        }
        orderRepository.deleteByProfileId(profileId);
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }

}
