package com.company.gateway.service.auth;

import com.company.gateway.dto.auth.login.AuthenticationResponse;
import com.company.gateway.dto.auth.login.LoginRequest;
import com.company.gateway.dto.auth.register.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface AuthenticationService {

    AuthenticationResponse registerNewUser(RegisterRequest registerRequest);

    AuthenticationResponse login(LoginRequest loginRequest);

    ResponseEntity<?> refresh(
            HttpServletRequest httpServletRequest,
            HttpServletResponse response
    ) throws IOException;

}
