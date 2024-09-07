package com.company.gateway.dto.auth.refresh;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RefreshTokenRequest {

    @JsonProperty("token_type")
    private String tokenType = "Bearer ";

//    @NotEmpty
//    @NotBlank(message = "Username field shod not be empty!")
//    private String username;

    @NotEmpty
    @NotBlank(message = "Refresh token shod not be empty!")
    @JsonProperty("refresh_token")
    private String refreshToken;

}
