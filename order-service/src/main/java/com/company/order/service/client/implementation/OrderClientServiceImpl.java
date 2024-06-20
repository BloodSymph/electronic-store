package com.company.order.service.client.implementation;

import com.company.order.dto.feign.ProductFeignResponse;
import com.company.order.entity.CartEntity;
import com.company.order.entity.ItemEntity;
import com.company.order.feign.ProductClient;
import com.company.order.repository.CartRepository;
import com.company.order.repository.ItemRepository;
import com.company.order.service.client.OrderClientService;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"orders"})
public class OrderClientServiceImpl implements OrderClientService {

    private final CartRepository cartRepository;

    private final ItemRepository itemRepository;

    private final ProductClient productClient;



}
