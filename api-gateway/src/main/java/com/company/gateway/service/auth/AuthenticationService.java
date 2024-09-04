package com.company.gateway.service.auth;

import com.company.gateway.dto.auth.AuthorizationResponse;
import com.company.gateway.dto.auth.LoginRequest;
import com.company.gateway.dto.auth.RegisterRequest;
import com.company.gateway.dto.auth.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    RegisterResponse registerNewUser(RegisterRequest registerRequest);

    AuthorizationResponse login(LoginRequest loginRequest);

    //todo: Think about refresh token
    AuthorizationResponse refresh();

}
