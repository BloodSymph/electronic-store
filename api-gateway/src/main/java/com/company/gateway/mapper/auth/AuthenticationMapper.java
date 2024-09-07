package com.company.gateway.mapper.auth;

import com.company.gateway.dto.auth.register.RegisterRequest;
import com.company.gateway.dto.auth.register.RegisterResponse;
import com.company.gateway.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

    public static RegisterResponse mapToRegisterResponse(UserEntity user) {
        return RegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

}
