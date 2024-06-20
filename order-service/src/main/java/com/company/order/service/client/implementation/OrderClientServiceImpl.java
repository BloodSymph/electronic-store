package com.company.order.service.client.implementation;

import com.company.order.dto.feign.ProductFeignResponse;
import com.company.order.entity.CartEntity;
import com.company.order.entity.ItemEntity;
import com.company.order.feign.ProductClient;
import com.company.order.repository.CartRepository;
import com.company.order.repository.ItemRepository;
import com.company.order.service.client.OrderClientService;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderClientServiceImpl implements OrderClientService {

    private final CartRepository cartRepository;

    private final ItemRepository itemRepository;

    private final ProductClient productClient;

    @Override
    public ProductFeignResponse addProductToCart(Long productId) {


        return null;
    }
}
