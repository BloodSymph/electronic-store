package com.company.gateway.dto.auth;

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

    private Long id;

    private String username;

    private String email;

}
