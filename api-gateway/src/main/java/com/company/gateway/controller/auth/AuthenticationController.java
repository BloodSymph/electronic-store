package com.company.gateway.controller.auth;

import com.company.gateway.dto.auth.login.AuthenticationResponse;
import com.company.gateway.dto.auth.login.LoginRequest;
import com.company.gateway.dto.auth.register.RegisterRequest;
import com.company.gateway.service.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/api-gateway/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse registerUser(
            @Valid @RequestBody RegisterRequest registerRequest) {
        return authenticationService.registerNewUser(registerRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthenticationResponse loginUser(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        return authenticationService.login(loginRequest);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> refresh(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        return authenticationService.refresh(
                httpServletRequest, httpServletResponse
        );
    }

}
