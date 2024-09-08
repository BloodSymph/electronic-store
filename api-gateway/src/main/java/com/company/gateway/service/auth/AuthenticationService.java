package com.company.gateway.service.auth;

import com.company.gateway.dto.auth.login.AuthorizationResponse;
import com.company.gateway.dto.auth.login.LoginRequest;
import com.company.gateway.dto.auth.register.RegisterRequest;
import com.company.gateway.dto.auth.register.RegisterResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface AuthenticationService {

    RegisterResponse registerNewUser(RegisterRequest registerRequest);

    AuthorizationResponse login(LoginRequest loginRequest);

    void refresh(
            HttpServletRequest httpServletRequest,
            HttpServletResponse response
    ) throws IOException;

}
