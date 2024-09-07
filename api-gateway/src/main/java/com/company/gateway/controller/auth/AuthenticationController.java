package com.company.gateway.controller.auth;

import com.company.gateway.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/api-gateway/authorization")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

}
