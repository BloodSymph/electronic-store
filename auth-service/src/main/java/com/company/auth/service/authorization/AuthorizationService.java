package com.company.auth.service.authorization;

import com.company.auth.dto.authorization.AuthenticationResponse;
import com.company.auth.dto.authorization.LoginRequest;
import com.company.auth.dto.authorization.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface AuthorizationService {

    AuthenticationResponse registerNewUser(RegisterRequest registerRequest);

    AuthenticationResponse login(LoginRequest loginRequest);

    ResponseEntity<?> refresh(
            HttpServletRequest httpServletRequest,
            HttpServletResponse response
    ) throws IOException;

}
