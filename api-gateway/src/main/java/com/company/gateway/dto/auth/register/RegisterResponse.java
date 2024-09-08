package com.company.gateway.dto.auth.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterResponse {

    @JsonProperty("user_id")
    private Long id;

    private String username;

    private String email;

}
