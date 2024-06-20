package com.company.order.service.client;

import com.company.order.dto.feign.ProductFeignResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrderClientService {

    ProductFeignResponse addProductToCart(Long productId);

}
