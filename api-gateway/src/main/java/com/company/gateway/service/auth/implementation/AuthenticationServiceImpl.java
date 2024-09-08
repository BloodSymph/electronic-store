package com.company.gateway.service.auth.implementation;

import com.company.gateway.dto.auth.login.AuthorizationResponse;
import com.company.gateway.dto.auth.login.LoginRequest;
import com.company.gateway.dto.auth.register.RegisterRequest;
import com.company.gateway.dto.auth.register.RegisterResponse;
import com.company.gateway.entity.RoleEntity;
import com.company.gateway.entity.UserEntity;
import com.company.gateway.exception.exceptions.role.RoleNotFoundException;
import com.company.gateway.exception.exceptions.user.UsernameIsTakenException;
import com.company.gateway.repository.RoleRepository;
import com.company.gateway.repository.UserRepository;
import com.company.gateway.security.service.JWTService;
import com.company.gateway.service.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;

import static com.company.gateway.mapper.auth.AuthenticationMapper.mapToRegisterResponse;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;


    @Override
    @Transactional
    public RegisterResponse registerNewUser(RegisterRequest registerRequest) {

        if (userRepository.existsByUsernameIgnoreCase(registerRequest.getUsername())) {
            throw new UsernameIsTakenException(
                    "Username: " + registerRequest.getUsername() + " is taken!"
            );
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setEmail(registerRequest.getEmail());
        userEntity.setPassword(
                passwordEncoder.encode(
                        registerRequest.getPassword()
                )
        );
        userEntity.setVersion(registerRequest.getVersion());

        RoleEntity roleEntity = roleRepository
                .findByNameIgnoreCase("ROLE_USER")
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Role dose not exist!"
                        )
                );

        userEntity.setRoles(Collections.singleton(roleEntity));

        userRepository.save(userEntity);

        return mapToRegisterResponse(userEntity);

    }

    @Override
    @Transactional
    public AuthorizationResponse login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtService.generateAccessToken(authentication);
        String refreshToken = jwtService.generateRefreshToken(authentication);

        AuthorizationResponse authorizationResponse = new AuthorizationResponse();
        authorizationResponse.setAccessToken(accessToken);
        authorizationResponse.setRefreshToken(refreshToken);

        return authorizationResponse;

    }

    //todo:Think better about this!!!

    @Override
    @Transactional
    public void refresh(
            HttpServletRequest httpServletRequest,
            HttpServletResponse response) throws IOException {

    }

}
