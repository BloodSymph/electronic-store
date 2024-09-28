package com.company.auth.service.client;

import com.company.auth.dto.client.ProfileClientRequest;
import com.company.auth.dto.client.ProfileClientResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProfileClientService {

    ProfileClientResponse viewUserProfile();

    ProfileClientResponse createProfile(ProfileClientRequest profileClientRequest);

    ProfileClientResponse updateProfile(ProfileClientRequest profileClientRequest);

    void deleteProfile(Long profileVersion);

}
