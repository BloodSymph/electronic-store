package com.company.auth.service.authorization.implementation;

import com.company.auth.dto.authorization.AuthenticationResponse;
import com.company.auth.dto.authorization.LoginRequest;
import com.company.auth.dto.authorization.RegisterRequest;
import com.company.auth.entity.RoleEntity;
import com.company.auth.entity.TokenEntity;
import com.company.auth.entity.UserEntity;
import com.company.auth.exception.exceptions.role.RoleNotFoundException;
import com.company.auth.exception.exceptions.user.UsernameIsTakenException;
import com.company.auth.repository.RoleRepository;
import com.company.auth.repository.TokenRepository;
import com.company.auth.repository.UserRepository;
import com.company.auth.security.service.JWTService;
import com.company.auth.service.authorization.AuthorizationService;
import jakarta.inject.Singleton;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    @Override
    @Transactional
    public AuthenticationResponse registerNewUser(RegisterRequest registerRequest) {

        if(userRepository.existsByUsernameIgnoreCase(registerRequest.getUsername())) {
            throw new UsernameIsTakenException(
                    "Username: " + registerRequest.getUsername() + " is taken!"
            );
        }

        UserEntity user = new UserEntity();

        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(
                registerRequest.getPassword())
        );
        user.setVersion(registerRequest.getVersion());

        RoleEntity roleEntity = roleRepository
                .findByNameIgnoreCase("ROLE_USER")
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Role not founded!"
                        )
                );

        user.setRoles(Collections.singleton(roleEntity));

        user = userRepository.save(user);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(
                accessToken,
                refreshToken,
                "User registration was successful!"
        );

    }

    @Override
    @Transactional
    public AuthenticationResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        UserEntity user = userRepository
                .findByUsernameIgnoreCase(loginRequest.getUsername())
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user by username: " + loginRequest.getUsername() + "!"
                        )
                );

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(
                accessToken,
                refreshToken,
                "User login was successful!"
        );

    }

    @Override
    @Transactional
    public ResponseEntity<?> refresh(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException {

        String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);

        String username = jwtService.extractUsername(token);

        UserEntity user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user by username: " + username + "!"
                        )
                );

        if(jwtService.isValidRefreshToken(token, user)) {

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            revokeAllTokenByUser(user);

            saveUserToken(accessToken, refreshToken, user);

            return new ResponseEntity<>(
                    new AuthenticationResponse(
                            accessToken,
                            refreshToken,
                            "New token generated!"
                    ), HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    //todo: Make mechanic of changing password!

    private void revokeAllTokenByUser(UserEntity user) {

        List<TokenEntity> validTokens = tokenRepository
                .findAllAccessTokenByUserId(user.getId());

        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);

    }
    private void saveUserToken(
            String accessToken,
            String refreshToken,
            UserEntity user) {

        TokenEntity token = new TokenEntity();

        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);

        tokenRepository.save(token);

    }

}