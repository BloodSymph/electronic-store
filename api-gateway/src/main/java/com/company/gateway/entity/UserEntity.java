package com.company.gateway.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "User")
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_username", unique = true, nullable = false, length = 120)
    private String username;

    @Column(name = "user_email", unique = true, nullable = false, length = 120)
    private String email;

    @Column(name = "user_password", nullable = false, unique = true, length = 120)
    private String password;

    @CreationTimestamp
    @Column(name = "user_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "user_updated")
    private LocalDateTime updated;



    @Version
    @Column(name = "user_version", nullable = false, unique = true)
    private Long version;

}
