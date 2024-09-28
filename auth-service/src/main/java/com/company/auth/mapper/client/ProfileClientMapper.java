package com.company.auth.mapper.client;

import com.company.auth.dto.client.ProfileClientRequest;
import com.company.auth.dto.client.ProfileClientResponse;
import com.company.auth.entity.ProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfileClientMapper {

    public static ProfileClientResponse mapToProfileClientResponse(ProfileEntity profile) {
        return ProfileClientResponse.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .phoneNumber(profile.getPhoneNumber())
                .country(profile.getCountry())
                .city(profile.getCity())
                .address(profile.getAddress())
                .mailAddress(profile.getMailAddress())
                .build();
    }

    public static ProfileEntity mapProfileClientRequestToEntity(ProfileClientRequest profile) {
        return ProfileEntity.builder()
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .phoneNumber(profile.getPhoneNumber())
                .country(profile.getCountry())
                .city(profile.getCity())
                .address(profile.getAddress())
                .mailAddress(profile.getMailAddress())
                .version(profile.getVersion())
                .build();
    }

}
