package com.company.gateway.dto.client;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileClientResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String country;

    private String city;

    private String address;

    private String mailAddress;

}
