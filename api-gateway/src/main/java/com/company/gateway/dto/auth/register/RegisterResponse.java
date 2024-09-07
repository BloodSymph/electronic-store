package com.company.gateway.dto.auth.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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
