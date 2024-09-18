package com.company.auth.dto.client;

import lombok.*;

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
