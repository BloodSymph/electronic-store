package com.company.auth.mapper.admin;

import com.company.auth.dto.admin.profile.ProfileAdminResponse;
import com.company.auth.entity.ProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfileAdminMapper {

    public static ProfileAdminResponse mapToProfileAdminResponse(ProfileEntity profile) {
        return ProfileAdminResponse.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .phoneNumber(profile.getPhoneNumber())
                .country(profile.getCountry())
                .city(profile.getCity())
                .address(profile.getAddress())
                .mailAddress(profile.getMailAddress())
                .created(profile.getCreated())
                .updated(profile.getUpdated())
                .version(profile.getVersion())
                .build();
    }

}
