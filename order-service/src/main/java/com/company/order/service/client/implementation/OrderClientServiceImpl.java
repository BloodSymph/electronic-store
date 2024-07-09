package com.company.order.service.client.implementation;

import com.company.order.dto.client.order.OrderClientDetailedResponse;
import com.company.order.dto.client.order.OrderClientRequest;
import com.company.order.dto.client.order.OrderClientResponse;
import com.company.order.dto.client.ordered.OrderedItemsClientRequest;
import com.company.order.dto.client.ordered.OrderedItemsClientResponse;
import com.company.order.entity.OrderEntity;
import com.company.order.entity.OrderedItemsEntity;
import com.company.order.exception.exceptions.OrderNotFoundException;
import com.company.order.exception.exceptions.OrderVersionNotValidException;
import com.company.order.feign.CartFeignClient;
import com.company.order.repository.OrderRepository;
import com.company.order.repository.OrderedItemsRepository;
import com.company.order.service.client.OrderClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.order.mapper.client.OrderClientMapper.*;
import static com.company.order.mapper.client.OrderedItemsClientMapper.mapOrderedItemsClientRequestToOrderEntity;
import static com.company.order.mapper.client.OrderedItemsClientMapper.mapToOrderedItemsClientResponse;
import static com.company.order.util.CacheEvictUtility.evictAllCaches;
import static com.company.order.util.OrderCodeGeneratorUtility.orderCodeGenerate;

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
        order.setOrderCode(orderCodeGenerate());
        orderRepository.save(order);
        return mapToOrderClientResponse(order);
    }

    @Override
    @Transactional
    public OrderedItemsClientResponse addItemsToTheOrder(
            OrderedItemsClientRequest orderedItemsClientRequest, Long profileId) {

        OrderEntity order = orderRepository
                .showOrderByProfileId(profileId)
                .orElseThrow(
                        () -> new OrderNotFoundException(
                                "Can not found order by profile id: " + profileId + " !"
                        )
                );

        OrderedItemsEntity orderedItemsEntity = mapOrderedItemsClientRequestToOrderEntity(orderedItemsClientRequest);
        orderedItemsEntity.setCartId(cartFeignClient.getCartIdForOrder(profileId));
        orderedItemsEntity.setOrderPrice(cartFeignClient.itemsPrice(profileId));

        orderedItemsRepository.save(orderedItemsEntity);

        order.setOrderedItems(orderedItemsEntity);

        return mapToOrderedItemsClientResponse(orderedItemsEntity);
    }

    @Override
    @Transactional
    public OrderClientDetailedResponse showOrder(Long profileId) {
        OrderEntity order = orderRepository
                .showOrderByProfileId(profileId)
                .orElseThrow(
                        () -> new OrderNotFoundException(
                                "Can not found order by profile id: " + profileId + " !"
                        )
                );
        return mapToOrderClientDetailedResponse(order);
    }

    @Override
    @Transactional
    public void removeOrder(Long profileId, Long orderVersion) {
        if (!orderRepository.existsByProfileId(profileId)) {
            throw new OrderNotFoundException(
                    "Can not found order by profile id: " + profileId + " !"
            );
        }
        if (!orderRepository.existsByVersion(orderVersion)) {
            throw new OrderVersionNotValidException("Order Entity valid by version: " + orderVersion + " !");
        }
        orderRepository.deleteByProfileId(profileId);
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }

}
