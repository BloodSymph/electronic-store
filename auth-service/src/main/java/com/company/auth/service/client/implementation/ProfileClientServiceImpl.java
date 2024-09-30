package com.company.auth.service.client.implementation;

import com.company.auth.dto.client.ProfileClientRequest;
import com.company.auth.dto.client.ProfileClientResponse;
import com.company.auth.entity.ProfileEntity;
import com.company.auth.entity.UserEntity;
import com.company.auth.exception.exceptions.profile.ProfileNotFoundException;
import com.company.auth.exception.exceptions.profile.ProfileVersionNotValidException;
import com.company.auth.repository.ProfileRepository;
import com.company.auth.repository.UserRepository;
import com.company.auth.service.client.ProfileClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.auth.mapper.client.ProfileClientMapper.mapProfileClientRequestToEntity;
import static com.company.auth.mapper.client.ProfileClientMapper.mapToProfileClientResponse;
import static com.company.auth.util.GetUserFromAuthSessionUtil.getSessionUser;

@Service
@RequiredArgsConstructor
public class ProfileClientServiceImpl implements ProfileClientService {

    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public ProfileClientResponse viewUserProfile() {
        String username = getSessionUser();
        ProfileEntity profile = profileRepository
                .findByUser_Username(username)
                .orElseThrow(
                        () -> new ProfileNotFoundException(
                                "Can not find profile by current username: " + username + "!"
                        )
                );
        return mapToProfileClientResponse(profile);
    }

    @Override
    @Transactional
    public ProfileClientResponse createProfile(ProfileClientRequest profileClientRequest) {
        String username = getSessionUser();
        UserEntity user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user by username: " + username + "!"
                        )
                );
        ProfileEntity profile = mapProfileClientRequestToEntity(profileClientRequest);
        profile.setUser(user);
        profileRepository.save(profile);
        return mapToProfileClientResponse(profile);
    }

    @Override
    @Transactional
    public ProfileClientResponse updateProfile(ProfileClientRequest profileClientRequest) {
        String username = getSessionUser();
        if (!profileRepository.existsByVersion(profileClientRequest.getVersion())){
            throw new ProfileVersionNotValidException(
                    "Profile Entity version not valid!"
            );
        }
        ProfileEntity profile = profileRepository
                .findByUser_Username(username)
                .orElseThrow(
                        () -> new ProfileNotFoundException(
                                "Can not find profile by current username: " + username + "!"
                        )
                );
        profile.setFirstName(profileClientRequest.getFirstName());
        profile.setLastName(profileClientRequest.getLastName());
        profile.setPhoneNumber(profileClientRequest.getPhoneNumber());
        profile.setCountry(profileClientRequest.getCountry());
        profile.setCity(profileClientRequest.getCity());
        profile.setAddress(profileClientRequest.getAddress());
        profile.setMailAddress(profileClientRequest.getMailAddress());
        profileRepository.save(profile);
        return mapToProfileClientResponse(profile);
    }

    @Override
    @Transactional
    public void deleteProfile(Long profileVersion) {
        String username = getSessionUser();
        if (!profileRepository.existsByUser_Username(username)) {
            throw new ProfileNotFoundException(
                    "Can not find profile by current username: " + username + "!"
            );
        }
        if (!profileRepository.existsByVersion(profileVersion)) {
            throw new ProfileVersionNotValidException(
                    "Profile Entity version not valid!"
            );
        }
        profileRepository.deleteByUser_Username(username);
    }

}
