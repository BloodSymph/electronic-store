package com.company.order.service.client.implementation;

import com.company.order.feign.CartFeignClient;
import com.company.order.repository.OrderRepository;
import com.company.order.repository.OrderedItemsRepository;
import com.company.order.service.client.OrderClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderClientServiceImpl implements OrderClientService {

    private final OrderRepository orderRepository;

    private final OrderedItemsRepository orderedItemsRepository;

    private final CartFeignClient cartFeignClient;



}
