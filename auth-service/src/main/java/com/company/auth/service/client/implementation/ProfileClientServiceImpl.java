package com.company.auth.service.client.implementation;

import com.company.auth.repository.ProfileRepository;
import com.company.auth.service.client.ProfileClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileClientServiceImpl implements ProfileClientService {

    private final ProfileRepository profileRepository;

}
