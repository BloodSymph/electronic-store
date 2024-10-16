package com.company.auth.entity;

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
@Entity(name = "Profile")
@Table(name = "profiles")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @Column(name = "profile_first_name", nullable = false, length = 120)
    private String firstName;

    @Column(name = "profile_last_name", nullable = false, length = 120)
    private String lastName;

    @Column(name = "profile_phone_number", nullable = false, length = 120)
    private String phoneNumber;

    @Column(name = "profile_country", nullable = false, length = 120)
    private String country;

    @Column(name = "profile_city", nullable = false, length = 120)
    private String city;

    @Column(name = "profile_address", nullable = false, length = 120)
    private String address;

    @Column(name = "profile_mail_address", nullable = false, length = 120)
    private String mailAddress;

    @CreationTimestamp
    @Column(name = "profile_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "profile_updated")
    private LocalDateTime updated;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "user_username")
    private UserEntity user;

    @Version
    @Column(name = "profile_version", nullable = false, unique = true)
    private Long version;


}
