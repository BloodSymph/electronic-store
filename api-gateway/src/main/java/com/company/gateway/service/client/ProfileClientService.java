package com.company.gateway.service.client;

import com.company.gateway.dto.client.ProfileClientRequest;
import com.company.gateway.dto.client.ProfileClientResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface ProfileClientService {

    ProfileClientResponse getProfile();

    ProfileClientResponse createProfile(ProfileClientRequest profileClientRequest);

    ProfileClientResponse updateProfile(ProfileClientRequest profileClientRequest);

    void deleteProfile(Long profileVersion);

}
