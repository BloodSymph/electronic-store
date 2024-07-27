package com.company.order.controller.client;

import com.company.order.service.client.OrderClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-service/client")
public class OrderClientController {

    private final OrderClientService orderClientService;

    

}
