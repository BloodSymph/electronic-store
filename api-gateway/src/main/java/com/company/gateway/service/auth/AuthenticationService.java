package com.company.gateway.service.auth;

import com.company.gateway.dto.auth.login.AuthorizationResponse;
import com.company.gateway.dto.auth.login.LoginRequest;
import com.company.gateway.dto.auth.refresh.RefreshTokenRequest;
import com.company.gateway.dto.auth.register.RegisterRequest;
import com.company.gateway.dto.auth.register.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    RegisterResponse registerNewUser(RegisterRequest registerRequest);

    AuthorizationResponse login(LoginRequest loginRequest);

    AuthorizationResponse refresh(RefreshTokenRequest refreshTokenRequest);

}
