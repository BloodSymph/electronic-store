package com.company.gateway.service.auth.implementation;

import com.company.gateway.dto.auth.login.AuthenticationResponse;
import com.company.gateway.dto.auth.login.LoginRequest;
import com.company.gateway.dto.auth.register.RegisterRequest;
import com.company.gateway.entity.RoleEntity;
import com.company.gateway.entity.TokenEntity;
import com.company.gateway.entity.UserEntity;
import com.company.gateway.exception.exceptions.role.RoleNotFoundException;
import com.company.gateway.exception.exceptions.user.UsernameIsTakenException;
import com.company.gateway.repository.RoleRepository;
import com.company.gateway.repository.TokenRepository;
import com.company.gateway.repository.UserRepository;
import com.company.gateway.security.service.JWTService;
import com.company.gateway.service.auth.AuthenticationService;
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
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;


    @Override
    @Transactional
    public AuthenticationResponse registerNewUser(RegisterRequest registerRequest) {

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

        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);

        saveUserToken(accessToken, refreshToken, userEntity);

        return new AuthenticationResponse(
                accessToken,
                refreshToken,
                "User: " + registerRequest.getUsername() + " login was successful!"
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
                .findByUsernameIgnoreCase(
                        loginRequest.getUsername()
                ).orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user by current username: " + loginRequest.getUsername() + " !"
                        )
                );

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllTokenByUser(user);

        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(
                accessToken,
                refreshToken,
                "User: " + loginRequest.getUsername() + " login was successful!"
        );
    }

    @Override
    @Transactional
    public ResponseEntity<?> refresh(
            HttpServletRequest httpServletRequest,
            HttpServletResponse response) throws IOException {

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
                                "Can not find user by current username: " + username + " !"
                        )
                );

        if(jwtService.isValidRefreshToken(token, user)) {

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            revokeAllTokenByUser(user);
            saveUserToken(accessToken, refreshToken, user);

            return new ResponseEntity<>(
                    new AuthenticationResponse(
                            accessToken, refreshToken, "New tokens are generated!"
                    ),
                    HttpStatus.OK
            );

        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    private void revokeAllTokenByUser(UserEntity owner) {

        List<TokenEntity> validTokens = tokenRepository.findAllAccessTokenByOwnerId(owner.getId());

        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);

    }
    private void saveUserToken(String accessToken, String refreshToken, UserEntity owner) {

        TokenEntity token = new TokenEntity();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setOwner(owner);

        tokenRepository.save(token);

    }

}
