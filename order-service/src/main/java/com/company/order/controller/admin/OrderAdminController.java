package com.company.order.controller.admin;

import com.company.order.dto.admin.OrderAdminResponse;
import com.company.order.dto.client.OrderClientResponse;
import com.company.order.service.admin.OrderAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-service/admin")
public class OrderAdminController {

    private final OrderAdminService orderAdminService;

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderAdminResponse> getAllOrders(
            @PageableDefault(
                    sort = "orderCode",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 15
            ) Pageable pageable) {
        return orderAdminService.getListOfOrders(pageable);
    }

    @GetMapping("/orders/{profileId}/show")
    @ResponseStatus(HttpStatus.OK)
    public OrderAdminResponse showUsersOrder(
            @PathVariable(value = "profileId") Long profileId) {
        return orderAdminService.getOrderByUserProfileId(profileId);
    }

    @DeleteMapping("/orders/{profileId}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeOrder(
            @PathVariable(value = "profileId") Long profileId,
            @RequestParam(value = "orderVersion") Long orderVersion) {
        orderAdminService.deleteUserOrder(profileId, orderVersion);
        return new ResponseEntity<>(
                "Order successful removed by profileId: " + profileId + " !",
                HttpStatus.OK);
    }

}
