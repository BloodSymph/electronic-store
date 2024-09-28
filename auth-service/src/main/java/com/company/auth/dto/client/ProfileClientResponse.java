package com.company.auth.dto.client;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProfileClientResponse {

    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String country;

    private String city;

    private String address;

    @JsonProperty("mail_address")
    private String mailAddress;

}
