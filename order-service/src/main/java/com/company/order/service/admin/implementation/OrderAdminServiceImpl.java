package com.company.order.service.admin.implementation;

import com.company.order.dto.admin.OrderAdminResponse;
import com.company.order.entity.OrderEntity;
import com.company.order.exception.exceptions.OrderNotFoundException;
import com.company.order.exception.exceptions.OrderVersionNotValidException;
import com.company.order.mapper.admin.OrderAdminMapper;
import com.company.order.repository.OrderRepository;
import com.company.order.service.admin.OrderAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.order.mapper.admin.OrderAdminMapper.mapToOrderAdminResponse;
import static com.company.order.util.CacheEvictUtility.evictAllCaches;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"orders_admin"})
public class OrderAdminServiceImpl implements OrderAdminService {

    private final OrderRepository orderRepository;

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<OrderAdminResponse> getListOfOrders(Pageable pageable) {
        return orderRepository.
                findAll(pageable)
                .map(OrderAdminMapper::mapToOrderAdminResponse);
    }

    @Override
    public OrderAdminResponse getOrderByUserProfileId(Long profileId) {
        OrderEntity order = orderRepository
                .showOrderByProfileId(profileId)
                .orElseThrow(
                        () -> new OrderNotFoundException(
                                "Can not find order by current profile id: " + profileId + " !"
                        )
                );
        return mapToOrderAdminResponse(order);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void deleteUserOrder(Long profileId, Long orderVersion) {
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
