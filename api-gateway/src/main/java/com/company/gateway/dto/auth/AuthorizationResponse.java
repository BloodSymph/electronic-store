package com.company.gateway.dto.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorizationResponse {

    private String tokenType = "Bearer ";

    private String accessToken;

    private String refreshToken;

}
