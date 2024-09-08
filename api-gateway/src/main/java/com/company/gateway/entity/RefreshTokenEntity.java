package com.company.gateway.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RefreshToken")
@Table(name = "refresh_tokens")
@ToString
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long id;

    @Column(name = "refresh_token", nullable = false, unique = true)
    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "user_username", referencedColumnName = "user_username")
    private UserEntity owner;

    @Column(name = "refresh_token_expiration", nullable = false)
    private Instant expiryDate;

}
