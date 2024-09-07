package com.company.gateway.service.client.implementation;

import com.company.gateway.dto.client.ProfileClientRequest;
import com.company.gateway.dto.client.ProfileClientResponse;
import com.company.gateway.repository.ProfileRepository;
import com.company.gateway.service.client.ProfileClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileClientServiceImpl implements ProfileClientService {

    private final ProfileRepository profileRepository;

    @Override
    public ProfileClientResponse getProfile() {
        return null;
    }

    @Override
    public ProfileClientResponse createProfile(ProfileClientRequest profileClientRequest) {
        return null;
    }

    @Override
    public ProfileClientResponse updateProfile(ProfileClientRequest profileClientRequest) {
        return null;
    }

    @Override
    public void deleteProfile(Long profileVersion) {

    }

}
