package com.company.gateway.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class JWTService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private Long accessTokenExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private Long refreshTokenExpiration;

    public String generateAccessToken(Authentication authentication) {

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(
                currentDate.getTime() + accessTokenExpiration
        );

        String accessToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        System.out.println("New token: ");
        System.out.println(accessToken);

        return accessToken;

    }

    public String generateRefreshToken(String accessToken) {

        Date currentDate = new Date();
        Date expireDate = new Date(
                currentDate.getTime() + refreshTokenExpiration
        );

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        System.out.println("Refresh token: ");
        System.out.println(refreshToken);

        return refreshToken;
    }

    public String getUsernameFromAccessToken(String accessToken) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();

        return claims.getSubject();

    }

    public boolean validatedAccessToken(String accessToken) {

        try {

            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(accessToken);

            return true;

        } catch (Exception exception) {

            throw new AuthenticationCredentialsNotFoundException(
                    "JWT was expired or incorrect!",
                    exception.fillInStackTrace()
            );

        }

    }

    public String validateRefreshTokenExpiration(String refreshToken) {
        //todo: Make a validation for refresh token!
        return null;

    }

}
