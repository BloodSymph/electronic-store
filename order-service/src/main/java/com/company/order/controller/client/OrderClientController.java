package com.company.order.controller.client;

import com.company.order.dto.client.order.OrderClientDetailedResponse;
import com.company.order.dto.client.order.OrderClientRequest;
import com.company.order.dto.client.order.OrderClientResponse;
import com.company.order.dto.client.ordered.OrderedItemsClientRequest;
import com.company.order.dto.client.ordered.OrderedItemsClientResponse;
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
    public OrderClientResponse createNewOrder(
            @Valid @RequestBody OrderClientRequest orderClientRequest) {
        return orderClientService.createNewOrder(orderClientRequest);
    }

    @PostMapping("/orders/{profileId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderedItemsClientResponse addItemsInOrder(
            @Valid @RequestBody OrderedItemsClientRequest orderedItemsClientRequest,
            @PathVariable(value = "profileId") Long profileId) {
        return orderClientService.addItemsToTheOrder(
                orderedItemsClientRequest, profileId
        );
    }

    @GetMapping("/orders/{profileId}/show")
    @ResponseStatus(HttpStatus.OK)
    public OrderClientDetailedResponse showOrder(
            @PathVariable("profileId") Long profileId) {
        return orderClientService.showOrder(profileId);
    }

    @DeleteMapping("/orders/{profileId}/remove")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeOrder(
            @PathVariable("profileId") Long profileId,
            @RequestParam(value = "orderVersion") Long orderVersion) {
        orderClientService.removeOrder(
                profileId, orderVersion
        );
        return new ResponseEntity<>(
                "Order successful deleted by profile id: " + profileId + " !",
                HttpStatus.OK
        );
    }

}
