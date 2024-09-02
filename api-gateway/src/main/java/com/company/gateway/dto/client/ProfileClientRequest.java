package com.company.gateway.dto.client;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
//todo: Validate this please i am lazy
public class ProfileClientRequest {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String country;

    private String city;

    private String address;

    private String mailAddress;

    private Long version;

}
