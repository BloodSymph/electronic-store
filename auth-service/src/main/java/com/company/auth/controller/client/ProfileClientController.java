package com.company.auth.controller.client;

import com.company.auth.dto.client.ProfileClientRequest;
import com.company.auth.dto.client.ProfileClientResponse;
import com.company.auth.service.client.ProfileClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/auth-service/client")
public class ProfileClientController {

    private final ProfileClientService profileClientService;

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public ProfileClientResponse viewUserProfile() {
        return profileClientService.viewUserProfile();
    }

    @PostMapping("/profile/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileClientResponse createProfile(
            @Valid @RequestBody ProfileClientRequest profileClientRequest) {
        return profileClientService.createProfile(profileClientRequest);
    }

    @PutMapping("/profile/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileClientResponse updateProfile(
            @Valid @RequestBody ProfileClientRequest profileClientRequest) {
        return profileClientService.updateProfile(profileClientRequest);
    }

    @DeleteMapping("/profile/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProfile(
            @RequestParam(value = "profileVersion") Long profileVersion) {
        profileClientService.deleteProfile(profileVersion);
        return new ResponseEntity<>(
                "Profile successful deleted!", HttpStatus.OK
        );
    }

}
