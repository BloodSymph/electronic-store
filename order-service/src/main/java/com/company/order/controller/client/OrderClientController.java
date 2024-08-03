package com.company.order.controller.client;

import com.company.order.dto.client.OrderClientRequest;
import com.company.order.dto.client.OrderClientResponse;
import com.company.order.service.client.OrderClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-service/client")
public class OrderClientController {

    private final OrderClientService orderClientService;

    @PostMapping("/orders/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderClientResponse makeAnOrder(
            @Valid @RequestBody OrderClientRequest orderClientRequest) {
        return orderClientService.makeAnOrder(orderClientRequest);
    }

    @GetMapping("/orders/{profileId}/show")
    @ResponseStatus(HttpStatus.OK)
    public OrderClientResponse showTheOrder(
            @PathVariable(value = "profileId") Long profileId) {
        return orderClientService.showTheOrder(profileId);
    }

    @DeleteMapping("/orders/{profileId}/remove")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeOrder(
            @PathVariable(value = "profileId") Long profileId,
            @RequestParam(value = "orderVersion") Long orderVersion) {
        orderClientService.removeOrder(profileId, orderVersion);
        return new ResponseEntity<>(
                "Order successful removed by profileId: " + profileId + " !",
                HttpStatus.OK);
    }

}
